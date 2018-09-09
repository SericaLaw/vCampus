package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import team.yummy.vCampus.models.viewmodel.LoginViewModel;
import team.yummy.vCampus.util.Api;
import team.yummy.vCampus.web.WebResponse;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiTest {

    Api api = new Api();

    @Before
    public void loginAndGetAuth() {
        WebResponse login = api.post(
                "/account/login",
                JSON.toJSONString(new LoginViewModel("213160003","123"))
        );
        assert login.getStatusCode().equals("200");
    }
}
