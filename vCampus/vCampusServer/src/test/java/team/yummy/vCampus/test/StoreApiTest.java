package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import team.yummy.vCampus.models.viewmodel.GoodsViewModel;
import team.yummy.vCampus.web.WebResponse;

import java.util.List;

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

    /**
     * @apiGroup Store
     * @api {get} /goods GetGoodsList
     * @apiPermission all
     * @apiDescription 获取商品列表
     * @apiSuccess List_GoodsViewModel List of GoodsViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/goods");
     * List<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      [{
     *          "Price":"1000.0",
     *          "Tag":"1",
     *          "Info":"An oooooold thinkpad version.",
     *          "ImgUrl":"http://nonexistent.com",
     *          "GoodsID":"c14eb7df-0624-421a-8c3f-d1b8b016c5db",
     *          "GoodsName":"Thinkpad T61"
     *        },{
     *          "Price":"100000.0",
     *          "Tag":"2",
     *          "Info":"Quite tough.",
     *          "ImgUrl":"http://nonexistent.com",
     *          "GoodsID":"dafa0250-e44b-42aa-8cfb-a5f5a9078d8f",
     *          "GoodsName":"Thinkpad X1 Carbon"
     *      }]
     */
    @Test
    public void getGoodsList() {
        WebResponse res = api.get("/goods");
        List<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);
        assert res.getStatusCode().equals("200");

    }
}
