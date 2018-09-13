package team.yummy.vCampus.util;

import java.io.ObjectOutputStream;

import team.yummy.vCampus.web.*;
import team.yummy.vCampus.util.Logger;

import java.net.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Map;

/**
 * Api接口类，封装了Api的方法实现，前端可以通过Api类的对象示例向后端发送数据交互请求。
 * @author Serica
 */
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

    /**
     * 用于发送GET类型请求
     * @param body 请求路径
     * @return the WebResponse
     */
    public WebResponse get(String body) {
        WebRequest request = new WebRequest(RequestMethod.GET, body);
        WebResponse response = null;
        return sendResponse(getSocket(), request, response);
    }

    /**
     * 用于发送POST类型请求
     * @param route 请求路径
     * @param body 请求体，用于装载交互数据
     * @return the WebResponse
     */
    public WebResponse post(String route, String body){
        WebRequest request = new WebRequest(RequestMethod.POST, route, body);
        WebResponse response = null;
        return sendResponse(getSocket(), request, response);
    }

    /**
     * 用于发送PATCH类型请求
     * @param route 请求路径
     * @param body 请求体，用于装载交互数据
     * @return the WebResponse
     */
    public WebResponse patch(String route, String body){
        WebRequest request = new WebRequest(RequestMethod.PATCH, route, body);
        WebResponse response = null;
        return sendResponse(getSocket(), request, response);
    }

    /**
     * 用于发送DELETE类型请求
     * @param route 请求路径
     * @return the WebResponse
     */
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

    /**
     * 获取发送API请求的客户端对应的Session ID
     * @return the session ID
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * 设置发送API请求的客户端的Session ID
     * @param sessionId 客户端的Session ID
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String toString() {
        return String.format("Api [ sessionId = %d ]", sessionId);
    }
}