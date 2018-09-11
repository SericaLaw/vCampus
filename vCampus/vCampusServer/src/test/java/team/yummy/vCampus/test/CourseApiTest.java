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

    @Test
    public void getCourse() {
        WebResponse res = api.get("/course");
        List<CourseRegisterViewModel> courseRegisterViewModels = res.dataList(CourseRegisterViewModel.class);
    }
}
