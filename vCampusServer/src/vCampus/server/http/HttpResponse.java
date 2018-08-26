package vCampus.server.http;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class HttpResponse implements Serializable {
    private String jsonData;
    private String statusCode;
    private String message;

    // default response
    public HttpResponse() {
        jsonData = null;
        statusCode = "200";
        message = "OK";
    }

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
    // 目前GET方法返回的都是list，所以下面的写法和HttpRequest不同
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof HttpResponse) {
            HttpResponse another = (HttpResponse) obj;
            return this.message.equals(another.message) &&
                    this.statusCode.equals(another.statusCode) &&
                    this.jsonData.equals(another.jsonData);
        }
        return false;
    }
}
