package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.Session;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.api.annotation.*;
import team.yummy.vCampus.util.Logger;
import team.yummy.vCampus.web.WebRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Controller {

    public Logger logger;

    public WebContext webContext;

    public Session dbSession;

    public void init(WebContext webContext) {
        this.logger = new Logger(getClass().getTypeName());
        this.webContext = webContext;
        this.dbSession = webContext.server.dbFactory.openSession();
    }

    public boolean run() {
        try {
            WebRequest request = webContext.request;

            Class methodType = new Class[] {
                Get.class, Post.class, Patch.class, Delete.class
            }[request.getType().ordinal()];

            Method action = null;
            for (Method method : this.getClass().getMethods()) {
                if (method.isAnnotationPresent(methodType)) {
                    String route = (String) methodType.getMethod("route").invoke(method.getAnnotation(methodType));
                    if (route.equals(request.getField().toLowerCase())) {
                        action = method;
                        break;
                    }
                }
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
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            webContext.response.setStatusCode("500");
            webContext.response.setMessage(e.toString());
            return false;
        } finally {
            dbSession.close();
        }
    }
}
