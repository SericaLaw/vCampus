package team.yummy.vCampus.server.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import team.yummy.vCampus.models.CourseStatusEnum;
import team.yummy.vCampus.models.Semester;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.CourseRegisterViewModel;
import team.yummy.vCampus.models.viewmodel.CourseReportViewModel;
import team.yummy.vCampus.models.viewmodel.CourseScheduleViewModel;
import team.yummy.vCampus.server.annotation.*;
import team.yummy.vCampus.server.framework.Controller;

import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Authorize(roles = { "admin", "student" })
public class CourseController extends Controller {

    /**
     * @apiGroup Course
     * @api {get} /course/schedule GetCourseSchedule
     * @apiPermission student
     * @apiDescription 获取本学期课程表，暂不支持不同学期课程表的查询
     * @apiSuccess List_CourseScheduleViewModel 装着课程表表项的list
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/schedule");
     * List<CourseScheduleViewModel> scheduleItems = res.dataList(CourseScheduleViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      [
     *          {
     *              "SpanEnd":2,
     *              "ProfName":"牛顿",
     *              "CourseID":"2001",
     *              "CourseName":"高等数学",
     *              "CourseVenue":"J8-103",
     *              "WeekDay":2,
     *              "SpanStart":1
     *          },
     *          {
     *              "SpanEnd":4,
     *              "ProfName":"牛顿",
     *              "CourseID":"2001",
     *              "CourseName":"高等数学",
     *              "CourseVenue":"J8-103",
     *              "WeekDay":4,
     *              "SpanStart":3
     *          },
     *          {
     *              "SpanEnd":4,
     *              "ProfName":"亚里士多德",
     *              "CourseID":"3001",
     *              "CourseName":"大学物理",
     *              "CourseVenue":"J6-101",
     *              "WeekDay":1,
     *              "SpanStart":3
     *          },
     *          {
     *              "SpanEnd":7,
     *              "ProfName":"亚里士多德",
     *              "CourseID":"3001",
     *              "CourseName":"大学物理",
     *              "CourseVenue":"J6-101",
     *              "WeekDay":3,
     *              "SpanStart":6
     *          }
     *      ]
     */
    @Get(route = "schedule")
    public void getSchedule() {
        ArrayList<CourseScheduleViewModel> schedules = new ArrayList<>();
        for (CourseRecordEntity record : account.getCourseRecordsByCampusCardId()) {
            CourseEntity course = record.getCourseByCourseId();
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

    /**
     * @apiGroup Course
     * @api {get} /course/report GetCourseReport
     * @apiPermission student
     * @apiDescription 获取成绩信息，直接返回所有学期的成绩信息，不做按学期的单独查询；默认0分为未给分的课程，不会包含在返回的列表里
     * @apiSuccess List_CourseReportViewModel 装着课程表表项的list
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/report");
     * List<CourseReportViewModel> reportItems = res.dataList(CourseReportViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      [
     *          {
     *              "Semester":"17-18-3",
     *              "Credit":2,
     *              "CourseName":"中国古建筑鉴赏",
     *              "ScoreType":"首修",
     *              "Score":90
     *          }
     *      ]
     */
    @Get(route = "report")
    public void getReport() {
        ArrayList<CourseReportViewModel> reports = new ArrayList<>();
        for (CourseRecordEntity record : account.getCourseRecordsByCampusCardId()) {
            CourseEntity course = record.getCourseByCourseId();
            reports.add(new CourseReportViewModel(
                account.getCampusCardId(),
                course.getCourseName(),
                course.getCredit().doubleValue(),
                record.getScore(),
                record.getSemester(),
                record.getScoreType()
            ));
        }
        webContext.response.setBody(JSON.toJSONString(reports));
    }

    /**
     * @apiGroup Course
     * @api {get} /course/register GetCourseRegister
     * @apiPermission student
     * @apiDescription 获取当前学期的选课列表
     * @apiSuccess CourseRegisterViewModel CourseRegisterViewModel对象
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/register");
     * CourseRegisterViewModel courseRegister = res.data(CourseRegisterViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      {
     *          "courseList":[{
     *              "courseID":"2001",
     *              "courseName":"高等数学",
     *              "courseSchedule":[{
     *                  "spanEnd":2,
     *                  "spanStart":1,
     *                  "weekDay":2
     *               },{
     *                  "spanEnd":4,
     *                  "spanStart":3,
     *                  "weekDay":4
     *               }],
     *               "courseVenue":"J8-103",
     *               "credit":"5",
     *               "profName":"牛顿",
     *               "status":"SELECTED",
     *               "stuAttendCount":2,
     *               "stuLimitCount":40
     *           },{
     *              "courseID":"3001",
     *              "courseName":"大学物理",
     *              "courseSchedule":[{
     *                  "spanEnd":4,
     *                  "spanStart":3,
     *                  "weekDay":1
     *              },{
     *                  "spanEnd":7,
     *                  "spanStart":6,
     *                  "weekDay":3
     *              }],
     *              "courseVenue":"J6-101",
     *              "credit":"3",
     *              "profName":"亚里士多德",
     *              "status":"SELECTED",
     *              "stuAttendCount":2,
     *              "stuLimitCount":40
     *          },{
     *              "courseID":"5001",
     *              "courseName":"中国古建筑鉴赏",
     *              "courseSchedule":[{
     *                  "spanEnd":13,
     *                  "spanStart":11,
     *                  "weekDay":1
     *              },{
     *                  "spanEnd":5,
     *                  "spanStart":3,
     *                  "weekDay":3
     *              }],
     *                  "courseVenue":"J6-102",
     *                  "credit":"2",
     *                  "profName":"梁思成",
     *                  "status":"AVAILABLE",
     *                  "stuAttendCount":1,
     *                  "stuLimitCount":60
     *              },{
     *                  "courseID":"5002",
     *                  "courseName":"果壳中的宇宙",
     *                  "courseSchedule":[{
     *                      "spanEnd":13,
     *                      "spanStart":11,
     *                      "weekDay":4
     *                  }],
     *                  "courseVenue":"J6-103",
     *                  "credit":"2","profName":"霍金",
     *                  "status":"AVAILABLE",
     *                  "stuAttendCount":0,
     *                  "stuLimitCount":60
     *              },{
     *                  "courseID":"5003",
     *                  "courseName":"时装艺术鉴赏",
     *                  "courseSchedule":[{
     *                      "spanEnd":5,
     *                      "spanStart":3,
     *                      "weekDay":4
     *                  }],
     *                  "courseVenue":"J3-203",
     *                  "credit":"2",
     *                  "profName":"哥斯拉",
     *                  "status":"CONFLICT",
     *                  "stuAttendCount":0,
     *                  "stuLimitCount":60
     *              }
     *          ]}
     *      }
     */
    @Get(route = "register")
    public void registerCourseList() {
        String campusCardId = webContext.session.getString("campusCardId");
        Semester semester = new Semester().next(); // 默认查找下一学期
        Transaction tx = dbSession.beginTransaction();
        StuInfoEntity stuInfo = dbSession.get(StuInfoEntity.class, campusCardId);

        Query<CourseEntity> courseQuery = dbSession.createQuery(
            "select cr from CourseEntity cr where cr.semester = ?1 " +
            "and (cr.grade = 0 or (cr.grade = ?2 and (cr.major = ?3 or cr.major is null)))"
        ).setParameter(1, String.valueOf(semester.n))
        .setParameter(2, semester.yy - (stuInfo.getEnrollmentYear() - 2000) + 1)
        .setParameter(3, stuInfo.getMajor());

        List<CourseEntity> courseList = courseQuery.list();

        Map<String, CourseRegisterViewModel> courseMap = courseList.stream()
            .map(course -> new CourseRegisterViewModel((CourseEntity) course))
            .collect(Collectors.toMap(CourseRegisterViewModel::getCourseID, Function.identity()));

        boolean isSelectedTable[][] = new boolean[8][14];
        List<CourseRegisterViewModel> occupyTable[][] = new ArrayList[8][14];
        for (int i = 0; i < 8; ++i)
        for (int j = 0; j < 14; ++j) {
            occupyTable[i][j] = new ArrayList<>();
        }
        for (CourseRegisterViewModel course : courseMap.values()) {
            for (CourseScheduleViewModel schedule : course.getCourseSchedule()) {
                for (int period : schedule.span()) {
                    occupyTable[schedule.getWeekDay()][period].add(course);
                }
            }
            if (course.getStuAttendCount() >= course.getStuLimitCount()) {
                course.setStatus(CourseStatusEnum.NOT_AVAILABLE);
            }
        }
        for (CourseRecordEntity record : account.getCourseRecordsByCampusCardId()) {
            CourseEntity selectedCourseEntity = record.getCourseByCourseId();
            if (courseMap.containsKey(selectedCourseEntity.getCourseId())) {
                courseMap.get(selectedCourseEntity.getCourseId()).setStatus(CourseStatusEnum.SELECTED);
            }
            for (CourseScheduleEntity schedule : selectedCourseEntity.getCourseSchedulesByCourseId()) {
                for (int period : schedule.span()) {
                    isSelectedTable[schedule.getWeekDay()][period] = true;
                    for (CourseRegisterViewModel courseViewModel : occupyTable[schedule.getWeekDay()][period]) {
                        if (courseViewModel.getStatus() == CourseStatusEnum.AVAILABLE) {
                            courseViewModel.setStatus(CourseStatusEnum.CONFLICT);
                        }
                    }
                }
            }
        }

        tx.commit();

        webContext.session.setString("isSelectedTable", JSONArray.toJSONString(isSelectedTable));
        webContext.response.setBody(JSON.toJSONString(courseMap.values()));
    }

    /**
     * @apiGroup Course
     * @api {post} /course/register RegisterCourse
     * @apiPermission student
     * @apiDescription 选课
     * @apiSuccess 200 -> OK
     * @apiError
     *     403 -> Course is full
     *     403 -> Please view course list first
     *     403 -> Course Conflict
     * @apiParamExample Code Snippets
     * WebResponse res = api.post("/course/register", courseId);
     */
    @Post(route = "register")
    public String registerCourse(@FromBody String courseId) {
        String campusCardId = webContext.session.getString("campusCardId");
        Transaction tx = dbSession.beginTransaction();
        CourseEntity course = dbSession.get(CourseEntity.class, courseId);

        if (course.getStuAttendCount() >= course.getStuLimitCount()) {
            webContext.response.setStatusCode("403");
            return "Course is full";
        }

        String tableJSON = webContext.session.getString("isSelectedTable");
        if (tableJSON == null) {
            webContext.response.setStatusCode("403");
            return "Please view course list first";
        }

        boolean isSelectedTable[][] = JSON.parseObject(tableJSON, boolean[][].class);
        for (CourseScheduleEntity schedule : course.getCourseSchedulesByCourseId()){
            for (int period : schedule.span()) {
                if (isSelectedTable[schedule.getWeekDay()][period]) {
                    webContext.response.setStatusCode("403");
                    return "Course conflict";
                } else {
                    // 当所有时间段没有冲突后，isSelectTable也顺便更新完毕。
                    isSelectedTable[schedule.getWeekDay()][period] = true;
                }
            }
        }

        // 正式更新
        CourseRecordEntity record = new CourseRecordEntity();
        course.incStuAttendCount();
        dbSession.update(course);
        record.setCourseByCourseId(course);
        record.setAccountByCampusCardId(account);
        record.setSemester(new Semester().next().toString());
        record.setScore(0);
        record.setScoreType("首修");
        dbSession.save(record);
        tx.commit();

        webContext.session.setString("isSelectedTable", JSONArray.toJSONString(isSelectedTable));
        return "OK";
    }

    /**
     * @apiGroup Course
     * @api {delete} /course/register/{courseId} RegisterCourse
     * @apiPermission student
     * @apiDescription 退课
     * @apiSuccess 200 -> OK
     * @apiError:
     *     400 -> Already empty
     *     403 -> Please view course list first
     *     404 -> Course not selected
     * @apiParamExample Code Snippets
     * WebResponse res = api.delete("/course/register/{courseId}");
     */
    @Delete(route = "register")
    public String unregisterCourse(@FromUrl String courseId) {
        String campusCardId = webContext.session.getString("campusCardId");
        Transaction tx = dbSession.beginTransaction();
        CourseEntity course = dbSession.get(CourseEntity.class, courseId);

        if (course.getStuAttendCount() <= 0) {
            webContext.response.setStatusCode("400");
            return "Already empty";
        }

        String tableJSON = webContext.session.getString("isSelectedTable");
        if (tableJSON == null) {
            webContext.response.setStatusCode("403");
            return "Please view course list first";
        }

        // 正式更新
        CourseRecordEntity record = (CourseRecordEntity) dbSession.createQuery(
            "select cr from CourseRecordEntity cr where cr.accountByCampusCardId.id = ?1 and cr.courseByCourseId.id = ?2"
        ).setParameter(1, campusCardId).setParameter(2, courseId).uniqueResult();
        if (record == null) {
            webContext.response.setStatusCode("404");
            return "Course not selected";
        }

        dbSession.delete(record);
        course.decStuAttendCount();
        dbSession.update(course);
        tx.commit();

        boolean isSelectedTable[][] = JSON.parseObject(tableJSON, boolean[][].class);
        for (CourseScheduleEntity schedule : course.getCourseSchedulesByCourseId()){
            for (int period : schedule.span()) {
                isSelectedTable[schedule.getWeekDay()][period] = false;
            }
        }
        webContext.session.setString("isSelectedTable", JSONArray.toJSONString(isSelectedTable));
        return "OK";
    }

    /**
     * @apiGroup Course
     * @api {get} /course GetCourseList
     * @apiPermission admin
     * @apiDescription 获取本学期开设的课程列表
     * @apiSuccess List_CourseRegisterViewModel List of CourseRegisterViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/list");
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
     *      }]
     * @apiErrorExample Error-Response:
     *     404 "Course not found."
     *
     */
    @Get(route = "list")
    public void getCourseList() {

    }

    /**
     * @apiGroup Course
     * @api {post} /course/new CreateCourse
     * @apiPermission admin
     * @apiDescription 创建课程，需要在Course表和CourseSchedule表里增加条目
     * @apiParamExample Code Snippets
     * WebResponse res = api.post("/course/new", courseRegisterViewModel);
     */
    @Post(route = "new")
    public void createCourse() {

    }

    /**
     * @apiGroup Course
     * @api {patch} /courseSchedule/id/{uuid} ModifyCourseSchedule
     * @apiPermission teacher
     * @apiDescription 修改课程安排
     * @apiParamExample Code Snippets
     * CourseScheduleViewModel schedule = ...
     * 
     * String uuid = schedule.getId();
     * schedule.set...;
     * WebResponse res = api.patch("/course/record/" + uuid, JSON.toJSONString(schedule);
     * @apiSuccessExample Success-Response:
     *      200 OK
     */
}
