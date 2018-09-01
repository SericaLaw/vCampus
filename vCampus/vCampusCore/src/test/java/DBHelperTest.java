
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import team.yummy.vCampus.data.DBHelper;
import team.yummy.vCampus.models.Account;
import team.yummy.vCampus.models.BorrowBookRecord;
import team.yummy.vCampus.models.RoleEnum;
import team.yummy.vCampus.util.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * 以test/database下的数据库中的Account表为例
 * 21316XXXX用于DBHelper测试
 */
public class DBHelperTest {
    DBHelper dbHelper;

    {
        try {
            dbHelper = new DBHelper(getClass().getClassLoader().getResource("test_database.accdb").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

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
         * select( tableName, count )
         */
        jsonData = dbHelper.select("BorrowBook", 2);

        List<BorrowBookRecord> b = JSON.parseArray(jsonData, BorrowBookRecord.class);
        logger.log(jsonData);

        /**
         * select 数据库不存在的项
         */
        jsonData = dbHelper.select("BorrowBook", "BookID", "200");
        logger.log(jsonData);

        /**
         * 关联查询
         */
        String sql = String.format("SELECT Publisher, ExpiryDate, BorrowDate, BookName, Writer, BookID From Book b, BorrowBook bb WHERE b.BookID = bb.BookID and bb.CampusCardID = '%s'", "213160000");
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


        BorrowBookRecord borrowBookRecord = new BorrowBookRecord("202","213160000", new Date());
        jsonData = JSON.toJSONString(borrowBookRecord, SerializerFeature.WriteDateUseDateFormat);
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
        String jsonData = dbHelper.search("Book", "BookName", "机器");
        logger.log(jsonData);
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
