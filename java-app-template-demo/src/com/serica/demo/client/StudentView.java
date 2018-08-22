package com.serica.demo.client;

/**
 * 视图层，只关心数据展示，不关心数据操作
 */
public class StudentView {
    public void printStudentDetails(String studentName, String studentID){
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentID);
    }
}