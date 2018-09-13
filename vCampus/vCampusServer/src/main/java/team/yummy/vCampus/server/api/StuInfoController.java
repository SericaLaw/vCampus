package team.yummy.vCampus.server.api;

import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.annotation.*;

import java.util.stream.Collectors;

public class StuInfoController extends Controller {

    AccountEntity account;

    @Override
    public void init(WebContext webContext) {
        super.init(webContext);
        Transaction tx = dbSession.beginTransaction();
        account = dbSession.get(AccountEntity.class, webContext.session.getString("campusCardId"));
        tx.commit();
    }


    /**
     * @apiGroup StuInfo
     * @api {get} /stuInfo/gpa CalculateGPA
     * @apiDescription 计算GPA
     * @apiPermission student admin
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/stuInfo/gpa");
     *
     * @apiSuccessExample Success-Response:
     *     200 OK
     */
    @Get(route = "gpa")
    public void calcGPA() {
        double gpa = 0;
        int credits = 0;
        // Serica: 需要过滤0分课程
        for (CourseRecordEntity record :
                account.getCourseRecordsByCampusCardId().stream()
                        .filter(r -> r.getScore() > 0)
                        .collect(Collectors.toList())) {
            double grade = 0;
            if (record.getScore() >= 60) {
                switch (record.getScore() % 10) {
                    case 9: case 8: case 7: case 6:
                        grade += 0.3;
                    case 5: case 4: case 3:
                        grade += 0.5;
                    case 0: case 1: case 2:
                        grade += 0.0;
                }
                grade += (record.getScore() - 50) / 10;
            }
            gpa += grade * record.getCourseByCourseId().getCredit().doubleValue();
            credits += record.getCourseByCourseId().getCredit().doubleValue();
        }
        gpa /= credits;

        dbSession.beginTransaction();
        account.getStuInfoByCampusCardId().setGpa(gpa);
        dbSession.save(account.getStuInfoByCampusCardId());
        dbSession.getTransaction().commit();
    }


    /**
     * 搜索学生信息、修改学生信息、添加学生可使用默认方法
     * 唔，当然会遇到权限问题
     */

    /**
     * @apiGroup StuInfo
     * @api {post} /stuInfo CreateStuInfo
     * @apiDescription 创建账号和学生信息，注意账号的campusCardId和StuInfo表关联，所以必须先创建账号，有初始化的密码和昵称。
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * AccountViewModel newAccount = new AccountViewModel();
     * newAccount.setCampusCardId("213190000");
     * newAccount.setFirstName("新");
     * newAccount.setLastName("生");
     * newAccount.setRole("student");
     * newAccount.setNickname("昵称");
     * newAccount.setPassword("09019000");
     *
     * StuInfoViewModel newStudent = new StuInfoViewModel();
     * newStudent.setEnrollmentYear(new Integer(2019));
     * newStudent.setCampusCardId("213190000");
     * newStudent.setStudentId("09019000");
     * newStudent.setDepartment("计算机科学与工程学院");
     * newStudent.setMajor("计算机科学与技术");
     *
     * WebResponse res = api.post("/account", JSON.toJSONString(newAccount));
     * if(res.getStatusCode().equals("201"))
     *    api.post("/stuInfo", JSON.toJSONString(newStudent));
     *
     * @apiSuccessExample Success-Response:
     *     201 OK
     *
     * @apiErrorExample Error-Response:
     *     403 "StuInfo already exists."
     *
     */

    /**
     * @apiGroup StuInfo
     * @api {patch} /stuInfo/campusCardID/:id ModifyStuInfo
     * @apiDescription 修改学生信息，该API不会返回修改后的StuInfo，若服务器告知修改成功，则前端自行对数据进行修改然后展示到界面上
     * @apiPermission student admin
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

    /**
     * @apiGroup StuInfo
     * @api {get} /stuInfo/campusCardID/{uid} GetStuInfoById
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
}
