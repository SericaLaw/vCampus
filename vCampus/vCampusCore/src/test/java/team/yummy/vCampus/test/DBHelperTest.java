package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.models.*;
import team.yummy.vCampus.models.viewmodel.CourseReportViewModel;
import team.yummy.vCampus.util.Logger;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;


/**
 * @author Serica
 * team.yummy.vCampus.test.Account 类
 * 对应Account表
 */
class Account {
    private String campusCardID;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String role;

    public Account() {}
    public Account(String campusCardID, String username, String password, String lastName, String firstName, RoleEnum role) {
        setCampusCardID(campusCardID);
        setUsername(username);
        setPassword(password);
        setLastName(lastName);
        setFirstName(firstName);
        setRole(role.getName());
    }
    public String getCampusCardID() {
        return campusCardID;
    }

    public void setCampusCardID(String campusCardID) {
        this.campusCardID = campusCardID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        assert role.equals("student") || role.equals("teacher") || role.equals("admin");
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("Class team.yummy.vCampus.test.Account [ campusCardID = %s, username = %s, " +
                        "password = %s, lastName = %s, firstName = %s, role = %s]",
                campusCardID, username, password, lastName, firstName, role);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof Account) {
            Account another = (Account) obj;
            return this.campusCardID.equals(another.campusCardID) && this.firstName.equals(another.firstName)
                    && this.lastName.equals(another.lastName) && this.username.equals(another.username)
                    && this.password.equals(another.password) && this.role.equals(another.role);
        }
        return false;
    }


}

/**
 * 以test/database下的数据库中的Account表为例
 * 21316XXXX用于DBHelper测试
 */
public class DBHelperTest {
    DBHelper dbHelper = new DBHelper("jdbc:Access:///" + getClass().getClassLoader().getResource("test_database.accdb").getPath());

    @Test
    public void testSelectOne() {
        String jsonData =
                dbHelper.selectOne(
                        "team.yummy.vCampus.test.Account",
                        "CampusCardID",
                        "213160001"
                );

        Account expectedAccount =
                new Account(
                        "213160001",
                        "Daisy",
                        "Bar",
                        "Johnson",
                        "Daisy",
                        RoleEnum.STUDENT
                );
        Account resAccount = JSON.parseObject(jsonData, Account.class);

        assertEquals(true, expectedAccount.equals(resAccount));
    }

    @Test
    public void testSelect() {
        String jsonData = dbHelper.select(
                "team.yummy.vCampus.test.Account",
                "Password",
                "Bar"
        );
        Account expectedAccount1 =
                new Account(
                        "213160000",
                        "Foo",
                        "Bar",
                        "Doe",
                        "John",
                        RoleEnum.STUDENT
                );
        Account expectedAccount2 =
                new Account(
                        "213160001",
                        "Daisy",
                        "Bar",
                        "Johnson",
                        "Daisy",
                        RoleEnum.STUDENT
                );
        List<Account> expectedList = new ArrayList<Account>();
        expectedList.add(expectedAccount1);
        expectedList.add(expectedAccount2);

        List<Account> resList= JSON.parseArray(jsonData, Account.class);

        boolean flag = false;

        for (Account resAccount : resList) {
            flag = false;
            for(Account expAccount : expectedList) {
                if(expAccount.equals(resAccount)) {
                    flag = true;
                    break;
                }
            }
            if(flag == false)
                break;
        }

        assertEquals(true, flag);

        /**
         * select 数据库不存在的项
         */
        jsonData = dbHelper.select("BorrowBook", "BookID", "200");
        logger.log(jsonData);

        /**
         * 关联查询
         */
        String sql = String.format("SELECT Publisher, ExpiryDate, BorrowDate, BookName, Writer, BookID From BookViewModel b, BorrowBook bb WHERE b.BookID = bb.BookID and bb.CampusCardID = '%s'", "213160000");
        jsonData = dbHelper.select(sql);
        logger.log(jsonData);
    }

    @Test
    public void testInsertAndDelete() {
        Account newAccount =
                new Account(
                        "213160005",
                        "Oliver",
                        "456",
                        "Queen",
                        "Oliver",
                        RoleEnum.STUDENT
                );

        boolean insertSuc =
                dbHelper.insert(
                        "team.yummy.vCampus.test.Account",
                        JSON.toJSONString(newAccount)
                );

        assertEquals(true, insertSuc);

        String jsonData =
                dbHelper.selectOne(
                        "team.yummy.vCampus.test.Account",
                        "CampusCardID",
                        "213160005"
                );

        Account resAccount = JSON.parseObject(jsonData, Account.class);

        assertEquals(true, newAccount.equals(resAccount));

        boolean deleteSuc =
                dbHelper.delete(
                        "team.yummy.vCampus.test.Account",
                        "CampusCardID",
                        "213160005"
                );
        assertEquals(true, deleteSuc);

        jsonData =
                dbHelper.selectOne(
                        "team.yummy.vCampus.test.Account",
                        "CampusCardID",
                        "213160005"
                );
        // selectOne的查询结果为空时jsonData是 {}
        assertEquals(true, jsonData.equals("{}"));

        boolean suc = dbHelper.insert("BorrowBook", jsonData);
        if(suc)
            logger.log("suc");
//        jsonData = dbHelper.select("BorrowBook", "campusCardID", "213160000");
//        List<BorrowBookRecord> l = JSON.parseArray(jsonData, BorrowBookRecord.class);
//        logger.log(jsonData);

//        dbHelper.delete("BorrowBook", "BookID", "101", "CampusCardID", "213160000");


    }

    @Test
    public void testUpdate() {
        boolean updateSuc = dbHelper.update("StuInfo", "CampusCardID", "213160000", "{\"Birthplace\":\"Shanghai\",\"Major\":\"SE\"}");
        assertEquals(true, updateSuc);
    }

    @Test
    public void testSearch() {
        String jsonData = dbHelper.search("BookViewModel", "BookName", "机器");
        logger.log(jsonData);
    }


    @Test
    public void testSelectCourseReport() {
        CourseReportViewModel courseReportViewModel = new CourseReportViewModel();
        String jsonData = dbHelper.select(courseReportViewModel.getSql("213170000"));
        logger.log(jsonData);

        jsonData=dbHelper.selectOne("SELECT StuAttendCount, StuLimitCount FROM Course WHERE CourseID = '1001'");
        logger.log(jsonData);
    }


    private static Logger logger = new Logger("team.yummy.vCampus.test.DBHelperTest");
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DBHelperTest.class);
        for(Failure failure : result.getFailures()) {
            logger.log(failure.toString());
        }
        if (result.wasSuccessful())
            logger.log("Unit test passed.");
        else
            logger.log("Unit test failed.");
    }
}
