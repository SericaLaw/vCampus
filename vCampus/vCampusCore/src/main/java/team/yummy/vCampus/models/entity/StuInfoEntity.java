package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "StuInfo", schema = "\".\"", catalog = "\".\"")
public class StuInfoEntity {
    private String campusCardId;
    private String seniorHigh;
    private String birthplace;
    private String sex;
    private String studentId;
    private String department;
    private String major;
    private String idNum;
    private String address;
    private Timestamp birthdate;
    private String phone;
    private Integer enrollmentYear;
    private Integer lectureAttendCount;
    private Double srtp;
    private Double gpa;
    private String email;
    private AccountEntity accountByCampusCardId;

    @Id
    @Column(name = "CampusCardID")
    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }

    @Basic
    @Column(name = "SeniorHigh")
    public String getSeniorHigh() {
        return seniorHigh;
    }

    public void setSeniorHigh(String seniorHigh) {
        this.seniorHigh = seniorHigh;
    }

    @Basic
    @Column(name = "Birthplace")
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Basic
    @Column(name = "Sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "StudentID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "Department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "Major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "IDNum")
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Birthdate")
    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "EnrollmentYear")
    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    @Basic
    @Column(name = "LectureAttendCount")
    public Integer getLectureAttendCount() {
        return lectureAttendCount;
    }

    public void setLectureAttendCount(Integer lectureAttendCount) {
        this.lectureAttendCount = lectureAttendCount;
    }

    @Basic
    @Column(name = "SRTP")
    public Double getSrtp() {
        return srtp;
    }

    public void setSrtp(Double srtp) {
        this.srtp = srtp;
    }

    @Basic
    @Column(name = "GPA")
    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuInfoEntity that = (StuInfoEntity) o;

        if (campusCardId != null ? !campusCardId.equals(that.campusCardId) : that.campusCardId != null) return false;
        if (seniorHigh != null ? !seniorHigh.equals(that.seniorHigh) : that.seniorHigh != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (idNum != null ? !idNum.equals(that.idNum) : that.idNum != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (enrollmentYear != null ? !enrollmentYear.equals(that.enrollmentYear) : that.enrollmentYear != null)
            return false;
        if (lectureAttendCount != null ? !lectureAttendCount.equals(that.lectureAttendCount) : that.lectureAttendCount != null)
            return false;
        if (srtp != null ? !srtp.equals(that.srtp) : that.srtp != null) return false;
        if (gpa != null ? !gpa.equals(that.gpa) : that.gpa != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = campusCardId != null ? campusCardId.hashCode() : 0;
        result = 31 * result + (seniorHigh != null ? seniorHigh.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (idNum != null ? idNum.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (enrollmentYear != null ? enrollmentYear.hashCode() : 0);
        result = 31 * result + (lectureAttendCount != null ? lectureAttendCount.hashCode() : 0);
        result = 31 * result + (srtp != null ? srtp.hashCode() : 0);
        result = 31 * result + (gpa != null ? gpa.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID", nullable = false)
    public AccountEntity getAccountByCampusCardId() {
        return accountByCampusCardId;
    }

    public void setAccountByCampusCardId(AccountEntity accountByCampusCardId) {
        this.accountByCampusCardId = accountByCampusCardId;
    }
    
}
