package vCampus.server.test;

import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import vCampus.models.Account;
import vCampus.server.database.DBHelper;
import vCampus.server.util.Logger;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * 以test/database下的数据库中的Account表为例
 * 21316XXXX用于DBHelper测试
 */
public class DBHelperTest {
    DBHelper dbHelper = new DBHelper();
    @Ignore
    @Test
    public void testSelectOne() {
        String jsonData =
                dbHelper.selectOne(
                        "Account",
                        "CampusCardID",
                        "213160001"
                );

        Account expectedAccount =
                new Account(
                        "213160001",
                        "Daisy",
                        "123",
                        "Johnson",
                        "Daisy",
                        "student"
                );
        Account resAccount = JSON.parseObject(jsonData, Account.class);

        assertEquals(true, expectedAccount.equals(resAccount));
    }

    @Test
    public void testSelect() {
        String jsonData = dbHelper.select(
                "Account",
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
                        "student"
                );
        Account expectedAccount2 =
                new Account(
                        "213160001",
                        "Daisy",
                        "Bar",
                        "Johnson",
                        "Daisy",
                        "student"
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
                        "student"
                );

        boolean insertSuc =
                dbHelper.insert(
                        "Account",
                        JSON.toJSONString(newAccount)
                );

        assertEquals(true, insertSuc);

        String jsonData =
                dbHelper.selectOne(
                        "Account",
                        "CampusCardID",
                        "213160005"
                );

        Account resAccount = JSON.parseObject(jsonData, Account.class);

        assertEquals(true, newAccount.equals(resAccount));

        boolean deleteSuc =
                dbHelper.delete(
                        "Account",
                        "CampusCardID",
                        "213160005"
                );
        assertEquals(true, deleteSuc);

        jsonData =
                dbHelper.selectOne(
                        "Account",
                        "CampusCardID",
                        "213160005"
                );
        // selectOne的查询结果为空时jsonData是 {}
        assertEquals(true, jsonData.equals("{}"));
    }

    private static Logger logger = new Logger("DBHelperTest");
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
