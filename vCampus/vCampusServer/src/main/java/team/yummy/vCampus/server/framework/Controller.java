package team.yummy.vCampus.server.framework;

import org.hibernate.Session;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.AccountEntity;
import team.yummy.vCampus.server.annotation.*;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.WebRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class Controller {

    public Logger logger;

    public WebContext webContext;

    public Method action;

    public Session dbSession;

    public AccountEntity account;

    public void init(WebContext webContext) {
        this.logger = new Logger(getClass().getTypeName());
        this.webContext = webContext;
        this.dbSession = webContext.server.dbFactory.openSession();
        String campusCardId = webContext.session.getString("campusCardId");
        if (campusCardId != null) {
            Transaction tx = dbSession.beginTransaction();
            account = dbSession.get(AccountEntity.class, campusCardId);
            tx.commit();
        }
    }

    public boolean run() {
        try {
            WebRequest request = webContext.request;

            // 路由失败
            if (action == null) {
                return false;
            }

            ArrayList<String> arguments = new ArrayList<String>();
            if (request.getValue() != null) {
                arguments.add(request.getValue());
            }
            if (request.getQuery() != null) {
                arguments.add(request.getQuery());
            }
            if (request.getQueryValue() != null) {
                arguments.add(request.getQueryValue());
            }
            // 若要自定义响应码，则应在action内重写
            Object result = action.invoke(this, arguments.toArray());
            if (result != null) {
                webContext.response.setMessage((String)result);
            }
            if (webContext.response.getMessage() == null) {
                webContext.response.setMessage("OK");
            }
            if (webContext.response.getStatusCode() == null) {
                webContext.response.setStatusCode("200");
            }
            return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            webContext.response.setStatusCode("500");
            webContext.response.setMessage(e.toString());
            return false;
        } finally {
            dbSession.close();
        }
    }
}
