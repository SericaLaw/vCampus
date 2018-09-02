import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.web.WebResponse;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiTest {
    protected Api loginAndGetAuth() {
        Api api = new Api();
        WebResponse login = api.post("/account/login", "{\"username\":\"client\",\"password\":\"123\"}");
        assert login.getStatusCode().equals("200");
        Map<String, String > resData = login.data(HashMap.class);
        api.setAuth(resData.get("Username"),resData.get("CampusCardID"), resData.get("Password"));
        return api;
    }
}
