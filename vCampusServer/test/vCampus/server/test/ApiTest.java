package vCampus.server.test;
import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;
import vCampus.models.Account;
import vCampus.server.api.Api;
import vCampus.server.database.DBHelper;
import vCampus.server.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
public class ApiTest {
    @Test
    public void testLogin() {
        HttpResponse login = Api.post("/account/login", "{\"username\":\"Foo\",\"password\":\"Bar\"}");
        HttpResponse expectedRes = new HttpResponse("200","{\"Password\":\"Bar\",\"Username\":\"Foo\",\"FirstName\":\"Doe\",\"LastName\":\"John\",\"CampusCardID\":\"213150000\"}", "OK");
        assertEquals(true, expectedRes.equals(login));
    }
}
