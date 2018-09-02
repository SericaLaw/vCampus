import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

public class StuInfoApiTest {

    private Api loginAndGetAuth() {
        Api api = new Api();
        WebResponse login = api.post("/account/login", "{\"username\":\"client\",\"password\":\"123\"}");
        assert login.getStatusCode().equals("200");
        Map<String, String > resData = login.data(HashMap.class);
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));
        return api;
    }
    /**
     * @apiGroup StuInfo
     * @api {get} /stuInfo/campusCardID/:id GetStuInfo ( passed )
     * @apiPermission student
     * @apiDescription 获取学生信息
     * @apiSuccess List_StuInfo 只含有一个项的StuInfo list
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/stuInfo/campusCardID/213180000");
     * StuInfo stuInfoGot = res.dataList(StuInfo.class, 0);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      [
     *          {
     *              "LectureAttendCount":"20",
     *              "Phone":"110",
     *              "Major":"计算机科学与技术",
     *              "SeniorHigh":"霍格沃茨魔法学校",
     *              "IDNum":"1234567",
     *              "GPA":"4.81",
     *              "EnrollmentYear":"2018",
     *              "StudentID":"09018101",
     *              "Birthplace":"临冬城",
     *              "Email":"boss@microsoft.com#mailto:boss@microsoft.com#",
     *              "SRTP":"100.0",
     *              "Address":"贝克街221B",
     *              "Birthdate":"1998-06-04 00:00:00.0",
     *              "Department":"计算机科学与工程学院",
     *              "CampusCardID":"213180000",
     *              "Sex":"男"
     *          }
     *      ]
     * @apiErrorExample Error-Response:
     *     404 "StuInfo not found."
     *
     */
    @Test
    public void getStuInfo() {
        Api api = loginAndGetAuth();

        WebResponse res = api.get("/stuInfo/campusCardID/213180000");
        StuInfo stuInfoGot = res.dataList(StuInfo.class, 0);
    }

    /**
     * @apiGroup StuInfo
     * @api {patch} /stuInfo/campusCardID/:id ModifyStuInfo ( passed )
     * @apiDescription 修改学生信息，该API不会返回修改后的StuInfo，若服务器告知修改成功，则前端自行对数据进行修改然后展示到界面上
     * @apiPermission student
     * @apiParamExample Code Snippets
     * JSONObject infoToModify = new JSONObject();
     * infoToModify.put("Phone", "120");
     * infoToModify.put("Sex","女");
     * api.patch("/stuInfo/campusCardID/213180000", infoToModify.toJSONString());
     *
     * @apiSuccessExample Success-Response:
     *     200 OK
     *
     * @apiErrorExample Error-Response:
     *     404 "StuInfo not found."
     *
     */
    @Test
    public void modifyStuInfo() {
        Api api = loginAndGetAuth();

        JSONObject infoToModify = new JSONObject();
        infoToModify.put("Phone", "120");
        infoToModify.put("Sex","女");

        api.patch("/stuInfo/campusCardID/213180000", infoToModify.toJSONString());
    }
}
