package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.AccountEntity;
import team.yummy.vCampus.server.api.annotation.Post;


public class AccountController extends Controller {

    @Post(route = "login")
    public void login() {
        String username = requestBody.get("campusCardId");
        String password = requestBody.get("password");
        Transaction tx = dbSession.beginTransaction();
        AccountEntity account = dbSession.get(AccountEntity.class, username);
        tx.commit();
        if (account == null) {
            webContext.response.setStatusCode("404");
            webContext.response.setMessage("Account not found.");
        } else if (account.getPassword().equals(password)) {
            webContext.response.setStatusCode("200");
            webContext.response.setJsonData(JSON.toJSONString(account));
            webContext.response.setMessage("OK");
            // TODO: 如有必要，这里可以更新更多内容到Session，如Role
            webContext.session.setString("username", username);
            webContext.session.setString("password", password);
            webContext.session.setString("campusCardId", username);
            logger.log(String.format("Session [ sessionId = %s, username = %s, password = %s ]", webContext.session.getSessionId().toString(), webContext.session.getString("username"), webContext.session.getString("password")));
        } else {
            webContext.response.setStatusCode("403");
            webContext.response.setMessage("Wrong password.");
        }
    }

}
