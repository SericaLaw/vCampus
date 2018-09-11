package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.GoodsViewModel;
import team.yummy.vCampus.web.WebResponse;

public class StoreApiTest extends ApiTest {
    /**
     * @apiGroup Store
     * @api {post} /goods
     * @apiDescription 添加商品
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * GoodsViewModel newGoods = new GoodsViewModel();
     * newGoods.setGoodsName("iPhone 100");
     * newGoods.setImgUrl("./images/item.png");
     * newGoods.setPrice(10000.);
     * newGoods.setTag(1);
     *
     * WebResponse res = api.post("/goods", JSON.toJSONString(newGoods));
     *
     * @apiSuccessExample Success-Response:
     *     201 OK
     *
     * @apiErrorExample Error-Response:
     *     403 "Goods already exists."
     *
     */
    @Test
    public void createGoods() {
        GoodsViewModel newGoods = new GoodsViewModel();
        newGoods.setGoodsName("iPhone 100");
        newGoods.setImgUrl("./images/item.png");
        newGoods.setPrice(10000.);
        newGoods.setTag(1);

        WebResponse res = api.post("/goods", JSON.toJSONString(newGoods));
        assert res.getStatusCode().equals("201");
    }
}
