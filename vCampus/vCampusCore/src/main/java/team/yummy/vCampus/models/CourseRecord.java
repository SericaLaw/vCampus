package team.yummy.vCampus.models;

/**
 * @author Serica
 * CourseRecord 类
 * 用于post选课信息
 */
public class CourseRecord {
    private String courseID;
    private String campusCardID;
    private String score;
    private String semester;
    private String scoreType;

    public CourseRecord(String courseID, String campusCardID, String score, String semester, String scoreType) {
        this.courseID = courseID;
        this.campusCardID = campusCardID;
        this.score = score;
        this.semester = semester;
        this.scoreType = scoreType;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCampusCardID() {
        return campusCardID;
    }

    public void setCampusCardID(String campusCardID) {
        this.campusCardID = campusCardID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
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
}
