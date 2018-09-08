package team.yummy.vCampus.server;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import team.yummy.vCampus.server.middlewares.AuthMiddleware;
import team.yummy.vCampus.server.middlewares.Middleware;
import team.yummy.vCampus.server.middlewares.RoutingMiddleware;
import team.yummy.vCampus.server.middlewares.SessionMiddleware;
import team.yummy.vCampus.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class Server {
    // 日志记录
    private Logger logger;

    // Session容器
    public HashMap<Integer, Session> sessions = new HashMap<Integer, Session>();

    // 中间件列表
    public ArrayList<Middleware> middlewares = new ArrayList<Middleware>();

    // DbSession配置
    public Configuration dbConfig = null;
    public SessionFactory dbFactory = null;

    // 服务器端Socket
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            logger = new Logger("Server");
            serverSocket = new ServerSocket(port); // 与port绑定
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void configure() {
        dbConfig = new Configuration().configure("/hibernate.cfg.xml");
        dbFactory = dbConfig.buildSessionFactory();

        middlewares.add(new SessionMiddleware());
        middlewares.add(new AuthMiddleware());
        middlewares.add(new RoutingMiddleware());
    }

    public void run() {
        logger.log("服务器即将启动，等待客户端的连接...");

        // 循环监听等待客户端的连接
        while(true) {
            try {
                //调用accept()方法开始监听，等待客户端的连接
                Socket socket = serverSocket.accept();

                // 记录客户端IP
                logger.log("当前客户端的IP：" + socket.getInetAddress().getHostAddress());

                // 创建一个新的上下文线程
                WebContext webContext = new WebContext(socket, this);

                // 启动线程
                webContext.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
