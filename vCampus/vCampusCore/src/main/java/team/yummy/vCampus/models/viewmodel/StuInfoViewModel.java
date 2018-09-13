package team.yummy.vCampus.models.viewmodel;

import com.alibaba.fastjson.annotation.JSONField;
import team.yummy.vCampus.models.entity.AccountEntity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 学生信息的视图模型类，用于前端展示学生信息
 * @author Serica
 * @author Vigilans
 */
public class StuInfoViewModel {
    private String campusCardId = "N/A";
    private String seniorHigh = "N/A";
    private String birthplace = "N/A";
    private String sex = "N/A";
    private String studentId = "N/A";
    private String department = "N/A";
    private String major = "N/A";
    private String idNum = "N/A";
    private String address = "N/A";
    @JSONField(format = "yyyy-mm-dd")
    private Timestamp birthdate = new Timestamp(0);
    private String phone = "N/A";
    private Integer enrollmentYear = 1970;
    private Integer lectureAttendCount = 0;
    private Double srtp = 0.0;
    private Double gpa = 0.0;
    private String email = "N/A";

    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }

    public String getSeniorHigh() {
        return seniorHigh;
    }

    public void setSeniorHigh(String seniorHigh) {
        this.seniorHigh = seniorHigh;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public Integer getLectureAttendCount() {
        return lectureAttendCount;
    }

    public void setLectureAttendCount(Integer lectureAttendCount) {
        this.lectureAttendCount = lectureAttendCount;
    }

    public Double getSrtp() {
        return srtp;
    }

    public void setSrtp(Double srtp) {
        this.srtp = srtp;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
