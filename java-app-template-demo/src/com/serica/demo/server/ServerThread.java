package com.serica.demo.server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import com.serica.demo.server.http.*;
import com.serica.demo.server.database.*;
/*
 * 服务器线程处理类
 */
public class ServerThread extends Thread {
    // 和本线程相关的Socket
    Socket socket = null;

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
            System.out.println("我是服务器，客户端说："+ request.toString());
            socket.shutdownInput();//关闭输入流

            RequestMethod method = request.getType();
            System.out.println("method: " + method);
            if (method == RequestMethod.GET) {
                //获取输出流，响应客户端的请求
                DBHelper dbhelper = new DBHelper();
                Connection conn = dbhelper.getConn();
                System.out.println("table name: " + request.getTableName());
                System.out.println("value: " + request.getValue());
                String jsonData = dbhelper.select(request.getTableName(), request.getKey(), request.getValue());

                HttpResponse response = new HttpResponse("200", jsonData);
                oos.writeObject(response);
                socket.shutdownOutput();
            } else if(method == RequestMethod.PATCH) {
                DBHelper dbhelper = new DBHelper();
                Connection conn = dbhelper.getConn();
                System.out.println("table name: " + request.getTableName());
                System.out.println("key: " + request.getKey());
                System.out.println("value: " + request.getValue());
                System.out.println("data: "+request.getData());
                dbhelper.update(request.getTableName(), request.getKey(), request.getValue(), request.getData());
                HttpResponse response = new HttpResponse("200", "success");
                oos.writeObject(response);
                socket.shutdownOutput();
            }else {
                HttpResponse response = new HttpResponse("500", "invalid request");
                oos.writeObject(response);
                socket.shutdownOutput();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            //关闭资源
            try {
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

