package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.server.annotation.Authorize;
import team.yummy.vCampus.server.framework.Middleware;
import team.yummy.vCampus.server.framework.WebContext;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.RequestMethod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 鉴权类中间件
 */
public class AuthMiddleware implements Middleware {

    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {

        if (ctx.controller != null) {
            boolean authorized = true;

            // 检查controller级鉴权
            if (!isRoleAuthorized(ctx, ctx.controller.getClass())) {
                authorized = false;
            }

            // 检查method级鉴权
            if (ctx.controller.action != null &&
                !isRoleAuthorized(ctx, ctx.controller.action.getClass())) {
                authorized = false;
            }

            if (authorized) {
                next.invoke();
            } else {
                ctx.response.setStatusCode("401");
                ctx.response.setMessage("Unauthorized");
            }
        } else {
            // 按补充的路由表检查鉴权
            String route = "/";
            if (ctx.request.getField() != null) {
                route = String.format("/%s/%s", ctx.request.getTableName(), ctx.request.getField());
            } else if (ctx.request.getTableName() != null) {
                route = String.format("/%s", ctx.request.getTableName());
            }

            Map<String, Boolean> authMap = new HashMap<String, Boolean>();
            authMap.put("/", true);
            authMap.put("/stuinfo", false);
            if (authMap.containsKey(route) && authMap.get(route)) {
                next.invoke();
            } else {
                ctx.response.setStatusCode("401");
                ctx.response.setMessage("Unauthorized");
            }
        }

    }

    private boolean isRoleAuthorized(WebContext ctx, Class<?> clazz) {
        if (clazz.isAnnotationPresent(Authorize.class)) {

            Authorize auth = clazz.getAnnotation(Authorize.class);

            // 若@Authorize未指定任何roles，只需检测是否登录
            if (auth.roles().length == 0) {
                return ctx.session.getString("campusCardId") != null;
            }

            // 否则，检查session中roles是否匹配
            String role = ctx.session.getString("role");
            for (String auth_role : auth.roles()) {
                if (auth_role.equals(role)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
