package vCampus.server.api;

import java.util.*;
import java.io.ObjectOutputStream;
import com.alibaba.fastjson.*;
import vCampus.server.http.*;
import java.net.*;
import java.io.*;
public class Api {
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

    public static HttpResponse get(String route) {
        HttpRequest request = new HttpRequest(RequestMethod.GET, route);
        HttpResponse response = new HttpResponse();
        return sendResponse(getSocket(), request, response);
    }

    // POST /tableName/key/:value, data 当新数据被创建
    public static HttpResponse post(String route, String data){
        HttpRequest request = new HttpRequest(RequestMethod.POST, route, data);
        HttpResponse response = new HttpResponse();
        return sendResponse(getSocket(), request, response);
    }
    // PATCH /tableName/:id, data为json string 当数据被修改
    public static HttpResponse patch(String route, String key, String data){
        HttpRequest request = new HttpRequest(RequestMethod.PATCH, route);
        HttpResponse response = new HttpResponse();
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

            System.out.println(response.toString());
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

