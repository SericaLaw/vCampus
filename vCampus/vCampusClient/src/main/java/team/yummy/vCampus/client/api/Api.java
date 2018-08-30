package team.yummy.vCampus.client.api;

import java.io.ObjectOutputStream;

import team.yummy.vCampus.web.*;
import team.yummy.vCampus.util.Logger;

import java.net.*;
import java.io.*;
public class Api {
    private static Logger logger = new Logger("API");
    private Api(){}
    // 每调用一次API，都开一个连接
    private static Socket getSocket() {
        Socket socket = null;
        try {
            // TODO:在下面配置服务器地址和端口
            socket = new Socket("localhost", 8890);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public static WebResponse get(String route) {
        WebRequest request = new WebRequest(RequestMethod.GET, route);
        WebResponse response = new WebResponse();
        return sendResponse(getSocket(), request, response);
    }

    // POST /tableName/key/:value, data 当新数据被创建
    public static WebResponse post(String route, String data){
        WebRequest request = new WebRequest(RequestMethod.POST, route, data);
        WebResponse response = new WebResponse();
        return sendResponse(getSocket(), request, response);
    }
    // PATCH /tableName/:id, data为json string 当数据被修改
    public static WebResponse patch(String route, String key, String data){
        WebRequest request = new WebRequest(RequestMethod.PATCH, route);
        WebResponse response = new WebResponse();
        return sendResponse(getSocket(), request, response);
    }
    // DELETE 当数据被删除
    public static WebResponse delete(String route){
        WebRequest request = new WebRequest(RequestMethod.DELETE, route);
        WebResponse response = new WebResponse("500", null);
        return sendResponse(getSocket(), request, response);
    }

    private static WebResponse sendResponse(Socket socket, WebRequest request, WebResponse response) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            socket.shutdownOutput();


            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            response = (WebResponse) ois.readObject();

            logger.log(response.toString());

            socket.shutdownInput();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return response;
        }
    }

    public static void main(String []ars) {

    }
}

