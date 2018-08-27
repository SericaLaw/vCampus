package vCampus.server.test;
import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import vCampus.models.Account;
import vCampus.models.RoleEnum;
import vCampus.server.api.Api;
import vCampus.server.database.DBHelper;
import vCampus.server.http.HttpResponse;
import vCampus.server.util.Logger;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * test/database 21317XXXX用于API测试
 */
public class ApiTest {
    @Ignore
    @Test
    public void testLogin() {
        HttpResponse res;
        HttpResponse expectedRes;

        Account loginAccount = new Account(
                "213170000",
                "LoginTest",
                "123",
                "Bar",
                "Foo",
                RoleEnum.STUDENT
        );

        // 200
        res = Api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        expectedRes = new HttpResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, loginAccount.equals(res.data(Account.class)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 404 用户不存在
        res = Api.post("/account/login", "{\"username\":\"LoginTTest\",\"password\":\"123\"}");
        expectedRes = new HttpResponse("404",null, "User not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 密码错误
        res = Api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123456\"}");
        expectedRes = new HttpResponse("403", null, "Wrong password.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());


    }

    @Test
    public void testRegisterAndUnRegister() {
        HttpResponse expectedRes;
        HttpResponse res;
        Account newAccount;

        newAccount = new Account("213170002", "RegisterTest","123","Bar", "Foo", RoleEnum.STUDENT);
        res = Api.post("/account", JSON.toJSONString(newAccount));
        expectedRes = new HttpResponse("201", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 下面的登录操作可以成功，说明注册的确成功了
        res = Api.post("/account/login", "{\"username\":\"RegisterTest\",\"password\":\"123\"}");
        expectedRes = new HttpResponse("200", "omitted", "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, newAccount.equals(res.data(Account.class)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 User already created.
        newAccount = new Account("213170012", "RegisterTest","123","Bar", "Foo", RoleEnum.STUDENT);
        res = Api.post("/account", JSON.toJSONString(newAccount));
        expectedRes = new HttpResponse("403", null, "User already created.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 测试注销
        res = Api.delete("/account/campusCardID/213170002");
        expectedRes = new HttpResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 下面的登录操作失败，说明注销成功了
        res = Api.post("/account/login", "{\"username\":\"RegisterTest\",\"password\":\"123\"}");
        expectedRes = new HttpResponse("404",null, "User not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 测试注销一个不存在的账户
        res = Api.delete("/account/campusCardID/213170002");
        expectedRes = new HttpResponse("404", null, "Item not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());
    }

    private static Logger logger = new Logger("ApiTest");
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ApiTest.class);
        for(Failure failure : result.getFailures()) {
            logger.log(failure.toString());
        }
        if (result.wasSuccessful())
            logger.log("Unit test passed.");
        else
            logger.log("Unit test failed");
    }
}
