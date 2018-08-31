package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.server.Session;
import team.yummy.vCampus.server.WebContext;

import java.sql.Timestamp;
import java.util.Random;

public class SessionMiddleware implements Middleware {
    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {
        if (ctx.request.getSessionId() != null) {
            ctx.session = ctx.server.sessions.get(ctx.request.getSessionId());
        }
        if (ctx.session == null) {
            // 创建一个新的会话
            Integer id = new Random().nextInt();
            ctx.session = new Session(id, new Timestamp(60 * 1000));
            ctx.server.sessions.put(id, ctx.session);
            ctx.response.setSessionId(id);
        }
        next.invoke();
    }
}
