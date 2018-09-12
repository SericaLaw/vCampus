package team.yummy.vCampus.models.viewmodel;

/**
 * @author Serica
 * 教师端成绩录入界面view model
 * 一条对应表格中的一项，包括学生的一卡通、姓名、课程名、学期和成绩
 * 用该view model展示信息和提交成绩录入请求
 */
public class TeacherCourseReportViewModel {
    private CourseReportViewModel report;
    private String firstName;
    private String lastName;

    public CourseReportViewModel getReport() {
        return report;
    }

    public void setReport(CourseReportViewModel report) {
        this.report = report;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
