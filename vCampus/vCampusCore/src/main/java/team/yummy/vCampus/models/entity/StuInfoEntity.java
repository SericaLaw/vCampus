package team.yummy.vCampus.models.entity;

import javax.persistence.*;

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
        return result;
    }
}
