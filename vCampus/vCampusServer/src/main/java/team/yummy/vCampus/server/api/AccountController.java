package team.yummy.vCampus.server.api;

import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.AccountEntity;
import team.yummy.vCampus.models.viewmodel.LoginViewModel;
import team.yummy.vCampus.server.annotation.Post;


public class AccountController extends Controller {

    @Post(route = "login")
    public void login() {
        LoginViewModel login = webContext.request.deserializeBody(LoginViewModel.class);
        Transaction tx = dbSession.beginTransaction();
        AccountEntity account = dbSession.get(AccountEntity.class, login.getCampusCardId());
        tx.commit();
        if (account == null) {
            webContext.response.setStatusCode("404");
            webContext.response.setMessage("Account not found.");
        } else if (account.getPassword().equals(login.getPassword())) {
            webContext.response.setStatusCode("200");
            webContext.response.setMessage("OK");
            // TODO: 如有必要，这里可以更新更多内容到Session，如Role
            webContext.session.setString("campusCardId", login.getCampusCardId());
            logger.log(String.format("Session [ sessionId = %s, username = %s, password = %s ]", webContext.session.getSessionId().toString(), webContext.session.getString("username"), webContext.session.getString("password")));
        } else {
            webContext.response.setStatusCode("403");
            webContext.response.setMessage("Wrong password.");
        }
    }

}
