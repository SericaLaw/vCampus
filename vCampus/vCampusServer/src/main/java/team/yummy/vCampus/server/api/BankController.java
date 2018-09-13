package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.models.entity.BankAccountEntity;
import team.yummy.vCampus.models.viewmodel.BankInfoViewModel;
import team.yummy.vCampus.server.annotation.Get;
import team.yummy.vCampus.server.framework.Controller;

public class BankController extends Controller {
    /**
     * @apiGroup Bank
     * @api {get} /bank/info GetBankInfo
     * @apiPermission student
     * @apiDescription 获取银行信息
     * @apiSuccess List_BankInfoViewModel List of BankInfoViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/bank/info");
     * List<BankInfoViewModel> bankInfoList = res.dataList(BankInfoViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Get(route = "info")
    public String getBankInfo() {
        BankAccountEntity bank = account.getBankAccountByCampusCardId();
        if (bank == null) {
            webContext.response.setStatusCode("404");
            return "No bank account yet";
        }

        webContext.response.setBody(JSON.toJSONString(new BankInfoViewModel(bank)));
        return "OK";
    }
}
