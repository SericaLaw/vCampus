package com.serica.demo.client;
import com.serica.demo.models.*;

/**
 * 控制层，连接视图层和数据层；视图层的交互信息通过controller传递给model
 */
public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name){
        model.setStudentName(name);
    }

    public String getStudentName(){
        return model.getStudentName();
    }

    public void setStudentID(String studentID){
        model.setStudentID(studentID);
    }

    public String getStudentID(){
        return model.getStudentID();
    }

    public void updateView(){
        view.printStudentDetails(model.getStudentName(), model.getStudentID());
    }
}