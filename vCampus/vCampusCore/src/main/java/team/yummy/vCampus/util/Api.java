package team.yummy.vCampus.util;

import java.io.ObjectOutputStream;

import team.yummy.vCampus.web.*;
import team.yummy.vCampus.util.Logger;

import java.net.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Map;

public class Api {
    private Logger logger = new Logger("API");
    private Integer sessionId = null;

    public Api() {

    }

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

    public WebResponse get(String route) {
        WebRequest request = new WebRequest(RequestMethod.GET, route);
        WebResponse response = null;
        return sendResponse(getSocket(), request, response);
    }

    // POST /tableName/key/:value, data 当新数据被创建
    public WebResponse post(String route, String data){
        WebRequest request = new WebRequest(RequestMethod.POST, route, data);
        WebResponse response = null;
        return sendResponse(getSocket(), request, response);
    }
    // PATCH /tableName/:id, data为json string 当数据被修改
    public WebResponse patch(String route, String data){
        WebRequest request = new WebRequest(RequestMethod.PATCH, route, data);
        WebResponse response = null;
        return sendResponse(getSocket(), request, response);
    }
    // DELETE 当数据被删除
    public WebResponse delete(String route){
        WebRequest request = new WebRequest(RequestMethod.DELETE, route);
        WebResponse response = null;
        return sendResponse(getSocket(), request, response);
    }

    private WebResponse sendResponse(Socket socket, WebRequest request, WebResponse response) {
        // config request
        request.setSessionId(sessionId);
        logger.log(request.toString());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            socket.shutdownOutput();


            ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            response = (WebResponse) ois.readObject();

            // 写入Session ID
            this.sessionId = response.getSessionId();

            logger.log(response.toString());

            socket.shutdownInput();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return response;
        }
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String toString() {
        return String.format("Api [ sessionId = %d ]", sessionId);
    }
}

