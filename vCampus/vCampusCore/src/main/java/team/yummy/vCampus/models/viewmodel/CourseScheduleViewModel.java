package team.yummy.vCampus.models.viewmodel;

/**
 * @author Serica
 * 课程表表项类
 * 存放关联查询的结果，用于前端展示课程表信息
 */
public class CourseScheduleViewModel {
    private String courseID;
    private int weekDay;
    private int spanStart;
    private int spanEnd;

    private String courseName;
    private String profName;
    private String courseVenue;

    public CourseScheduleViewModel() {}

    public CourseScheduleViewModel(String courseID, int weekDay, int spanStart, int spanEnd, String courseName, String profName, String courseVenue) {
        this.courseID = courseID;
        this.weekDay = weekDay;
        this.spanStart = spanStart;
        this.spanEnd = spanEnd;
        this.courseName = courseName;
        this.profName = profName;
        this.courseVenue = courseVenue;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getSpanStart() {
        return spanStart;
    }

    public void setSpanStart(int spanStart) {
        this.spanStart = spanStart;
    }

    public int getSpanEnd() {
        return spanEnd;
    }

    public void setSpanEnd(int spanEnd) {
        this.spanEnd = spanEnd;
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


//    /**
//     * 关联查询，返回课程表项List
//     * TODO: 这里学期字段是HARD CODE，当然如果真的要到生产环境部署，可以从服务器获得当前学期
//     * @param campusCardID
//     * @return sql
//     */
//    public String getSql(String campusCardID) {
//        String sql = String.format("SELECT CourseID, WeekDay, SpanStart, SpanEnd, CourseName, ProfName, CourseVenue From Course c, CourseSchedule cs, CourseRecord cr WHERE c.CourseID = cs.CourseID AND cs.CourseID = cr.CourseID AND cr.CampusCardID = '%s' AND cr.Semester='18-19-2'", campusCardID);
//        return sql;
//    }
}
