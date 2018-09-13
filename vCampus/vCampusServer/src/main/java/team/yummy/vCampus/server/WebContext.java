package team.yummy.vCampus.server;

import java.io.*;
import java.net.*;
import java.util.Iterator;

import team.yummy.vCampus.server.middlewares.Middleware;
import team.yummy.vCampus.web.*;
import team.yummy.vCampus.util.Logger;

/**
 * 实现网络连接
 * @author Vigilans
 */
public class WebContext extends Thread {

    public Logger logger;

    public Server server;

    public Socket socket;

    public WebRequest request;

    public WebResponse response;

    public Session session;

    /**
     * 构造函数
     * @param socket 网络连接所需socket
     * @param server 对应服务器
     */
    public WebContext(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.logger = new Logger("WebContext");
        this.response = new WebResponse();
    }

    /**
     * 线程执行的操作（获取输入流读取信息，进行中间件处理，输出服务器响应）
     */
    public void run() {
        // 获取输入流，并读取客户端信息
        try {
            // 从客户端获取信息流
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // 把客户端发过来的流读取成对象形式
            request = (WebRequest) ois.readObject();
            logger.log(request.toString());

            // 关闭输入流
            socket.shutdownInput();
        } catch (ClassNotFoundException e) {
            // 400
        } catch (IOException e) {
            // 500
        }

        // 中间件管道处理
        try {
            new MiddlewareInvoker(server.middlewares.iterator()).invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 输出服务器端响应至客户端，并关闭socket连接
        try {
            // 获取指向客户端的输出流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());//向客户端发送信息的流

            // 序列化响应并返回给客户端
            oos.writeObject(response);
            logger.log(response.toString());

            // 关闭输出流
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 整个请求结束，关闭socket连接
         */
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 激活中间件进行处理
     */
    public class MiddlewareInvoker {
        // 记录当前context正在运行的中间件
        Iterator<Middleware> iterator;

        /**
         * 构造函数
         * @param iterator 中间件迭代器
         */
        MiddlewareInvoker(Iterator<Middleware> iterator) {
            this.iterator = iterator;
        }

        /**
         * 调用此函数使得管道进入下一个中间件
         */
        public void invoke() {
            if (iterator.hasNext()) {
                Middleware middleware = iterator.next();
                middleware.run(WebContext.this, this);
            }
        }
    }
}

