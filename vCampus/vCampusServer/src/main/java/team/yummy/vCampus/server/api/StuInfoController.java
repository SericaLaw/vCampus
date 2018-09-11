package team.yummy.vCampus.server.api;

import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.server.WebContext;
import team.yummy.vCampus.server.annotation.*;

public class StuInfoController extends Controller {

    AccountEntity account;

    @Override
    public void init(WebContext webContext) {
        super.init(webContext);
        Transaction tx = dbSession.beginTransaction();
        account = dbSession.get(AccountEntity.class, webContext.session.getString("campusCardId"));
        tx.commit();
    }


    @Post(route = "gpa")
    public void calcGPA() {
        double gpa = 0;
        int credits = 0;
        for (CourseRecordEntity record : account.getCourseRecordsByCampusCardId()) {
            double grade = 0;
            if (record.getScore() >= 60) {
                switch (record.getScore() % 10) {
                    case 9: case 8: case 7: case 6:
                        grade += 0.3;
                    case 5: case 4: case 3:
                        grade += 0.5;
                    case 0: case 1: case 2:
                        grade += 0.0;
                }
                grade += (record.getScore() - 50) / 10;
            }
            gpa += grade * record.getCourseByCourseId().getCredit().doubleValue();
            credits +=  record.getCourseByCourseId().getCredit().doubleValue();
        }
        gpa /= credits;

        dbSession.beginTransaction();
        account.getStuInfoByCampusCardId().setGpa(gpa);
        dbSession.save(account.getStuInfoByCampusCardId());
        dbSession.getTransaction().commit();
    }
}
