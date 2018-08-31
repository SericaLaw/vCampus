package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.server.WebContext;

public interface Middleware {
    void run(WebContext ctx, WebContext.MiddlewareInvoker next);
}