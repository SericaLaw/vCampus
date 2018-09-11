package team.yummy.vCampus.server.api;

public class StoreController {
    /**
     * @apiGroup Store
     * @api {get} /store/goods GetGoodsList
     * @apiPermission all
     * @apiDescription 获取商品列表
     * @apiSuccess List_GoodsViewModel GoodsViewModel List
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
}
