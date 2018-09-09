package team.yummy.vCampus.web;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class WebResponse implements Serializable {
    private Integer sessionId;
    private String body;
    private String statusCode;
    private String message;

    // default response
    public WebResponse() {
        body = null;
        statusCode = null;
        message = null;
        sessionId = null;
    }

    public WebResponse(String statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
        message = "success";
    }

    public WebResponse(String statusCode, String body, String message) {
        this.statusCode = statusCode;
        this.body = body;
        this.message = message;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getBody() { return body; }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }


    public <T> T data(Class<T> clazz) {
        String jsonData = getBody();
        // 将字符串转为T类
        T parsedData = JSON.parseObject(jsonData, clazz);
        return parsedData;
    }

    public <T> List<T>  dataList(Class<T> clazz) {
        String jsonData = getBody();
        // 将字符串转为 T 类
        List<T> list = JSON.parseArray(jsonData, clazz);
        return list;
    }

    public <T> T  dataList(Class<T> clazz, int pos) {
        String jsonData = getBody();
        List<T> list = JSON.parseArray(jsonData, clazz);
        return list.get(pos);
    }

    @Override
    public String toString() {
        return statusCode + " --> "  + message + "\n[ JSON deserializeBody = " + body + " ]\n[ sessionId = " + sessionId +" ]\n";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof WebResponse) {
            WebResponse another = (WebResponse) obj;
            return this.message.equals(another.message) &&
                    this.statusCode.equals(another.statusCode) &&
                    this.body.equals(another.body);
        }
        return false;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}
