package vCampus.server.http;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class HttpResponse implements Serializable {
    private String jsonData = null;
    private String statusCode = null;
    private String message = null;

    public HttpResponse() {}

    public HttpResponse(String statusCode, String jsonData) {
        this.statusCode = statusCode;
        this.jsonData = jsonData;
        message = "success";
    }

    public HttpResponse(String statusCode, String jsonData, String message) {
        this.statusCode = statusCode;
        this.jsonData = jsonData;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getJsonData() {
        return jsonData;
    }

    public String getStatusCode() {
        return statusCode;
    }

    // parsed jsonData 针对于 select的结果 且结果数组里只有一个对象的情况
    public <T> T data(Class<T> clazz) {
        String jsonData = getJsonData();
        // 将字符串转为Student类
        List<T> list = JSON.parseArray(jsonData, clazz);
        return list.get(0);
    }
    // parsed jsonData 针对于 select的结果 且结果数组里只有一个对象的情况
    public <T> List<T>  dataList(Class<T> clazz) {
        String jsonData = getJsonData();
        // 将字符串转为Student类
        List<T> list = JSON.parseArray(jsonData, clazz);
        return list;
    }

    @Override
    public String toString() {
        return statusCode + " --> "  + message + "\n[ JSON data = " + jsonData + " ]\n";
    }
}
