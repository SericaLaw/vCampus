package team.yummy.vCampus.server.middlewares;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.server.Server;
import team.yummy.vCampus.server.Session;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.util.Logger;

import java.net.URI;
import java.util.Map;

public class RoutingMiddleware implements Middleware {
    private Logger logger = new Logger("RoutingMiddleware");
    @Override
    public void run(WebContext ctx, WebContext.MiddlewareInvoker next) {
        try {
            // 准备数据库
            URI db_uri = Server.class.getClassLoader().getResource("test_database.accdb").toURI();
            ctx.logger.log("Database dir: " + db_uri.toString());
            DBHelper dbhelper = new DBHelper(db_uri);

            switch (ctx.request.getType()) {
                case GET: {
                    //获取输出流，响应客户端的请求
                    String jsonData = null;

                    if(ctx.request.getField() == null) {
                        // 形如GET ~/book的API，返回数据表前20条记录
                        jsonData = dbhelper.select(ctx.request.getTableName(), 20);
                    }
                    else if(ctx.request.getQuery() != null && ctx.request.getQuery().equals("Like"))
                        // ~/book/bookName/机器/like 模糊查询
                        jsonData = dbhelper.search(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue());
                    else
                        // 精准查询
                        jsonData = dbhelper.select(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue());
                    if (jsonData == null) {
                        ctx.response.setStatusCode("404");
                        ctx.response.setMessage("Not Found");
                    }
                    else {
                        ctx.response.setStatusCode("200");
                        ctx.response.setJsonData(jsonData);
                        ctx.response.setMessage("OK");
                    }
                    break;
                }
                case PATCH: {
                    boolean updateSuc = dbhelper.update(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue(), ctx.request.getJsonData());
                    if (updateSuc) {
                        ctx.response.setStatusCode("200");
                        ctx.response.setMessage("OK");
                    }
                    else {
                        ctx.response.setStatusCode("403");
                        ctx.response.setMessage("update failed");
                    }
                    break;
                }
                case POST: {
                    if (ctx.request.getRoute().equals("/account/login")) {
                        logger.log("/account/login");
                        Map<String, String> requestData = ctx.request.data(Map.class);
                        String jsonData = dbhelper.selectOne("Account", "Username", requestData.get("username"));
                        Map<String, String> resData = JSON.parseObject(jsonData, Map.class);
                        String pwd = resData.get("Password");
                        if (pwd == null) {
                            ctx.response.setStatusCode("404");
                            ctx.response.setMessage("Account not found.");
                        } else if (pwd.equals(requestData.get("password"))) {
                            ctx.response.setStatusCode("200");
                            ctx.response.setJsonData(jsonData);
                            ctx.response.setMessage("OK");
                            // TODO: 如有必要，这里可以更新更多内容到Session，如Role
                            Session sess =  ctx.server.sessions.get(ctx.session.getSessionId());
                            sess.setString("username", resData.get("Username"));
                            sess.setString("password", resData.get("Password"));
                            logger.log(String.format("Session [ sessionId = %s, username = %s, password = %s ]", sess.getSessionId().toString(), sess.getString("username"), sess.getString("password")));
                        } else {
                            ctx.response.setStatusCode("403");
                            ctx.response.setMessage("Wrong password.");
                        }
                    }
                    // 一般形式的POST
                    else {
                        boolean insertSuc = dbhelper.insert(ctx.request.getTableName(), ctx.request.getJsonData());
                        if (insertSuc) {
                            // 创建成功，201
                            ctx.response.setStatusCode("201");
                            ctx.response.setMessage("OK");
                        }

                        else { // 创建失败，403，往往是因为已经有创建过的了
                            ctx.response.setStatusCode("403");
                            ctx.response.setMessage(ctx.request.getTableName() + " already exist.");
                        }
                    }
                    break;
                }

                case DELETE: {
                    boolean deleteSuc;
                    if(ctx.request.getQueryValue() != null)
                        // DELETE ~borrowBook/bookID/:bid/campusCardID/:cid
                        deleteSuc= dbhelper.delete(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue(), ctx.request.getQuery(), ctx.request.getQueryValue());
                    else
                        // DELETE ~stuInfo/campusCardID/:id
                        deleteSuc= dbhelper.delete(ctx.request.getTableName(), ctx.request.getField(), ctx.request.getValue());
                    if (deleteSuc) {
//                        ctx.response = new WebResponse();
                    }
                    else {
                        ctx.response.setStatusCode("404");
                        ctx.response.setMessage(ctx.request.getTableName() + " not found.");
//                        ctx.response = new WebResponse("404", null, ctx.request.getTableName() + " not found.");
                    }
                    break;
                }
                default:
                    ctx.response.setStatusCode("404");
                    ctx.response.setMessage("Request Url Not Found: " + ctx.request.getRoute());
//                    ctx.response = new WebResponse("404", null, "Request Url Not Found: " + ctx.request.getRoute());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
