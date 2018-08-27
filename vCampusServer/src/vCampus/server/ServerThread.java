package vCampus.server;

import java.io.*;
import java.net.*;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import vCampus.server.http.*;
import vCampus.server.database.*;
import vCampus.server.util.Logger;

/*
 * 服务器线程处理类
 */
public class ServerThread extends Thread {
    // 和本线程相关的Socket
    private Socket socket;
    private static Logger logger = new Logger("ServerThread");

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    //线程执行的操作，响应客户端的请求
    public void run(){
        try {
            //获取输入流，并读取客户端信息
            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(socket.getInputStream()));//从客户端获取信息的流
            ObjectOutputStream oos = new ObjectOutputStream(socket
                    .getOutputStream());//向客户端发送信息的流
            HttpRequest request = (HttpRequest) ois.readObject();//把客户端发过来的流读取成对象形式

            // log
            logger.log(request.toString());

            socket.shutdownInput();//关闭输入流

            RequestMethod method = request.getType();

            DBHelper dbhelper = new DBHelper();
            HttpResponse response;
            if (method == RequestMethod.GET) {
                //获取输出流，响应客户端的请求
                String jsonData = dbhelper.select(request.getTableName(), request.getKey(), request.getValue());

                if(jsonData == null)
                     response = new HttpResponse("404", null, "Not Found");
                else
                    response = new HttpResponse("200", jsonData, "OK");

            } else if(method == RequestMethod.PATCH) {
                boolean updateSuc = dbhelper.update(request.getTableName(), request.getKey(), request.getValue(), request.getJsonData());
                if (updateSuc)
                    response = new HttpResponse("200", null, "OK");
                else
                    response = new HttpResponse("403", null, "update failed");
            } else if (method == RequestMethod.POST) {
                if (request.getRoute().equals("/account/login")) {
                        Map<String, String> requestData = request.data(Map.class);
                        String jsonData = dbhelper.selectOne("Account","Username", requestData.get("username"));
                        Map<String, String> resData = JSON.parseObject(jsonData, Map.class);
                        String pwd = resData.get("Password");
                        if(pwd == null)
                            response = new HttpResponse("404", null, "User not found.");
                        else if (pwd.equals(requestData.get("password")))
                            response = new HttpResponse("200", jsonData, "OK");
                        else
                            response = new HttpResponse("403", null, "Wrong password.");
                } else {
                    // 一般形式的post
                    boolean insertSuc = dbhelper.insert(request.getTableName(), request.getJsonData());
                    if (insertSuc)  // 创建成功，201
                        response = new HttpResponse("201", null, "OK");
                    else // 创建失败，403，往往是因为已经有创建过的了
                        response = new HttpResponse("403", null, request.getTableName() + " already exist.");
                }
            } else if(method == RequestMethod.DELETE) {
                boolean deleteSuc = dbhelper.delete(request.getTableName(), request.getKey(), request.getValue());
                if(deleteSuc)
                    response = new HttpResponse();
                else
                    response = new HttpResponse("404", null, request.getTableName() + " not found.");
            } else {
                response = new HttpResponse("404", null, "Request Url Not Found: " + request.getRoute());
            }
            oos.writeObject(response);
            socket.shutdownOutput();


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            //关闭资源
            try {
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

