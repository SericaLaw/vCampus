package vCampus.server.test;

import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;
import vCampus.models.Account;
import vCampus.server.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

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
                        "Daisy"
                );
        Account resAccount = JSON.parseObject(jsonData, Account.class);

        assertEquals(true, expectedAccount.equals(resAccount));
    }
    @Ignore
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
                        "John"
                );
        Account expectedAccount2 =
                new Account(
                        "213150000",
                        "Foo",
                        "Bar",
                        "John",
                        "Doe"
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
                        "Oliver"
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

    public static void main(String[] args) {}
}
