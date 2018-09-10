import org.junit.Test;
import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.models.CourseRegister;
import team.yummy.vCampus.models.CourseReportItem;
import team.yummy.vCampus.models.CourseScheduleItem;
import team.yummy.vCampus.web.WebResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseApiTest extends ApiTest{

    /**
     * @apiGroup Course
     * @api {get} /course/schedule GetCourseSchedule ( passed )
     * @apiPermission student
     * @apiDescription 获取本学期课程表，暂不支持不同学期课程表的查�?
     * @apiSuccess List_CourseScheduleItem 装着课程表表项的list
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/schedule");
     * List<CourseScheduleItem> scheduleItems = res.dataList(CourseScheduleItem.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      [
     *          {
     *              "SpanEnd":"2",
     *              "ProfName":"牛顿",
     *              "CourseID":"2001",
     *              "CourseName":"高等数学",
     *              "CourseVenue":"J8-103",
     *              "WeekDay":"2",
     *              "SpanStart":"1"
     *          },
     *          {
     *              "SpanEnd":"4",
     *              "ProfName":"牛顿",
     *              "CourseID":"2001",
     *              "CourseName":"高等数学",
     *              "CourseVenue":"J8-103",
     *              "WeekDay":"4",
     *              "SpanStart":"3"
     *          },
     *          {
     *              "SpanEnd":"4",
     *              "ProfName":"亚里士多�?",
     *              "CourseID":"3001",
     *              "CourseName":"大学物理",
     *              "CourseVenue":"J6-101",
     *              "WeekDay":"1",
     *              "SpanStart":"3"
     *          },
     *          {
     *              "SpanEnd":"7",
     *              "ProfName":"亚里士多�?",
     *              "CourseID":"3001",
     *              "CourseName":"大学物理",
     *              "CourseVenue":"J6-101",
     *              "WeekDay":"3",
     *              "SpanStart":"6"
     *          }
     *      ]
     */
    @Test
    public void getCourseSchedule() {
        Api api = loginAndGetAuth();

        WebResponse res = api.get("/course/schedule");
        List<CourseScheduleItem> scheduleItems = res.dataList(CourseScheduleItem.class);
    }



    /**
     * @apiGroup Course
     * @api {get} /course/report GetCourseReport ( passed )
     * @apiPermission student
     * @apiDescription 获取成绩信息，直接返回所有学期的成绩信息，不做按学期的单独查询；默认0分为未给分的课程，不会包含在返回的列表里
     * @apiSuccess List_CourseReportItem 装着课程表表项的list
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/report");
     * List<CourseReportItem> reportItems = res.dataList(CourseReportItem.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      [
     *          {
     *              "Semester":"17-18-3",
     *              "Credit":"2",
     *              "CourseName":"中国古建筑鉴�?",
     *              "ScoreType":"首修",
     *              "Score":"90"
     *          }
     *      ]
     */
    @Test
    public void getCourseReport() {
        Api api = loginAndGetAuth();

        WebResponse res = api.get("/course/report");
        List<CourseReportItem> reportItems = res.dataList(CourseReportItem.class);
    }


    /**
     * @apiGroup Course
     * @api {get} /course/register GetCourseRegister ( passed )
     * @apiPermission student
     * @apiDescription 获取当前学期的�?�课列表
     * @apiSuccess CourseRegister CourseRegister对象
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/course/register");
     * CourseRegister courseRegister = res.data(CourseRegister.class);
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
     *              "profName":"亚里士多�?",
     *              "status":"SELECTED",
     *              "stuAttendCount":2,
     *              "stuLimitCount":40
     *          },{
     *              "courseID":"5001",
     *              "courseName":"中国古建筑鉴�?",
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
     *                  "profName":"梁�?�成",
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
     *                  "profName":"哥斯�?",
     *                  "status":"CONFLICT",
     *                  "stuAttendCount":0,
     *                  "stuLimitCount":60
     *              }
     *          ]}
     *      }
     */
    @Test
    public void getCourseRegister() {
        Api api = loginAndGetAuth();

        WebResponse res = api.get("/course/register");
        CourseRegister courseRegister = res.data(CourseRegister.class);

    }

    /**
     * 选课
     * 这个api的data只要传courseID就好了；但前端必须保证不可�?�的课不会被传进来orz
     * 如果选课时已经满了，则返�?403
     * TODO: 选了课之后，Course表不能相应增加人�?
     */
    @Test
    public void registerCourse() {
        Api api = loginAndGetAuth();

        WebResponse res = api.post("/course/register","5002");
        CourseRegister r = res.data(CourseRegister.class);
    }

    /**
     * �?�?
     * TODO: �?课后，Course表不能相应减去人�?...
     */
    @Test
    public void quitCourse() {
        Api api = loginAndGetAuth();

        WebResponse res = api.delete("/courseRecord/courseID/5002/campusCardID/213170000");
        CourseRegister r = res.data(CourseRegister.class);
    }
}
