import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.server.*;

/*
 * 基于TCP协议的Socket通信，实现用户登陆
 * 服务器端
 */
public class ServerTest {

    static Logger logger = new Logger("Program");

    public static void main(String[] args) {
        Server server = new Server(8890);
        server.configure();
        server.run();
    }
}
