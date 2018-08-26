import com.alibaba.fastjson.JSON;
import vCampus.server.api.*;
import vCampus.server.http.*;
import vCampus.models.*;

public class ApiTest {

    public static void main(String[] args) {
        /**
         * Account
         * - login
         * - logout
         * - register
         */
//        HttpResponse res = Api.get("/account/campusCardID/213160000");
//        Account resAccount = res.data(Account.class);
//
        // login
        HttpResponse login = Api.post("/account/login", "{\"username\":\"Foo\",\"password\":\"Bar\"}");
        System.out.println(login.getJsonData());
        // register
//        Account newAccount = new Account("213160002", "Daisy","123","Daisy", "Johnson");
//        HttpResponse register = Api.post("/account", JSON.toJSONString(newAccount));
    }
}
