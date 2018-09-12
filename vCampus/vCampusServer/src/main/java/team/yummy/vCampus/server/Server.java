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
import java.util.Timer;
import java.util.TimerTask;

/**
 * 服务器类，记录日志，管理session和中间件，连接服务器
 */
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

    /**
     * 根据分配的端口，运行服务器
     * @param port 设置服务器运行的端口
     */
    public Server(int port) {
        try {
            logger = new Logger("Server");
            serverSocket = new ServerSocket(port); // 与port绑定
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置数据库地址，与数据库进行连接，激活中间件
     */
    public void configure() {
        String url = "jdbc:Access:///" + System.getProperty("user.dir") + "\\vCampusServer\\database\\test_database.accdb";
        dbConfig = new Configuration().configure("/hibernate.cfg.xml");
        dbConfig.setProperty("hibernate.connection.url", url);
        dbFactory = dbConfig.buildSessionFactory();

        middlewares.add(new SessionMiddleware());
        middlewares.add(new AuthMiddleware());
        middlewares.add(new RoutingMiddleware());
    }

    /**
     * 运行服务器，定时清除session，同时循环监听客户端来凝结
     */
    public void run() {
        logger.log("服务器即将启动，等待客户端的连接...");

        // 为Session添加定时清除任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (HashMap.Entry entry : sessions.entrySet()) {
                    if (((Session) entry.getValue()).hasExpired()) {
                        sessions.remove(entry.getKey());
                    }
                }
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 120 * 1000;
        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, intervalPeriod);

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
