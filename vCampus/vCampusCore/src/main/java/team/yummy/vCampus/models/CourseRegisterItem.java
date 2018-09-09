package team.yummy.vCampus.models;

import java.util.List;

public class CourseRegisterItem {
    private String courseID;
    private String courseName;
    private String profName;
    private String courseVenue;
    private String credit;
    private int StuAttendCount;
    private int StuLimitCount;

    private List<Schedule> courseSchedule;
    private CourseStatusEnum status; // 可选，已满，冲突

    public CourseRegisterItem() {}

    public List<Schedule> getCourseSchedule() {
        return courseSchedule;
    }

    public void setCourseSchedule(List<Schedule> courseSchedule) {
        this.courseSchedule = courseSchedule;
    }


    public String getSql() {
        String sql = String.format("SELECT WeekDay, SpanStart, SpanEnd FROM CourseSchedule WHERE CourseID = '%s'", courseID);
        return sql;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getCourseVenue() {
        return courseVenue;
    }

    public void setCourseVenue(String courseVenue) {
        this.courseVenue = courseVenue;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public int getStuAttendCount() {
        return StuAttendCount;
    }

    public void setStuAttendCount(int stuAttendCount) {
        StuAttendCount = stuAttendCount;
    }

    public int getStuLimitCount() {
        return StuLimitCount;
    }

    public void setStuLimitCount(int stuLimitCount) {
        StuLimitCount = stuLimitCount;
    }


    public CourseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CourseStatusEnum status) {
        this.status = status;
    }


}