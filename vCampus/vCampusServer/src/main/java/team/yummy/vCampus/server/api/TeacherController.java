package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.models.Semester;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.*;
import team.yummy.vCampus.server.annotation.*;
import team.yummy.vCampus.server.framework.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Authorize(roles = { "admin", "teacher" })
public class TeacherController extends Controller {
    /**
     * @apiGroup Course
     * @api {patch} /course/record ModifyCourseRecord
     * @apiPermission teacher
     * @apiDescription 登记或改变学生成绩
     * @apiParamExample Code Snippets
     * WebResponse res = api.patch("/course/record", "{"id":"uuid", "score":"the score"}");
     * @apiSuccessExample Success-Response:
     *      200 OK
     */
    @Patch(route = "record")
    public String modifyCourseRecord() {
        Map<String, String> body = webContext.request.deserializeBody(Map.class);
        dbSession.beginTransaction();
        CourseRecordEntity record = dbSession.get(CourseRecordEntity.class, body.get("id"));

        if (record.getCourseByCourseId().getProfCampusCardId() != account.getCampusCardId()) {
            webContext.response.setStatusCode("403");
            return "This course record does not belong to you.";
        }

        record.setScore(Integer.parseInt(body.get("score")));
        dbSession.update(record);
        dbSession.getTransaction().commit();
        return "OK";
    }

    /**
     * @apiGroup Course
     * @api {get} /course/record GetCourseRecord
     * @apiPermission teacher
     * @apiDescription 获取任教课程对应学生的CourseReport列表
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/record");
     * List<TeacherCourseReportViewModel> reportList = res.dataList(TeacherCourseReportViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     */
    @Get(route = "record")
    public void getCourseRecord() {
        dbSession.beginTransaction();
        List<CourseEntity> courseList = dbSession.createQuery(
            "select cr from CourseEntity cr where cr.semester = ?1 " +
                "and cr.profCampusCardId = ?2"
        ).setParameter(1, String.valueOf(new Semester().n))
        .setParameter(2, account.getCampusCardId()).list();
        dbSession.getTransaction().commit();

        List<TeacherCourseReportViewModel> reportList = new ArrayList<>();
        for (CourseEntity course : courseList) {
            for (CourseRecordEntity record : course.getCourseRecordsByCourseId()) {
                AccountEntity student = record.getAccountByCampusCardId();
                TeacherCourseReportViewModel report = new TeacherCourseReportViewModel();
                report.setReport(new CourseReportViewModel(
                    student.getCampusCardId(),
                    course.getCourseName(),
                    course.getCredit().doubleValue(),
                    record.getScore(),
                    record.getSemester(),
                    record.getScoreType()
                ));
                report.setFirstName(student.getFirstName());
                report.setLastName(student.getLastName());
                reportList.add(report);
            }
        }
        webContext.response.setBody(JSON.toJSONString(reportList));
    }

    /**
     * @apiGroup Course
     * @api {get} /course/teacher GetTeacherCourseSchedule
     * @apiPermission teacher
     * @apiDescription 根据ProfCampusCardId获取教师任教的课程表
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/teacher");
     * List<CourseScheduleViewModel> schedules = res.dataList(CourseScheduleViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     */
    @Get(route = "teacher")
    public void getTeacherCourseSchedule() {
        dbSession.beginTransaction();
        List<CourseEntity> courseList = dbSession.createQuery(
        "select cr from CourseEntity cr where cr.semester = ?1 " +
            "and cr.profCampusCardId = ?2"
        ).setParameter(1, String.valueOf(new Semester().n))
        .setParameter(2, account.getCampusCardId()).list();
        dbSession.getTransaction().commit();

        List<CourseScheduleViewModel> schedules = new ArrayList<>();
        for (CourseEntity course : courseList) {
            for (CourseScheduleEntity schedule : course.getCourseSchedulesByCourseId()) {
                schedules.add(new CourseScheduleViewModel(
                    course.getCourseId(),
                    schedule.getWeekDay(),
                    schedule.getSpanStart(),
                    schedule.getSpanEnd(),
                    course.getCourseName(),
                    course.getProfName(),
                    course.getCourseVenue()
                ));
            }
        }

        webContext.response.setBody(JSON.toJSONString(schedules));
    }
}
