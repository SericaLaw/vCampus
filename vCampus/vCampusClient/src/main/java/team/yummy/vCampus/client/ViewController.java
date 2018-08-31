package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.models.Account;

public abstract class ViewController {
    protected Api api;
    protected String accountJsonData;
    protected StageController stageController;
    protected Account currentAccount;

    public ViewController() {
        api = new Api();
        currentAccount = null;

    }
    public void setStageController(StageController stageController) { this.stageController = stageController; }

    public String getAccountJsonData() {
        return accountJsonData;
    }

    public void setAccountJsonData(String accountJsonData) {
        this.accountJsonData = accountJsonData;
        this.currentAccount = JSON.parseObject(accountJsonData, Account.class);
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }




}
