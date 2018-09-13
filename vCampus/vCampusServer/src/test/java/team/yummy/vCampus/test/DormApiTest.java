package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.*;

public class DormApiTest extends ApiTest {
    @Test
    public void getDormInfo() {
        DormInfoViewModel info = api.get("/dorm/info").data(DormInfoViewModel.class);
        System.out.println(JSON.toJSONString(info));
    }
}
