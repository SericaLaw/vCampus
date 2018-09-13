
package team.yummy.vCampus.server;

import team.yummy.vCampus.util.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 基于TCP协议的Socket通信，实现用户登录，为服务器端入口
 * @author Vigilans
 */
public class Program {
    static Logger logger = new Logger("Program");

    /**
     * 激活服务器，在port:8890开始运行
     * @param args 运行server所需命令
     */
    public static void main(String[] args) {
        Server server = new Server(8890);
        server.configure();
        server.run();
    }
}
