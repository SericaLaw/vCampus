package team.yummy.vCampus.server.middlewares;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.server.Server;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.web.WebResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RoutingMiddleware implements Middleware {
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
                    String jsonData = dbhelper.select(ctx.request.getTableName(), ctx.request.getKey(), ctx.request.getValue());
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
                    boolean updateSuc = dbhelper.update(ctx.request.getTableName(), ctx.request.getKey(), ctx.request.getValue(), ctx.request.getJsonData());
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
                            ctx.session.setString("username", resData.get("Username"));
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
                    boolean deleteSuc = dbhelper.delete(ctx.request.getTableName(), ctx.request.getKey(), ctx.request.getValue());
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
