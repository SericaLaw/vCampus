package team.yummy.vCampus.models.viewmodel;

/**
 * 成绩单表项的视图模型类，用于前端展示成绩信息
 * @author Serica
 */
public class CourseReportViewModel {
    private String campusCardId;
    private String courseName;
    private double credit;
    private int score;
    private String semester;
    private String scoreType;

    public CourseReportViewModel() {}

    public CourseReportViewModel(CourseReportViewModel that) {
        this.campusCardId = that.getCampusCardId();
        this.courseName = that.getCourseName();
    }
    public CourseReportViewModel(String campusCardId, String courseName, double credit, int score, String semester, String scoreType) {
        this.campusCardId = campusCardId;
        this.courseName = courseName;
        this.credit = credit;
        this.score = score;
        this.semester = semester;
        this.scoreType = scoreType;
    }

    public String getCampusCardId() { return campusCardId; }

    public void setCampusCardId(String campusCardId) { this.campusCardId = campusCardId; }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    /**
     * 关联查询，返回成绩单列表，0分默认是这学期新选择的课程，不会展现在成绩单中
     * @param campusCardID 要查询成绩单的学生的一卡通号
     * @return 查询的SQL语句
     */
    public String getSql(String campusCardID) {
        String sql = String.format("SELECT CourseName, Semester, Credit, Score, ScoreType FROM Course c, CourseRecord cr WHERE c.CourseID = cr.CourseID AND cr.CampusCardID = '%s' AND (NOT cr.Score = '0')", campusCardID);
        return sql;
    }
}
