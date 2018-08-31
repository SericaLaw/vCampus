package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.RequestMethod;

public class AuthenticationMiddleware implements Middleware {
    private Logger logger = new Logger("AuthMiddleware");
    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {

        logger.log(String.format(" request auth [ username = %s, password = %s ]", ctx.request.getAuthString("username"), ctx.request.getAuthString("password")));
        logger.log(String.format(" session data [ username = %s, password = %s ]", ctx.session.getString("username"), ctx.session.getString("password")));

        // 登录和注册不需要鉴权
        if(ctx.request.getRoute().equals("/account/login") ||
                ctx.request.getRoute().equals("/account") && ctx.request.getType() == RequestMethod.POST)
            next.invoke();


        else if (ctx.request.getAuthString("username") == null
                || ctx.request.getAuthString("password") == null) {
            ctx.response.setStatusCode("401");
            ctx.response.setMessage("Unauthorized");
        }


        // 鉴权
        else if(ctx.request.getAuthString("username").equals(ctx.session.getString("username"))
                && ctx.request.getAuthString("password").equals(ctx.session.getString("password")))
            next.invoke();
        else {
            ctx.response.setStatusCode("401");
            ctx.response.setMessage("Unauthorized");

        }
    }
}
