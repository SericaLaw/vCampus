package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.RequestMethod;

/**
 * 鉴权类中间件， 除登陆、注册无需鉴权外，其他请求均需通过此中间件
 * @author Serica
 */
public class AuthMiddleware implements Middleware {
    private Logger logger = new Logger("AuthMiddleware");
    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {

        logger.log(String.format(" session auth [ campusCardId = %s, password = %s ]", ctx.session.getString("campusCardId"), ctx.session.getString("password")));

        // 登录和注册不需要鉴权
        if(ctx.request.getRoute().equals("/account/login") ||
                ctx.request.getRoute().equals("/account") && ctx.request.getType() == RequestMethod.POST) {
            next.invoke();
        }

        else if (ctx.session.getString("campusCardId") == null) {
            ctx.response.setStatusCode("401");
            ctx.response.setMessage("Unauthorized");
        }

        else {
            next.invoke();
        }
    }
}
