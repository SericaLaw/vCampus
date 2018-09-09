package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "Course", schema = "\".\"", catalog = "\".\"")
public class CourseEntity {
    private String profCampusCardId;
    private Integer stuLimitCount;
    private String profName;
    private String courseVenue;
    private Integer stuAttendCount;
    private String courseId;
    private String examVenue;
    private Timestamp examDate;
    private String courseName;
    private String major;
    private String semester;
    private String intro;
    private Integer grade;
    private BigDecimal credit;
    private Collection<CourseRecordEntity> courseRecordsByCourseId;
    private Collection<CourseScheduleEntity> courseSchedulesByCourseId;

    @Basic
    @Column(name = "ProfCampusCardID")
    public String getProfCampusCardId() {
        return profCampusCardId;
    }

    public void setProfCampusCardId(String profCampusCardId) {
        this.profCampusCardId = profCampusCardId;
    }

    @Basic
    @Column(name = "StuLimitCount")
    public Integer getStuLimitCount() {
        return stuLimitCount;
    }

    public void setStuLimitCount(Integer stuLimitCount) {
        this.stuLimitCount = stuLimitCount;
    }

    @Basic
    @Column(name = "ProfName")
    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    @Basic
    @Column(name = "CourseVenue")
    public String getCourseVenue() {
        return courseVenue;
    }

    public void setCourseVenue(String courseVenue) {
        this.courseVenue = courseVenue;
    }

    @Basic
    @Column(name = "StuAttendCount")
    public Integer getStuAttendCount() {
        return stuAttendCount;
    }

    public void setStuAttendCount(Integer stuAttendCount) {
        this.stuAttendCount = stuAttendCount;
    }

    public void incStuAttendCount() { ++stuAttendCount; }

    public void decStuAttendCount() { --stuAttendCount; }

    @Id
    @Column(name = "CourseID")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "ExamVenue")
    public String getExamVenue() {
        return examVenue;
    }

    public void setExamVenue(String examVenue) {
        this.examVenue = examVenue;
    }

    @Basic
    @Column(name = "ExamDate")
    public Timestamp getExamDate() {
        return examDate;
    }

    public void setExamDate(Timestamp examDate) {
        this.examDate = examDate;
    }

    @Basic
    @Column(name = "CourseName")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
    @Column(name = "Semester")
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Basic
    @Column(name = "Intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "Grade")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "Credit")
    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (profCampusCardId != null ? !profCampusCardId.equals(that.profCampusCardId) : that.profCampusCardId != null)
            return false;
        if (stuLimitCount != null ? !stuLimitCount.equals(that.stuLimitCount) : that.stuLimitCount != null)
            return false;
        if (profName != null ? !profName.equals(that.profName) : that.profName != null) return false;
        if (courseVenue != null ? !courseVenue.equals(that.courseVenue) : that.courseVenue != null) return false;
        if (stuAttendCount != null ? !stuAttendCount.equals(that.stuAttendCount) : that.stuAttendCount != null)
            return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (examVenue != null ? !examVenue.equals(that.examVenue) : that.examVenue != null) return false;
        if (examDate != null ? !examDate.equals(that.examDate) : that.examDate != null) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;
        if (intro != null ? !intro.equals(that.intro) : that.intro != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = profCampusCardId != null ? profCampusCardId.hashCode() : 0;
        result = 31 * result + (stuLimitCount != null ? stuLimitCount.hashCode() : 0);
        result = 31 * result + (profName != null ? profName.hashCode() : 0);
        result = 31 * result + (courseVenue != null ? courseVenue.hashCode() : 0);
        result = 31 * result + (stuAttendCount != null ? stuAttendCount.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (examVenue != null ? examVenue.hashCode() : 0);
        result = 31 * result + (examDate != null ? examDate.hashCode() : 0);
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (intro != null ? intro.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<CourseRecordEntity> getCourseRecordsByCourseId() {
        return courseRecordsByCourseId;
    }

    public void setCourseRecordsByCourseId(Collection<CourseRecordEntity> courseRecordsByCourseId) {
        this.courseRecordsByCourseId = courseRecordsByCourseId;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<CourseScheduleEntity> getCourseSchedulesByCourseId() {
        return courseSchedulesByCourseId;
    }

    public void setCourseSchedulesByCourseId(Collection<CourseScheduleEntity> courseSchedulesByCourseId) {
        this.courseSchedulesByCourseId = courseSchedulesByCourseId;
    }
}
