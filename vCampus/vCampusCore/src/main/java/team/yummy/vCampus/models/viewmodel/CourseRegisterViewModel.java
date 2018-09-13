package team.yummy.vCampus.models.viewmodel;

import team.yummy.vCampus.models.CourseStatusEnum;
import team.yummy.vCampus.models.entity.CourseEntity;
import team.yummy.vCampus.models.entity.CourseScheduleEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 选课的视图模型类，用于前端展示选课信息
 * @author Serica
 */
public class CourseRegisterViewModel {
    private String courseID;
    private String courseName;
    private String profName;
    private String courseVenue;
    private BigDecimal credit;
    private int stuAttendCount;
    private int stuLimitCount;

    private List<CourseScheduleViewModel> courseSchedule;
    private CourseStatusEnum status = CourseStatusEnum.AVAILABLE; // 可选，已满，冲突

    public CourseRegisterViewModel() {}

    public CourseRegisterViewModel(CourseEntity c) {
        this.courseID = c.getCourseId();
        this.courseName = c.getCourseName();
        this.profName = c.getProfName();
        this.courseVenue = c.getCourseVenue();
        this.credit = c.getCredit();
        this.stuAttendCount = c.getStuAttendCount();
        this.stuLimitCount = c.getStuLimitCount();
        this.courseSchedule = c.getCourseSchedulesByCourseId().stream()
            .map(s -> new CourseScheduleViewModel(
                c.getCourseId(),
                s.getWeekDay(),
                s.getSpanStart(),
                s.getSpanEnd(),
                c.getCourseName(),
                c.getProfName(),
                c.getCourseVenue()
            )).collect(Collectors.toList());
    }

    public List<CourseScheduleViewModel> getCourseSchedule() {
        return courseSchedule;
    }

    public void setCourseSchedule(List<CourseScheduleViewModel> courseSchedule) {
        this.courseSchedule = courseSchedule;
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

    public void setProfName(String profName) { this.profName = profName; }

    public String getCourseVenue() {
        return courseVenue;
    }

    public void setCourseVenue(String courseVenue) {
        this.courseVenue = courseVenue;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public int getStuAttendCount() {
        return stuAttendCount;
    }

    public void setStuAttendCount(int stuAttendCount) {
        this.stuAttendCount = stuAttendCount;
    }

    public int getStuLimitCount() {
        return stuLimitCount;
    }

    public void setStuLimitCount(int stuLimitCount) {
        this.stuLimitCount = stuLimitCount;
    }

    public CourseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CourseStatusEnum status) {
        this.status = status;
    }

//    public String getSql() {
//        String sql = String.format("SELECT WeekDay, SpanStart, SpanEnd FROM CourseSchedule WHERE CourseID = '%s'", courseID);
//        return sql;
//    }
}
