package com.serica.demo.server.api;

import java.util.*;
import java.io.ObjectOutputStream;
import com.alibaba.fastjson.*;
import com.serica.demo.server.http.*;
import java.net.*;
import java.io.*;
public class API {
    private API(){}
    // 每调用一次API，都开一个连接
    private static Socket getSocket() {
        Socket socket = null;
        try {
            // TODO:在下面配置服务器地址和端口
            socket = new Socket("localhost", 8890);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
    // get '/tableName/:key/query'
    public static HttpResponse Get(String route) {
        // 由route分配到不同的方法
        String[] p = route.split("/");
        String tableName = p[1].substring(0, 1).toUpperCase() + p[1].substring(1);
        switch(tableName) {
            // TODO:根据不同的tableName, 对get进行配置
            case "Student":return get(route, "studentID");

        }
        // 其他情况，返回500
        return new HttpResponse("500", "invalid request url");
    }
    public static HttpResponse get(String route, String key) {
        HttpRequest request = new HttpRequest(RequestMethod.GET, route, key);
        HttpResponse response = new HttpResponse("500", null);
        return sendResponse(getSocket(), request, response);
    }
    // GET /student/:id 查询student表 id是123的学生
//    public static HttpResponse getStudent(String route) {
//        return get(route, "studentID");
//    }

    // POST 当新数据被创建
    public static HttpResponse post(String route, String key, String data){
        HttpRequest request = new HttpRequest(RequestMethod.POST, route, key);
        HttpResponse response = new HttpResponse("500", null);
        return sendResponse(getSocket(), request, response);
    }
    // PATCH 当数据被修改
    public static HttpResponse patch(String route, String key, String data){
        HttpRequest request = new HttpRequest(RequestMethod.PATCH, route, key, data);
        HttpResponse response = new HttpResponse("500", null);
        return sendResponse(getSocket(), request, response);
    }
    // DELETE 当数据被删除
    public static HttpResponse delete(String route, String key){
        HttpRequest request = new HttpRequest(RequestMethod.DELETE, route, key);
        HttpResponse response = new HttpResponse("500", null);
        return sendResponse(getSocket(), request, response);
    }

    private static HttpResponse sendResponse(Socket socket, HttpRequest request, HttpResponse response) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            socket.shutdownOutput();


            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            response =(HttpResponse) ois.readObject();

            System.out.println("我是客户端，服务器说："+ response.toString());
            socket.shutdownInput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return response;
        }
    }
}

