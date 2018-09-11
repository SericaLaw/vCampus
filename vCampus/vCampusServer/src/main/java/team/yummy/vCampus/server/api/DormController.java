package team.yummy.vCampus.server.api;

import team.yummy.vCampus.server.annotation.Get;

public class DormController extends Controller {
    /**
     * @apiGroup Dorm
     * @api {get} /dorm/info GetDormInfo
     * @apiPermission all
     * @apiDescription 获取宿舍信息
     * @apiSuccess List_DormInfoViewModel List of DormInfoViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/dorm/info");
     * List<DormInfoViewModel> dormInfoList = res.dataList(DormInfoViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Get(route = "info")
    public void getDormInfo() {

    }
}
