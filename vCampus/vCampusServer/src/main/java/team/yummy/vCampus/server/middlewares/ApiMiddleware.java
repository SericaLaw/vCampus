package team.yummy.vCampus.server.middlewares;

import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.server.framework.Middleware;
import team.yummy.vCampus.server.framework.WebContext;

/**
 * Api中间件，为最后的执行中间件。
 */
public class ApiMiddleware implements Middleware {

    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {
        try {
            // 若已绑定controller，则尝试使用controller执行Api。
            if (ctx.controller != null) {
                ctx.controller.run(ctx);
            }

            // 如果状态码没有设置过，则路由或执行失败
            if (ctx.response.getStatusCode() == null) {
                // 使用Method的默认方法处理
                this.runDefault(ctx);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
