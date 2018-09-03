package team.yummy.vCampus.models;

import java.util.Date;

/**
 * @author Serica
 * StuInfo 类
 * 对应StuInfo表
 * TODO: 这里需要改成和数据库一致的形式
 */
public class StuInfo {
    private String campusCardID = null;
    private String studentID = null;
    private String seniorHigh = null;
    private String IDNum = null;
    private String birthplace = null;
    private String sex = null;
    private String department = null;
    private String major = null;
    private String enrollmentYear;
    private String email;
    private String address;
    private Date birthDate;
    private String SRTP;
    private String lectureAttendCount;
    private String GPA;
    private String phone;


    // 这个空的构造函数是给FastJSON预留的
    public StuInfo() {}
    public StuInfo(String campusCardID, String studentID, String seniorHigh, String IDNum, String  birthplace, SexEnum sex, String department, String major) {
        setCampusCardID(campusCardID);
        setStudentID(studentID);
        setSeniorHigh(seniorHigh);
        setIDNum(IDNum);
        setBirthplace(birthplace);
        setSex(sex.getName());
        setDepartment(department);
        setMajor(major);
    }


    public String getCampusCardID() {
        return campusCardID;
    }

    public void setCampusCardID(String campusCardID) {
        this.campusCardID = campusCardID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSeniorHigh() {
        return seniorHigh;
    }

    public void setSeniorHigh(String seniorHigh) {
        this.seniorHigh = seniorHigh;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
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
        assert sex.equals("男") || sex.equals("女");
        this.sex = sex;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof StuInfo) {
            StuInfo another = (StuInfo) obj;
            return this.campusCardID.equals(another.campusCardID) && this.studentID.equals(another.studentID)
                    && this.major.equals(another.major) && this.sex.equals(another.sex)
                    && this.department.equals(another.department) && this.seniorHigh.equals(another.seniorHigh)
                    && this.IDNum.equals(another.IDNum) && this.birthplace.equals(another.birthplace);
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(String enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getSRTP() {
        return SRTP;
    }

    public void setSRTP(String SRTP) {
        this.SRTP = SRTP;
    }

    public String getLectureAttendCount() {
        return lectureAttendCount;
    }

    public void setLectureAttendCount(String lectureAttendCount) {
        this.lectureAttendCount = lectureAttendCount;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
