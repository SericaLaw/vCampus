package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.AccountEntity;
import team.yummy.vCampus.models.viewmodel.*;
import team.yummy.vCampus.server.annotation.Authorize;
import team.yummy.vCampus.server.annotation.FromBody;
import team.yummy.vCampus.server.annotation.Post;
import team.yummy.vCampus.server.framework.Controller;

public class AccountController extends Controller {

    /**
     * @apiGroup Account
     * @api {get} /account/login Login
     * @apiPermission all
     * @apiDescription 登录
     * @apiSuccess AccountViewModel AccountViewModel
     * @apiParamExample Code Snippets
     * LoginViewModel login = new LoginViewModel(username, password);
     * WebResponse res = api.post("/account/login", JSON.toJSONString(login));
     * @apiSuccessExample Success-Response:
     *     200 OK
     *     {
     *         "CampusCardID": "213180000",
     *         "Username": "client",
     *         "Password": "123",
     *         "FirstName":"Foo",
     *         "LastName": "Bar",
     *         "Role": "student"
     *     }
     * @apiErrorExample Error-Response:
     *     403 "Wrong password."
     *     404 "Account not found."
     *
     */
    @Post(route = "login")
    public void login(@FromBody LoginViewModel login) {
        dbSession.beginTransaction();
        AccountEntity account = dbSession.get(AccountEntity.class, login.getCampusCardId());
        dbSession.getTransaction().commit();
        if (account == null) {
            webContext.response.setStatusCode("404");
            webContext.response.setMessage("Account not found.");
        } else if (account.getPassword().equals(login.getPassword())) {
            webContext.response.setStatusCode("200");
            webContext.response.setMessage("OK");
            webContext.response.setBody(JSON.toJSONString(new AccountViewModel(account)));
            // TODO: 如有必要，这里可以更新更多内容到Session
            webContext.session.setString("campusCardId", account.getCampusCardId());
            webContext.session.setString("role", account.getRole());
            logger.log(String.format("Session [ sessionId = %s, username = %s, password = %s ]",
                webContext.session.getSessionId().toString(),
                webContext.session.getString("username"),
                webContext.session.getString("password")
            ));
        } else {
            webContext.response.setStatusCode("403");
            webContext.response.setMessage("Wrong password.");
        }
    }

    /**
     * @apiGroup Account
     * @api {post} /account CreateAccount
     * @apiDescription 创建账号和学生信息，注意账号的campusCardId和StuInfo表关联，所以必须先创建账号，有初始化的密码和昵称。
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * AccountViewModel newAccount = new AccountViewModel();
     * newAccount.setCampusCardId("213190000");
     * newAccount.setFirstName("新");
     * newAccount.setLastName("生");
     * newAccount.setRole("student");
     * newAccount.setNickname("昵称");
     * newAccount.setPassword("09019000");
     *
     * WebResponse res = api.post("/account", JSON.toJSONString(newAccount));
     *
     * @apiSuccessExample Success-Response:
     *     201 OK
     *
     * @apiErrorExample Error-Response:
     *     403 "Account already exists."
     *
     */
}
