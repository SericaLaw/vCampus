package com.serica.demo.client;
import com.alibaba.fastjson.JSON;
import com.serica.demo.models.*;
import com.serica.demo.server.api.API;
import com.serica.demo.server.http.HttpResponse;

import java.util.*;

public class MVCPatternDemo {
    public static void main(String[] args) {

        // 从后端数据库获取学生记录
        Student model  = retriveStudentFromDatabase();

        // 创建一个视图：把学生详细信息输出到控制台
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        //更新模型数据
        controller.setStudentName("John");

        controller.updateView();
    }

    private static Student retriveStudentFromDatabase(){
        // 这个API返回服务器的响应，返回HttpResponse, 使用其中的data方法可以获取查询到的单个数据
        Student student = API.Get("/student/101").data(Student.class);
        // 有时候，查询有多个结果，返回的是一个数组，这时候我们可以用 dataList方法
//        List<Student> studentList = API.Get("/student/101").dataList(Student.class);
        return student;
    }
}