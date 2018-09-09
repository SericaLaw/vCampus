package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.server.api.Controller;
import team.yummy.vCampus.server.Server;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.api.*;
import team.yummy.vCampus.util.Logger;

import java.net.URI;

public class RoutingMiddleware implements Middleware {
    private Logger logger = new Logger("RoutingMiddleware");

    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {
        try {

            Controller controller = null;

            switch (ctx.request.getTableName().toLowerCase()) {
                case "account":
                    controller = new AccountController(); break;
                case "course":
                    controller = new CourseController(); break;
                case "library":
                    controller = new LibraryController(); break;
                case "stuinfo":
                    controller = new StuInfoController(); break;
            }

            if (controller != null) {
                // 设置Controller上下文。
                controller.init(ctx);

                // 运行Controller，若成功，则response将不为null。
                controller.run();
            }

            // 如果data仍为null且状态码不为500，说明路由失败
            if (ctx.response.getStatusCode() == null && ctx.response.getStatusCode() != "500") {
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
                        ctx.response.setBody(jsonData);
                        ctx.response.setMessage("OK");
                    }
                }
                case PATCH: {
                    boolean success = dbhelper.update(
                            ctx.request.getTableName(),
                            ctx.request.getField(),
                            ctx.request.getValue(),
                            ctx.request.getBody()
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
                            ctx.request.getBody()
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
