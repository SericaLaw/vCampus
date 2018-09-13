package team.yummy.vCampus.test;

import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.BankInfoViewModel;

public class BankApiTest extends ApiTest {
    @Test
    public void getInfo() {
        BankInfoViewModel info = api.get("/bank/info").data(BankInfoViewModel.class);
    }
}
