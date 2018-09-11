package team.yummy.vCampus.server.api;

import team.yummy.vCampus.server.annotation.Get;

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
    @Get(route = "bank")
    public void getBankInfo() {

    }
}
