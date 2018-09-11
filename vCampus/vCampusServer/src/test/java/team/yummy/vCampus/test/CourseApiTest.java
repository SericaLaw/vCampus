package team.yummy.vCampus.test;

import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.*;
import team.yummy.vCampus.util.Api;
import team.yummy.vCampus.web.WebResponse;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CourseApiTest extends ApiTest{

    @Test
    public void getCourseSchedule() {
        WebResponse res = api.get("/course/schedule");
        List<CourseScheduleViewModel> scheduleItems = res.dataList(CourseScheduleViewModel.class);
    }

    @Test
    public void getCourseReport() {
        WebResponse res = api.get("/course/report");
        List<CourseReportViewModel> reportItems = res.dataList(CourseReportViewModel.class);
    }

    @Test
    public void getCourseRegister() {
        WebResponse res = api.get("/course/register");
        List<CourseRegisterViewModel> courseRegister = res.dataList(CourseRegisterViewModel.class);
    }

    // 正常选课与退课
    @Test
    public void normalRegisterAndQuit() {
        Map<String, CourseRegisterViewModel> before = api.get("/course/register").dataList(CourseRegisterViewModel.class).stream()
                .collect(Collectors.toMap(CourseRegisterViewModel::getCourseID, Function.identity()));
        WebResponse post = api.post("/course/register","5002");
        Map<String, CourseRegisterViewModel> after = api.get("/course/register").dataList(CourseRegisterViewModel.class).stream()
                .collect(Collectors.toMap(CourseRegisterViewModel::getCourseID, Function.identity()));
        WebResponse delete = api.delete("/course/register/5002");
        Map<String, CourseRegisterViewModel> eventual = api.get("/course/register").dataList(CourseRegisterViewModel.class).stream()
                .collect(Collectors.toMap(CourseRegisterViewModel::getCourseID, Function.identity()));

        assert post.getStatusCode().equals("200") && delete.getStatusCode().equals("200");
        assert before.get("5002").getStuAttendCount() + 1 == after.get("5002").getStuAttendCount();
        assert after.get("5002").getStuAttendCount() - 1 == eventual.get("5002").getStuAttendCount();
    }

    // 冲突时选课与退课
    @Test
    public void fullRegisterAndQuit() {
        Map<String, CourseRegisterViewModel> before = api.get("/course/register").dataList(CourseRegisterViewModel.class).stream()
                .collect(Collectors.toMap(CourseRegisterViewModel::getCourseID, Function.identity()));
        WebResponse post = api.post("/course/register","1003");
        Map<String, CourseRegisterViewModel> after = api.get("/course/register").dataList(CourseRegisterViewModel.class).stream()
                .collect(Collectors.toMap(CourseRegisterViewModel::getCourseID, Function.identity()));
        WebResponse delete = api.delete("/course/register/1003");
        Map<String, CourseRegisterViewModel> eventual = api.get("/course/register").dataList(CourseRegisterViewModel.class).stream()
                .collect(Collectors.toMap(CourseRegisterViewModel::getCourseID, Function.identity()));

        assert post.getStatusCode().equals("403");
        assert delete.getStatusCode().equals("400") || delete.getStatusCode().equals("404");
        assert before.get("5002").getStuAttendCount() == after.get("5002").getStuAttendCount();
        assert after.get("5002").getStuAttendCount() == eventual.get("5002").getStuAttendCount();
    }

    /**
     * @apiGroup Course
     * @api {get} /course GetCourseList
     * @apiPermission admin
     * @apiDescription 获取课程列表
     * @apiSuccess List_CourseRegisterViewModel List of CourseRegisterViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course");
     * List<CourseRegisterViewModel> courseRegisterViewModels = res.dataList(CourseRegisterViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      [{
     *          "CourseName":"JAVA程序设计",
     *          "Semester":"2",
     *          "CourseVenue":"J2-203",
     *          "Grade":"3",
     *          "StuLimitCount":"3",
     *          "ExamDate":"2019-01-20 00:00:00.0",
     *          "CourseID":"1001",
     *          "Credit":"2",
     *          "Major":"计算机科学与技术",
     *          "ProfName":"沈傲东",
     *          "Intro":"大佬的课不得不听啊",
     *          "ExamVenue":"J4-302",
     *          "StuAttendCount":"1",
     *          "ProfCampusCardID":"1001"
     *         },{
     *          "CourseName":"高等数学",
     *          "Semester":"2",
     *          "CourseVenue":"J8-103",
     *          "Grade":"1",
     *          "StuLimitCount":"3",
     *          "ExamDate":"2019-01-18 00:00:00.0",
     *          "CourseID":"2001",
     *          "Credit":"5",
     *          "ProfName":"牛顿",
     *          "Intro":"同上",
     *          "ExamVenue":"J4-104",
     *          "StuAttendCount":"2",
     *          "ProfCampusCardID":"2001"
     *       }]
     * @apiErrorExample Error-Response:
     *     404 "Course not found."
     *
     */
    @Test
    public void getCourseList() {
        WebResponse res = api.get("/course");
        List<CourseRegisterViewModel> courseRegisterViewModels = res.dataList(CourseRegisterViewModel.class);
    }
}
