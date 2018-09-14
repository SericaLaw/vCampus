package team.yummy.vCampus.client;

/**
 * 多页程序间数据通信工具类，用于窗口切换时传送登陆鉴权信息
 * @author Serica
 */
public class Bridge {

    private Bridge() {}

    public static void transmit(ViewController to, ViewController from) {
        to.setApi(from.getApi());
        to.setAccountJsonData(from.getAccountJsonData());
    }

}
