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
 * @author Vigilans
 * @author Serica
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
            ctx.controller = route(ctx.request);
            next.invoke();
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

}
