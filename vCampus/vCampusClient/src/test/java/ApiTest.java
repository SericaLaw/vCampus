
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.models.*;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.WebResponse;

import java.util.*;

import static junit.framework.Assert.assertEquals;

/**
 * test/database 21317XXXX用于API测试
 */
public class ApiTest {
    Api api = new Api();
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
        res = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        expectedRes = new WebResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, loginAccount.equals(res.data(Account.class)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 登录后，写入鉴权信息
        api.setAuth("LoginTest", "213170000", "123");


        // 404 用户不存在
        res = api.post("/account/login", "{\"username\":\"LoginTTest\",\"password\":\"123\"}");
        expectedRes = new WebResponse("404",null, "Account not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 密码错误
        res = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123456\"}");
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
        res = api.post("/account", JSON.toJSONString(newAccount));
        expectedRes = new WebResponse("201", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 下面的登录操作可以成功，说明注册的确成功了
        res = api.post("/account/login", "{\"username\":\"RegisterTest\",\"password\":\"123\"}");
        expectedRes = new WebResponse("200", "omitted", "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, newAccount.equals(res.data(Account.class)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 Account already exist.
        // TODO: username是否唯一? 应用campusCardID作为账号的标识?
        newAccount = new Account("213170012", "RegisterTest","123","Bar", "Foo", RoleEnum.STUDENT);
        res = api.post("/account", JSON.toJSONString(newAccount));
        expectedRes = new WebResponse("403", null, "Account already exist.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 尝试在不登录的情况下伪造WebRequest请求
        res = api.delete("/account/campusCardID/213170005");
        expectedRes = new WebResponse("401", null, "Unauthorized");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 登录后，写入鉴权信息
        api.setAuth("RegisterTest", "213170005", "123");

        // 测试注销
        res = api.delete("/account/campusCardID/213170005");
        expectedRes = new WebResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 下面的登录操作失败，说明注销成功了
        res = api.post("/account/login", "{\"username\":\"RegisterTest\",\"password\":\"123\"}");
        expectedRes = new WebResponse("404",null, "Account not found.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

//        // 测试注销一个不存在的账户
//        res = api.delete("/account/campusCardID/213170002");
//        expectedRes = new WebResponse("404", null, "Account not found.");
//
//        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
//        assertEquals(expectedRes.getJsonData(), res.getJsonData());
//        assertEquals(expectedRes.getMessage(), res.getMessage());
    }

    @Test
    public void testStuInfo() {
        /**
         * 下面是一个从登录获取权限到调用其他api的完整流程
         */
        WebResponse res = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        assert res.getStatusCode().equals("200");
        Map<String, String > resData = res.data(HashMap.class);
        // 如果不setAuth，则直接返回401
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));
        /**
         * POST ~/stuInfo
         */
        WebResponse expectedRes;
        StuInfo newStuInfo = new StuInfo(
                "213170000",
                "09017101",
                "SeniorHigh",
                "310111111111111",
                "Beijing",
                SexEnum.MALE,
                "School of CS",
                "Computer Science"
        );
        // 201 OK
        res = api.post("/stuInfo", JSON.toJSONString(newStuInfo));
        expectedRes = new WebResponse("201", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 403 Forbidden
        res = api.post("/stuInfo", JSON.toJSONString(newStuInfo));
        expectedRes = new WebResponse("403", null, "StuInfo already exist.");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        // 404不做了

        /**
         * GET ~/stuInfo/campusCardID/:id
         */
        res = api.get("/stuInfo/campusCardID/213170000");
        expectedRes = new WebResponse("200", "omiited", "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, newStuInfo.equals(res.dataList(StuInfo.class, 0)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        /**
         * PATCH ~/stuInfo/campusCardID/:id
         */
        StuInfo modifiedStuInfo = new StuInfo(
                "213170000",
                "09017101",
                "SeniorHigh",
                "3100000000000",
                "Shanghai",
                SexEnum.MALE,
                "School of CS",
                "Software Engineering"
        );

        res = api.patch("/stuInfo/campusCardID/213170000", JSON.toJSONString(modifiedStuInfo));
        expectedRes = new WebResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

        res = api.get("/stuInfo/campusCardID/213170000");
        expectedRes = new WebResponse("200", "omiited", "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(true, modifiedStuInfo.equals(res.dataList(StuInfo.class, 0)));
        assertEquals(expectedRes.getMessage(), res.getMessage());

        /**
         * DELETE ~/stuInfo/campusCardID/:id
         */
        res = api.delete("/stuInfo/campusCardID/213170000");
        expectedRes = new WebResponse("200", null, "OK");

        assertEquals(expectedRes.getStatusCode(), res.getStatusCode());
        assertEquals(expectedRes.getJsonData(), res.getJsonData());
        assertEquals(expectedRes.getMessage(), res.getMessage());

    }

    @Test
    public void testBook() {
        WebResponse res;
        WebResponse expectedRes;
        String jsonData;

        res = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        assert res.getStatusCode().equals("200");
        Map<String, String > resData = res.data(HashMap.class);
        api.setAuth(resData.get("Username"), resData.get("CampusCardID"),resData.get("Password"));

        /**
         * GET ~/book
         * 返回数据库前20条信息，获取图书馆的书目列表
         */
        res = api.get("/book");

        /**
         * GET ~/book/bookName/:keyword/like
         * 模糊查询，按书名
         */
        res = api.get("/book/bookName/机器/like");

        /**
         * GET ~/borrowBook
         * 返回学生已借书籍列表
         */

        res = api.get("/borrowBook");
        /**
         * POST ~/borrowBook 借书
         * 在BorrowBook表里新增信息，数据库会自动修改availableCount
         * 前端负责实现展示书籍可借与否，后端availableCount < 0不处理
         */
        BorrowBookRecord borrowBookRecord = new BorrowBookRecord("101","213170000", new Date());
        jsonData = JSON.toJSONString(borrowBookRecord, SerializerFeature.WriteDateUseDateFormat);
        res = api.post("/borrowBook", jsonData);

        /**
         * DELETE ~/borrowBook/bookID/:bid/campusCardID/:cid 还书
         * 在BorrowBook里删除信息，数据库自动修改availableCount
         */
//        res = api.delete("/borrowBook/bookID/101/campusCardID/213170000");
//
//        res = api.delete("/borrowBook/bookID/201/campusCardID/213170000");


    }

    @Test
    public void testGetCourseSchedule() {
        WebResponse login = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        assert login.getStatusCode().equals("200");
        Map<String, String > resData = login.data(HashMap.class);
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));

        api.setAuth("LoginTest", "213170000", "123");
        WebResponse res = api.get("/course/schedule");

    }

    /**
     * 直接返回所有学期的成绩信息，不做单独查询
     */
    @Test
    public void testGetCourseReport() {
        WebResponse login = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        assert login.getStatusCode().equals("200");
        Map<String, String > resData = login.data(HashMap.class);
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));

        WebResponse res = api.get("/course/report");
    }

    /**
     * 选课系统，前端只需要做一个页面，不需要把已选课程单独拿出来！
     */
    @Test
    public void testGetCourseRegister() {
        WebResponse login = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        assert login.getStatusCode().equals("200");
        Map<String, String > resData = login.data(HashMap.class);
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));

        WebResponse res = api.get("/course/register");
    }

    /**
     * 选课
     * 这个api的data只要传courseID就好了；但前端必须保证不可选的课不会被传进来orz
     * 如果选课时已经满了，则返回403
     * TODO: 该api应该返回新的选课列表，需要写硬路由
     */
    @Test
    public void testRegisterCourse() {
        WebResponse login = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        assert login.getStatusCode().equals("200");
        Map<String, String > resData = login.data(HashMap.class);
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));

        WebResponse res = api.post("/course/register","5002");
        CourseRegister r = res.data(CourseRegister.class);
    }

    /**
     * 退课
     * TODO: 该api应该返回新的选课列表，需要写硬路由
     */
    @Test
    public void testQuitCourse() {
        WebResponse login = api.post("/account/login", "{\"username\":\"LoginTest\",\"password\":\"123\"}");
        assert login.getStatusCode().equals("200");
        Map<String, String > resData = login.data(HashMap.class);
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));

        WebResponse res = api.delete("/courseRecord/courseID/5002/campusCardID/213170000");
        CourseRegister r = res.data(CourseRegister.class);
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
