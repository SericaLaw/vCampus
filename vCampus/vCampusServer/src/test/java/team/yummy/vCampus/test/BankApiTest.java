package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.BankInfoViewModel;
import team.yummy.vCampus.web.WebResponse;

public class BankApiTest extends ApiTest {
    @Test
    public void getInfo() {
        WebResponse res = api.get("/bank/info");
        BankInfoViewModel info = res.data(BankInfoViewModel.class);
        System.out.println(JSON.toJSONString(info));
    }
}
