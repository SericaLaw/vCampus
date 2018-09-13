package team.yummy.vCampus.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import team.yummy.vCampus.models.entity.BankAccountEntity;
import team.yummy.vCampus.models.entity.CartRecordEntity;
import team.yummy.vCampus.models.viewmodel.CartRecordViewModel;
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

    @Test
    public void addAndBuy() {
        GoodsViewModel goods = api.get("/goods").dataList(GoodsViewModel.class).get(0);
        String cartId = api.post("/store/cart", goods.getGoodsId()).getBody();
        BankAccountEntity before = api.get("/bankAccount/campusCardId/213160003").dataList(BankAccountEntity.class).get(0);
        api.post("/store/purchase", "[\"" + cartId + "\"]");
        BankAccountEntity after = api.get("/bankAccount/campusCardId/213160003").dataList(BankAccountEntity.class).get(0);
        assert before.getBalance() == after.getBalance() + goods.getPrice();
    }

    /**
     * @apiGroup Store
     * @api {patch} /goods/goodsId/{goodsId}
     * @apiDescription 修改商品 ModifyGoods
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
     * GoodsViewModel goodsToModify = new GoodsViewModel();
     * goodsToModify.setGoodsId("66b20429-922e-45f9-aeb3-6c7f4fdeaff1");
     * goodsToModify.setGoodsName("iPhone 100");
     * goodsToModify.setPrice(10000);
     * goodsToModify.setImgUrl("./images/item.png");
     * goodsToModify.setTag(1);
     *
     * // 下面开始更新
     * goodsToModify.setInfo("罗永浩子公司荣誉出品");
     * api.patch("/goods/goodsId/" + goodsToModify.getGoodsId(), JSON.toJSONString(goodsToModify));
     * @apiSuccessExample Success-Response:
     *     200 OK
     *
     * @apiErrorExample Error-Response:
     *     404 "Goods not found."
     */
    @Test
    public void modifyGoods() {
        // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
        GoodsViewModel goodsToModify = new GoodsViewModel();
        goodsToModify.setGoodsId("66b20429-922e-45f9-aeb3-6c7f4fdeaff1");
        goodsToModify.setGoodsName("iPhone 100");
        goodsToModify.setPrice(10000);
        goodsToModify.setImgUrl("./images/item.png");
        goodsToModify.setTag(1);

        // 下面开始更新
        goodsToModify.setInfo("罗永浩子公司荣誉出品");
        api.patch("/goods/goodsId/" + goodsToModify.getGoodsId(), JSON.toJSONString(goodsToModify));
    }

    /**
     * @apiGroup Store
     * @api {delete} /goods/goodsId/{goodsId}
     * @apiDescription 删除商品 DeleteGoods
     * @apiPermission admin
     * @apiParamExample Code Snippets
     * // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
     * GoodsViewModel goodsToDelete = new GoodsViewModel();
     * goodsToDelete.setGoodsId("66b20429-922e-45f9-aeb3-6c7f4fdeaff1");
     * // 下面开始删除
     * api.delete("/goods/goodsId/" + goodsToDelete.getGoodsId());
     * @apiSuccessExample Success-Response:
     *     200 OK
     *
     * @apiErrorExample Error-Response:
     *     404 "Goods not found."
     */
    @Test
    public void deleteGoods() {
        // 下面的操作在实际使用中并不需要，只需要得到一个要修改对象的引用即可
        GoodsViewModel goodsToDelete = new GoodsViewModel();
        goodsToDelete.setGoodsId("66b20429-922e-45f9-aeb3-6c7f4fdeaff1");
        // 下面开始删除
        api.delete("/goods/goodsId/" + goodsToDelete.getGoodsId());
    }

    /**
     * @apiGroup Store
     * @api {get} /goods/goodsId/{goodsId}
     * @apiDescription 按ID查询商品 GetGoodsById
     * @apiPermission student admin
     * @apiParamExample Code Snippets
     * String goodsId = "66b20429-922e-45f9-aeb3-6c7f4fdeaff1";
     * WebResponse res = api.get("/goods/goodsId/" + goodsId);
     * GoodsViewModel goods = res.dataList(GoodsViewModel.class, 0);
     * @apiSuccessExample Success-Response:
     *     200 OK
     *     [{
     *          "Price":"10000.0",
     *          "Tag":"1",
     *          "Info":"罗永浩子公司荣誉出品",
     *          "ImgUrl":"./images/item.png",
     *          "GoodsID":"66b20429-922e-45f9-aeb3-6c7f4fdeaff1",
     *          "GoodsName":"iPhone 100"
     *     }]
     *
     *
     * @apiErrorExample Error-Response:
     *     404 "Goods not found."
     */
    @Test
    public void getGoodsById() {
        String goodsId = "66b20429-922e-45f9-aeb3-6c7f4fdeaff1";
        WebResponse res = api.get("/goods/goodsId/" + goodsId);
        GoodsViewModel goods = res.dataList(GoodsViewModel.class, 0);
    }

    @Test
    public void getCartList() {
        WebResponse res = api.get("/store/cart");
        List<CartRecordViewModel> goods = res.dataList(CartRecordViewModel.class);
    }
}
