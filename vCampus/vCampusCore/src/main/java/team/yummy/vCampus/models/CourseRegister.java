package team.yummy.vCampus.models;

import team.yummy.vCampus.models.entity.Schedule;
import team.yummy.vCampus.models.viewmodel.CourseRegisterViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Serica
 * 选课类，用于展示可选和已选课程
 * 每次提交一次选课，都从服务器返回更新后的信息8
 */
public class CourseRegister {
    private List<CourseRegisterViewModel> courseList;



    public String getSql(int grade, int semester, String major) {
        String sql =
                String.format("SELECT CourseID, CourseName, ProfName, StuLimitCount, StuAttendCount, Credit, CourseVenue FROM Course WHERE (Grade = %d AND Semester = %d AND (Major = '%s' OR Major IS NULL)) OR (Grade = 0 AND Semester = %d)", grade, semester, major, semester);
        return sql;
    }

    public List<CourseRegisterViewModel> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseRegisterViewModel> courseList) {
        this.courseList = courseList;
    }

    public void setCourseStatus(List<Map> selectedCourse) {
        List<String> selectedCourseIDs = new ArrayList<>();
        for(Map<String, String> item : selectedCourse) {
            selectedCourseIDs.add(item.get("CourseID"));
        }
        for(CourseRegisterViewModel c: courseList) {
            if(selectedCourseIDs.contains(c.getCourseID()))
                c.setStatus(CourseStatusEnum.SELECTED);
            else if(c.getStuAttendCount() == c.getStuLimitCount())
                c.setStatus(CourseStatusEnum.NOT_AVAILABLE);
            else
                c.setStatus(CourseStatusEnum.AVAILABLE);

        }

        checkConflict();
    }

    /**
     * 检查courseList里状态为AVAILABLE和CONFLICT的项，
     * 与状态为SELECTED的项比对，更新他们的状态
     * 每次初始化以及选课退课后需要调用该方法
     */
    public void checkConflict() {
        // 1~7工作日，1~13节次
        boolean[][] table = new boolean[8][14];
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 14; j++)
                table[i][j] = true;
        }

        // 初始化table，已经有课的时间段设置false
        for(CourseRegisterViewModel c : courseList) {
            if(c.getStatus() == CourseStatusEnum.SELECTED) {
                List<Schedule> schedules = c.getCourseSchedule();
                for (Schedule s : schedules) {
                    for (int i = s.getSpanStart(); i <= s.getSpanEnd(); i++)
                        table[s.getWeekDay()][i] = false;
                }
            }
        }

        for(CourseRegisterViewModel c : courseList) {
            if(c.getStatus() == CourseStatusEnum.AVAILABLE || c.getStatus() == CourseStatusEnum.CONFLICT) {
                // init
                c.setStatus(CourseStatusEnum.AVAILABLE);

                List<Schedule> schedules = c.getCourseSchedule();
                for(Schedule s : schedules) {
                    for(int i = s.getSpanStart(); i <= s.getSpanEnd(); i++) {
                        if(table[s.getWeekDay()][i] == false) {
                            c.setStatus(CourseStatusEnum.CONFLICT);
                            break;
                        }
                    }
                    if(c.getStatus() == CourseStatusEnum.CONFLICT)
                        break;
                }
            }
        }
    }
}
