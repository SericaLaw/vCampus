package team.yummy.vCampus.utils.http;


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


    public <T> T data(Class<T> clazz) {
        String jsonData = getJsonData();
        // 将字符串转为T类
        T parsedData = JSON.parseObject(jsonData, clazz);
        return parsedData;
    }

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
