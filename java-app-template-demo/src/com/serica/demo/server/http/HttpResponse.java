package com.serica.demo.server.http;

import com.alibaba.fastjson.JSON;
import com.serica.demo.models.Student;

import java.io.Serializable;
import java.util.List;

public class HttpResponse implements Serializable {
    private String data;
    private String statusCode;

    public HttpResponse(String statusCode, String data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getStatusCode() {
        return statusCode;
    }
    // parsed data 针对于 select的结果 且结果数组里只有一个对象的情况
    public <T> T data(Class<T> clazz) {
        String jsonData = getData();
        // 将字符串转为Student类
        List<T> list = JSON.parseArray(jsonData, clazz);
        return list.get(0);
    }
    // parsed data 针对于 select的结果 且结果数组里只有一个对象的情况
    public <T> List<T>  dataList(Class<T> clazz) {
        String jsonData = getData();
        // 将字符串转为Student类
        List<T> list = JSON.parseArray(jsonData, clazz);
        return list;
    }
    @Override
    public String toString() {
        return statusCode + " " + data;
    }
}
