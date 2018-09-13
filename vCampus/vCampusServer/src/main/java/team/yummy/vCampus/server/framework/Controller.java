package team.yummy.vCampus.server.framework;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.hamcrest.internal.ArrayIterator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.AccountEntity;
import team.yummy.vCampus.server.annotation.*;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.WebRequest;

import javax.persistence.criteria.From;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;


public class Controller {

    public Logger logger;

    public WebContext webContext;

    public Method action;

    public ArrayList<Object> arguments;

    public Session dbSession;

    public boolean isModelValid;

    public AccountEntity account;

    public boolean run(WebContext webContext) {
        // 路由失败
        if (action == null) {
            return false;
        }

        try {
            // 初始化上下文
            this.init(webContext);

            // 解析请求参数
            this.parseArguments();

            // 若要自定义响应码，则应在action内重写
            Object result = action.invoke(this, arguments.toArray());

            // 解析处理结果
            this.parseResult(result);

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

    private void init(WebContext webContext) {
        this.logger = new Logger(getClass().getTypeName());
        this.webContext = webContext;
        this.dbSession = webContext.server.dbFactory.openSession();
        this.arguments = new ArrayList<>();
        this.isModelValid = true;
        String campusCardId = webContext.session.getString("campusCardId");
        if (campusCardId != null) {
            Transaction tx = dbSession.beginTransaction();
            account = dbSession.get(AccountEntity.class, campusCardId);
            tx.commit();
        }
    }

    private void parseArguments() {
        WebRequest request = webContext.request;

        Iterator fromUrls = new ArrayIterator(new String[] { request.getValue(), request.getQuery(), request.getQueryValue() });

        Iterator fromBody = new ArrayIterator(new String[] { request.getBody() });

        for (Parameter param : action.getParameters()) {
            Class<?> paramType = param.getType();
            Object argument = null;
            if (fromBody.hasNext() && param.isAnnotationPresent(FromBody.class)) {
                String body = (String) fromBody.next();
                if (paramType.isAssignableFrom(String.class)) {
                    argument = body;
                } else if (body.charAt(0) == '{') {
                    argument = JSON.parseObject(body, paramType);
                } else if (body.charAt(0) == '[') {
                    Class elemType = param.getAnnotation(FromBody.class).value();
                    argument = JSON.parseArray(body, elemType);
                } else {
                    argument = body;
                    isModelValid = false;
                }
            } else if (fromUrls.hasNext() && (param.isAnnotationPresent(FromUrl.class) || param.getAnnotations().length == 0)) {
                if (paramType.isAssignableFrom(String.class)) {
                    argument = fromUrls.next();
                } else if (paramType.isAssignableFrom(Integer.class)) {
                    argument = Integer.valueOf((String) fromUrls.next());
                } else if (paramType.isAssignableFrom(Double.class)) {
                    argument = Double.valueOf((String) fromUrls.next());
                } else if (paramType.isAssignableFrom(Boolean.class)) {
                    argument = Boolean.valueOf((String) fromUrls.next());
                } else {
                    isModelValid = false;
                }
            }
            arguments.add(argument);
        }
    }

    private void parseResult(Object result) {
        if (result != null) {
            webContext.response.setMessage((String)result);
        }
        if (webContext.response.getMessage() == null) {
            webContext.response.setMessage("OK");
        }
        if (webContext.response.getStatusCode() == null) {
            webContext.response.setStatusCode("200");
        }
    }
}
