package team.yummy.vCampus.server.api;


import com.alibaba.fastjson.JSON;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.CourseRegisterViewModel;
import team.yummy.vCampus.models.viewmodel.CourseReportViewModel;
import team.yummy.vCampus.models.viewmodel.CourseScheduleViewModel;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.api.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class CourseController extends Controller {

    AccountEntity account;

    @Override
    public void init(WebContext webContext) {
        super.init(webContext);
        Transaction tx = dbSession.beginTransaction();
        AccountEntity account = dbSession.get(AccountEntity.class, webContext.session.getString("campusCardId"));
        tx.commit();
    }

    public String getAcademicYear() { return ""; }

    public String getCurrentSemester() {
        return "";
    }

    @Get(route = "schedule")
    public String getSchedule() {
        ArrayList<CourseScheduleViewModel> schedules = new ArrayList<>();
        for (CourseRecordEntity record : account.getCourseRecordsByCampusCardId()) {
            CourseEntity course = record.getCourseByCourseId();
            for (CourseScheduleEntity schedule : course.getCourseSchedulesByCourseId()) {
                schedules.add(new CourseScheduleViewModel(
                    schedule.getCourseId(),
                    schedule.getWeekDay(),
                    schedule.getSpanStart(),
                    schedule.getSpanEnd(),
                    course.getCourseName(),
                    course.getProfName(),
                    course.getCourseVenue()
                ));
            }
        }
        return JSON.toJSONString(schedules);
    }

    @Get(route = "report")
    public String getReport() {
        ArrayList<CourseReportViewModel> reports = new ArrayList<>();
        for (CourseRecordEntity record : account.getCourseRecordsByCampusCardId()) {
            CourseEntity course = record.getCourseByCourseId();
            reports.add(new CourseReportViewModel(
                account.getCampusCardId(),
                course.getCourseName(),
                course.getCredit().doubleValue(),
                record.getScore(),
                record.getSemester(),
                record.getScoreType()
            ));
        }
        return JSON.toJSONString(reports);
    }

    @Get(route = "register")
    public String registerCourseList() {
        String campusCardId = webContext.session.getString("campusCardId");
        Transaction tx = dbSession.beginTransaction();
        StuInfoEntity stuInfo = dbSession.get(StuInfoEntity.class, campusCardId);

        List<CourseEntity> allCourses = dbSession.createQuery(
            "select cr from Course cr where cr.semester = ? " +
            "and (cr.grade = 0 or (cr.grade = ? and (cr.major = ? or cr.major is null)))"
        ).setParameter(0, getCurrentSemester())
        .setParameter(1, stuInfo.getEnrollmentYear())
        .setParameter(2, stuInfo.getMajor()).list();

        return "Success";
    }

}
