package com.serica.demo.models;

import java.io.Serializable;

public class Student implements Serializable {
    private String studentName;
    private String studentID;
    private String studentScore;

    public Student() {
    }

    ;

    public Student(String name, String id, String score) {
        studentName = name;
        studentID = id;
        studentScore = score;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentScore(String studentScore) {
        this.studentScore = studentScore;
    }


    @Override
    public String toString() {
        return "Student [name=" + studentName + ", id=" + studentID + ", score="
                + studentScore + "]";
    }
}
