package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.server.annotation.Delete;
import team.yummy.vCampus.server.annotation.Get;
import team.yummy.vCampus.server.annotation.Patch;
import team.yummy.vCampus.server.annotation.Post;
import team.yummy.vCampus.server.framework.Controller;
import team.yummy.vCampus.server.api.*;
import team.yummy.vCampus.server.framework.Middleware;
import team.yummy.vCampus.server.framework.WebContext;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.WebRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 路由类中间件
 */
public class RoutingMiddleware implements Middleware {
    private Logger logger = new Logger("RoutingMiddleware");

    /**
     * 运行路由类中间件，定义GET,POST,PATCH,DELETE四类交互方式
     * @param ctx 网络连接上下文
     * @param next 运行下一个中间件
     */
    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {
        try {

            Controller controller = route(ctx.request);

            if (controller != null) {
                // 设置Controller上下文。
                controller.init(ctx);

                // 运行Controller，若成功，则response将不为null。
                controller.run();
            }

            // 如果状态码没有设置过，则路由失败
            if (ctx.response.getStatusCode() == null) {
                // 使用Method的默认方法处理
                this.runDefault(ctx);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Controller route(WebRequest request) {
        try {
            Class ctrl_class = Class.forName(String.format(
                "team.yummy.vCampus.server.api.%sController",
                request.getTableName())
            );

            Controller controller = (Controller) ctrl_class.newInstance();

            Class methodType = new Class[] {
                    Get.class, Post.class, Patch.class, Delete.class
            }[request.getType().ordinal()];

            for (Method method : ctrl_class.getMethods()) {
                if (method.isAnnotationPresent(methodType)) {
                    Annotation annotation = method.getAnnotation(methodType);
                    String field = request.getField();
                    String route = (String) methodType.getMethod("route").invoke(annotation);
                    if ((field == null && route.equals("index")) || route.equals(field.toLowerCase())) {
                        controller.action = method;
                        break;
                    }
                }
            }

            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    void runDefault(WebContext ctx) {
        String url = ctx.server.dbConfig.getProperty("hibernate.connection.url");
        DBHelper dbhelper = new DBHelper(url);
        switch (ctx.request.getType()) {
            case GET: {
                // 范围查询，仅返回前20条记录
                if (ctx.request.getField() == null) {
                    String jsonData = dbhelper.select(ctx.request.getTableName(), 20);
                    if (jsonData == null) {
                        ctx.response.setStatusCode("404");
                        ctx.response.setMessage("Table not found");
                    } else {
                        ctx.response.setStatusCode("200");
                        ctx.response.setBody(jsonData);
                        ctx.response.setMessage("OK");
                    }
                    break;
                }
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
                break;
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
                break;
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
                break;
            }
        }
    }
}
