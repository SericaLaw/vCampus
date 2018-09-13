package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.models.viewmodel.AccountViewModel;
import team.yummy.vCampus.util.Api;

/**
 * ViewController抽象基类，封装了一些所有前端Controller的公有方法和成员
 * @author Serica
 */
public abstract class ViewController {
    protected Api api;
    protected String accountJsonData;
    protected StageController stageController;
    protected AccountViewModel currentAccount;

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
        this.currentAccount = JSON.parseObject(accountJsonData, AccountViewModel.class);
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }




}
