package team.yummy.vCampus.server.middlewares;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.models.*;
import team.yummy.vCampus.models.entity.Schedule;
import team.yummy.vCampus.models.entity.StuInfo;
import team.yummy.vCampus.models.entity.CourseRecord;
import team.yummy.vCampus.models.viewmodel.CourseRegisterViewModel;
import team.yummy.vCampus.server.Server;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.api.*;
import team.yummy.vCampus.util.Logger;

import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class RoutingMiddleware implements Middleware {
    private Logger logger = new Logger("RoutingMiddleware");

    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {
        try {

            Controller controller = null;

            switch (ctx.request.getTableName()) {
                case "Login":
                    controller = new AccountController(); break;
                case "Course":
                    controller = new CourseController(); break;
                case "Library":
            }

            // 设置Controller上下文。
            controller.init(ctx);

            // 运行Controller，若成功，则response将不为null。
            controller.run();

            // 如果data仍为null，说明路由失败
            if (ctx.response.getJsonData() == null) {
                // 使用Method的默认方法处理
                URI db_uri = Server.class.getClassLoader().getResource("test_database.accdb").toURI();
                ctx.logger.log("Database dir: " + db_uri.toString());
                DBHelper dbhelper = new DBHelper(db_uri);
                switch (ctx.request.getType()) {
                case GET: {
                    // 精准查询 ~/stuInfo/campusCardID/213170000，注意这里返回的全是数组形式的JSON数据！
                    String jsonData = dbhelper.select(
                            ctx.request.getTableName(),
                            ctx.request.getField(),
                            ctx.request.getValue()
                    );
                    if (jsonData == null) {
                        // get不到东西并不是错误，只是因为数据库里没有记录
                        ctx.response.setStatusCode("404");
                        ctx.response.setMessage("Not Found");
                    } else {
                        ctx.response.setStatusCode("200");
                        ctx.response.setJsonData(jsonData);
                        ctx.response.setMessage("OK");
                    }
                }
                case PATCH: {
                    boolean success = dbhelper.update(
                            ctx.request.getTableName(),
                            ctx.request.getField(),
                            ctx.request.getValue(),
                            ctx.request.getJsonData()
                    );
                    if (success) {
                        ctx.response.setStatusCode("200");
                        ctx.response.setMessage("OK");
                    } else {
                        ctx.response.setStatusCode("403");
                        ctx.response.setMessage("update failed");
                    }
                    break;
                }
                case POST: {
                    boolean insertSuc = dbhelper.insert(
                            ctx.request.getTableName(),
                            ctx.request.getJsonData()
                    );
                    if (insertSuc) {
                        // 创建成功，201
                        ctx.response.setStatusCode("201");
                        ctx.response.setMessage("OK");
                    } else {
                        // 创建失败，403，往往是因为已经有创建过的了
                        ctx.response.setStatusCode("403");
                        ctx.response.setMessage(ctx.request.getTableName() + " already exist.");
                    }
//                    if (ctx.request.getTableName().equals("BorrowBook")) {
//                        // 不能借一本图书馆里没有收录的书
//                        ctx.response.setStatusCode("404");
//                        ctx.response.setMessage("BorrowBook not found.");
//                    } else {
//
//                    }
                    }
                case DELETE: {
                    // DELETE ~stuInfo/campusCardID/:id
                    boolean success = dbhelper.delete(
                            ctx.request.getTableName(),
                            ctx.request.getField(),
                            ctx.request.getValue()
                    );
                    if (success) {
                        ctx.response.setStatusCode("204");
                    } else {
                        ctx.response.setStatusCode("404");
                        ctx.response.setMessage(ctx.request.getTableName() + " not found.");
                    }
                }
                }
            }


            switch (ctx.request.getType()) {
                case GET: {

                    if (ctx.request.getRoute().equals("/course/register")) {
                        // 先得到年级信息
                        String campusCardID = ctx.session.getString("campusCardID");

                        jsonData = dbhelper.select("StuInfo", "CampusCardID", campusCardID);

                        List<StuInfo> stuInfos = JSON.parseArray(jsonData, StuInfo.class);
                        StuInfo stuInfo = stuInfos.get(0);

                        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                        int grade = Integer.valueOf(year) - Integer.valueOf(stuInfo.getEnrollmentYear()) + 1;
                        String major = stuInfo.getMajor();
                        // 得到所有可选的课程
                        CourseRegister courseRegister = new CourseRegister();
                        // HARD CODE: 下面semester写死
                        // TODO: 可以考虑配置当前学期信息
                        jsonData = dbhelper.select(courseRegister.getSql(grade, 2, major));
                        List<CourseRegisterViewModel> courseRegisterViewModels = JSON.parseArray(jsonData, CourseRegisterViewModel.class);
                        // 分别得到每个课程的课程表
                        for (CourseRegisterViewModel c : courseRegisterViewModels) {
                            jsonData = dbhelper.select(c.getSql());
                            List<Schedule> s = JSON.parseArray(jsonData, Schedule.class);
                            c.setCourseSchedule(s);
                        }
                        // 设置所有可选的课程
                        courseRegister.setCourseList(courseRegisterViewModels);
                        // 设置课程状态为：已选，已满，可选，可选但与已选冲突；已选课程需要从对应学期课程表中获取
                        String sql = String.format("SELECT CourseID From CourseRecord WHERE CampusCardID = '%s' AND Semester='18-19-2'", campusCardID);
                        jsonData = dbhelper.select(sql);
                        List<Map> selectedCourse = JSON.parseArray(jsonData, Map.class);
                        courseRegister.setCourseStatus(selectedCourse);
                        jsonData = JSON.toJSONString(courseRegister);
                    } else if (ctx.request.getQuery() != null && ctx.request.getQuery().equals("Like"))
                        // 形如 ~/book/bookName/机器/like 模糊查询
                        jsonData = dbhelper.search(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue());
                    else
                        // 精准查询 ~/stuInfo/campusCardID/213170000，注意这里返回的全是数组形式的JSON数据！
                        jsonData = dbhelper.select(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue());

                    if (jsonData == null) {
                        // TODO: 这条实际上无效，get不到东西并不是错误，只是因为数据库里没有记录
                        ctx.response.setStatusCode("404");
                        ctx.response.setMessage("Not Found");
                    } else {
                        ctx.response.setStatusCode("200");
                        ctx.response.setJsonData(jsonData);
                        ctx.response.setMessage("OK");
                    }
                    break;
                }
                case PATCH: {
                    boolean updateSuc = dbhelper.update(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue(), ctx.request.getJsonData());
                    if (updateSuc) {
                        ctx.response.setStatusCode("200");
                        ctx.response.setMessage("OK");
                    } else {
                        ctx.response.setStatusCode("403");
                        ctx.response.setMessage("update failed");
                    }
                    break;
                }
                case POST: {
                    if (ctx.request.getRoute().equals("/course/register")) {
                        String courseID = ctx.request.getJsonData();
                        String jsonData = dbhelper.selectOne(String.format("SELECT StuAttendCount, StuLimitCount FROM Course WHERE CourseID = '%s'", courseID));
                        Map<String, String> m = JSON.parseObject(jsonData, Map.class);
                        int stuAttendCount = Integer.valueOf(m.get("StuAttendCount"));
                        int stuLimitCount = Integer.valueOf(m.get("StuLimitCount"));
                        if (stuAttendCount >= stuLimitCount) {
                            ctx.response.setStatusCode("403");
                            ctx.response.setMessage("Course is not available.");
                        } else {
                            // HARD CODE: semester
                            // TODO: 可以在此更换semester
                            CourseRecord courseRecord = new CourseRecord(courseID, ctx.session.getString("campusCardID"), "0", "18-19-2", "首修");
                            dbhelper.insert("CourseRecord", JSON.toJSONString(courseRecord));

                            // 返回新的选课信息
                            // TODO: 可以重构一下代码
                            String campusCardID = ctx.session.getString("campusCardID");

                            jsonData = dbhelper.select("StuInfo", "CampusCardID", campusCardID);

                            List<StuInfo> stuInfos = JSON.parseArray(jsonData, StuInfo.class);
                            StuInfo stuInfo = stuInfos.get(0);

                            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                            int grade = Integer.valueOf(year) - Integer.valueOf(stuInfo.getEnrollmentYear()) + 1;
                            String major = stuInfo.getMajor();
                            // 得到所有可选的课程
                            CourseRegister courseRegister = new CourseRegister();
                            // HARD CODE: 下面semester写死
                            // TODO: 可以考虑配置当前学期信息
                                    jsonData = dbhelper.select(courseRegister.getSql(grade, 2, major));
                            List<CourseRegisterViewModel> courseRegisterViewModels = JSON.parseArray(jsonData, CourseRegisterViewModel.class);
                            // 分别得到每个课程的课程表
                            for (CourseRegisterViewModel c : courseRegisterViewModels) {
                                jsonData = dbhelper.select(c.getSql());
                                List<Schedule> s = JSON.parseArray(jsonData, Schedule.class);
                                c.setCourseSchedule(s);
                            }
                            // 设置所有可选的课程
                            courseRegister.setCourseList(courseRegisterViewModels);
                            // 设置课程状态为：已选，已满，可选，可选但与已选冲突；已选课程需要从对应学期课程表中获取
                            String sql = String.format("SELECT CourseID From CourseRecord WHERE CampusCardID = '%s' AND Semester='18-19-2'", campusCardID);
                            jsonData = dbhelper.select(sql);
                            List<Map> selectedCourse = JSON.parseArray(jsonData, Map.class);
                            courseRegister.setCourseStatus(selectedCourse);
                            jsonData = JSON.toJSONString(courseRegister);

                            ctx.response.setStatusCode("200");
                            ctx.response.setJsonData(jsonData);
                            ctx.response.setMessage("OK");
                        }

                    }
                    break;
                }

                case DELETE: {
                    boolean deleteSuc;
                    if (ctx.request.getQueryValue() != null) {
                        // DELETE ~borrowBook/{bookID}/{campusCardID}
                        deleteSuc = dbhelper.delete(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue(), ctx.request.getQuery(), ctx.request.getQueryValue());
                        if (ctx.request.getTableName().equals("CourseRecord")) {
                            // 如果是退课，则还要返回新的选课信息
                            // 返回新的选课信息
                            // TODO: 可以重构一下代码
                            String campusCardID = ctx.session.getString("campusCardID");

                            String jsonData = dbhelper.select("StuInfo", "CampusCardID", campusCardID);

                            List<StuInfo> stuInfos = JSON.parseArray(jsonData, StuInfo.class);
                            StuInfo stuInfo = stuInfos.get(0);

                            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                            int grade = Integer.valueOf(year) - Integer.valueOf(stuInfo.getEnrollmentYear()) + 1;
                            String major = stuInfo.getMajor();
                            // 得到所有可选的课程
                            CourseRegister courseRegister = new CourseRegister();
                            // HARD CODE: 下面semester写死
                            // TODO: 可以考虑配置当前学期信息
                            jsonData = dbhelper.select(courseRegister.getSql(grade, 2, major));
                            List<CourseRegisterViewModel> courseRegisterViewModels = JSON.parseArray(jsonData, CourseRegisterViewModel.class);
                            // 分别得到每个课程的课程表
                            for (CourseRegisterViewModel c : courseRegisterViewModels) {
                                jsonData = dbhelper.select(c.getSql());
                                List<Schedule> s = JSON.parseArray(jsonData, Schedule.class);
                                c.setCourseSchedule(s);
                            }
                            // 设置所有可选的课程
                            courseRegister.setCourseList(courseRegisterViewModels);
                            // 设置课程状态为：已选，已满，可选，可选但与已选冲突；已选课程需要从对应学期课程表中获取
                            String sql = String.format("SELECT CourseID From CourseRecord WHERE CampusCardID = '%s' AND Semester='18-19-2'", campusCardID);
                            jsonData = dbhelper.select(sql);
                            List<Map> selectedCourse = JSON.parseArray(jsonData, Map.class);
                            courseRegister.setCourseStatus(selectedCourse);
                            jsonData = JSON.toJSONString(courseRegister);

                            ctx.response.setStatusCode("200");
                            ctx.response.setJsonData(jsonData);
                            ctx.response.setMessage("OK");
                        }
                    } else

                    break;
                }
                default:
                    ctx.response.setStatusCode("404");
                    ctx.response.setMessage("Request Url Not Found: " + ctx.request.getRoute());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
