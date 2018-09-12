package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.server.WebContext;

/**
 * 中间件的抽象方法集合
 */
public interface Middleware {
    void run(WebContext ctx, WebContext.MiddlewareInvoker next);
}