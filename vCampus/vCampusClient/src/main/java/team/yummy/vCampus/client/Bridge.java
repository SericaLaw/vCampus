package team.yummy.vCampus.client;

import team.yummy.vCampus.client.api.Api;

public class Bridge {

    private Bridge() {}

    public static void transmit(ViewController to, ViewController from) {
        to.setApi(from.getApi());
        to.setAccountJsonData(from.getAccountJsonData());
    }

}