package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.models.entity.DormEntity;
import team.yummy.vCampus.models.viewmodel.DormInfoViewModel;
import team.yummy.vCampus.server.annotation.Get;
import team.yummy.vCampus.server.framework.Controller;

import java.util.Optional;

public class DormController extends Controller {
    /**
     * @apiGroup Dorm
     * @api {get} /dorm/info GetDormInfo
     * @apiPermission all
     * @apiDescription 获取宿舍信息
     * @apiSuccess List_DormInfoViewModel List of DormInfoViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/dorm/info");
     * DormInfoViewModel dormInfo = res.data(DormInfoViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Get(route = "info")
    public String getDormInfo() {
        Optional<DormEntity> dorm = account.getDormsByCampusCardId().stream().findFirst();
        if (!dorm.isPresent()) {
            webContext.response.setStatusCode("404");
            return "No dorm yet";
        }
        webContext.response.setBody(JSON.toJSONString(new DormInfoViewModel(dorm.get())));
        return "OK";
    }
}
