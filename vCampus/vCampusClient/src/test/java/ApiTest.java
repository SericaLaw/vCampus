
import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.models.*;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.WebResponse;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * test/database 21317XXXX用于API测试
 */
public class ApiTest {
    @Test
    public void testLogin() {
        WebResponse res;
        WebResponse expectedRes;

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
        expectedRes = new WebResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, loginAccount.equals(res.data(Account.class)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 404 用户不存在
        res = Api.post("/account/login", "{\"username\":\"LoginTTest\",\"password\":\"123\"}");
        expectedRes = new WebResponse("404",null, "Account not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 密码错误
        res = Api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123456\"}");
        expectedRes = new WebResponse("403", null, "Wrong password.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());


    }

    @Test
    public void testRegisterAndUnRegister() {
        WebResponse expectedRes;
        WebResponse res;
        Account newAccount;

        newAccount = new Account("213170005", "RegisterTest","123","Bar", "Foo", RoleEnum.STUDENT);
        res = Api.post("/account", JSON.toJSONString(newAccount));
        expectedRes = new WebResponse("201", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 下面的登录操作可以成功，说明注册的确成功了
        res = Api.post("/account/login", "{\"username\":\"RegisterTest\",\"password\":\"123\"}");
        expectedRes = new WebResponse("200", "omitted", "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, newAccount.equals(res.data(Account.class)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 User already exist.
        newAccount = new Account("213170012", "RegisterTest","123","Bar", "Foo", RoleEnum.STUDENT);
        res = Api.post("/account", JSON.toJSONString(newAccount));
        expectedRes = new WebResponse("403", null, "Account already exist.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 测试注销
        res = Api.delete("/account/campusCardID/213170005");
        expectedRes = new WebResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 下面的登录操作失败，说明注销成功了
        res = Api.post("/account/login", "{\"username\":\"RegisterTest\",\"password\":\"123\"}");
        expectedRes = new WebResponse("404",null, "Account not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 测试注销一个不存在的账户
        res = Api.delete("/account/campusCardID/213170002");
        expectedRes = new WebResponse("404", null, "Account not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());
    }

    @Test
    public void testStuInfo() {
        /**
         * POST ~/StuInfo
         */
        WebResponse res;
        WebResponse expectedRes;
        StuInfo newStuInfo = new StuInfo(
                "213170000",
                "09017101",
                "SeniorHigh",
                "IDNum",
                "Beijing",
                SexEnum.MALE,
                "School of CS",
                "Computer Science"
        );
        // 201 OK
        res = Api.post("/stuInfo", JSON.toJSONString(newStuInfo));
        expectedRes = new WebResponse("201", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 Forbidden
        res = Api.post("/stuInfo", JSON.toJSONString(newStuInfo));
        expectedRes = new WebResponse("403", null, "StuInfo already exist.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 404不做了

        /**
         * GET ~/stuInfo/campusCardID/:id
         */
        res = Api.get("/stuInfo/campusCardID/213170000");
        expectedRes = new WebResponse("200", "omiited", "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, newStuInfo.equals(res.dataList(StuInfo.class, 0)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        /**
         * PATCH ~/stuInfo/campusCardID/:id
         * 暂时没有做鉴权，若要的话还需要在ServerThread里添加逻辑
         */
        StuInfo modifiedStuInfo = new StuInfo(
                "213170000",
                "09017101",
                "SeniorHigh",
                "IDNum",
                "Shanghai",
                SexEnum.MALE,
                "School of CS",
                "Software Engineering"
        );

        res = Api.patch("/stuInfo/campusCardID/213170000", JSON.toJSONString(modifiedStuInfo));
        expectedRes = new WebResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        res = Api.get("/stuInfo/campusCardID/213170000");
        expectedRes = new WebResponse("200", "omiited", "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, modifiedStuInfo.equals(res.dataList(StuInfo.class, 0)));
        assertEquals(expectedRes.getMessage(), res.getMessage());
        /**
         * DELETE ~/stuInfo/campusCardID/:id
         */
        res = Api.delete("/stuInfo/campusCardID/213170000");
        expectedRes = new WebResponse("200", null, "OK");

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
