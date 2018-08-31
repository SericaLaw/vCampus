package team.yummy.vCampus.server;

import team.yummy.vCampus.util.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 基于TCP协议的Socket通信，实现用户登陆
 * 服务器端
 */
public class Program {

    static Logger logger = new Logger("Program");

    public static void main(String[] args) {
        Server server = new Server(8890);
        server.configure();
        server.run();
    }
}
