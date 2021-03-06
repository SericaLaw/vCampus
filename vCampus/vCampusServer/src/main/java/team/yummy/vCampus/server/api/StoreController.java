package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.*;
import team.yummy.vCampus.server.annotation.*;
import team.yummy.vCampus.server.framework.Controller;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Authorize
public class StoreController extends Controller {

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
    
    /**
     * @apiGroup Store
     * @api {get} /store/cart GetCartList
     * @apiPermission student teacher
     * @apiDescription 获取购物车列表
     * @apiSuccess List_CartRecordViewModel List of CartRecordViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/store/cart");
     * List<CartRecordViewModel> cartList = res.dataList(CartRecordViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Get(route = "cart")
    public void getCartList() {
        webContext.response.setBody(JSON.toJSONString(
            account.getCartRecordsByCampusCardId().stream()
                .filter(record -> !record.getIsPurchased())
                .map(record -> new CartRecordViewModel(record)).toArray()
        ));
    }

    /**
     * @apiGroup Store
     * @api {post} /store/cart AddToCart
     * @apiPermission student
     * @apiDescription 添加至购物车(cart record没有该商品)
     * @apiParamExample Code Snippets
     * String goodsId = goodsViewModel.getGoodsId();
     * WebResponse res = api.post("/store/cart", goodsId);
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Post(route = "cart")
    public void addToCart(@FromBody String goodsId) {
        CartRecordEntity record = account.getCartRecordsByCampusCardId().stream()
            .filter(r -> !r.getIsPurchased())
            .filter(r -> r.getGoodsByGoodsId().getGoodsId().equals(goodsId))
            .findFirst().orElse(new CartRecordEntity(goodsId, account.getCampusCardId()));

        // 不管是购物车中已有记录还是新生成的，自增1即可
        record.incGoodsCnt();
        dbSession.beginTransaction();
        dbSession.saveOrUpdate(record);
        dbSession.getTransaction().commit();
        webContext.response.setBody(record.getCartRecordId());
    }

    /**
     * @apiGroup Store
     * @api {post} /store/purchase Purchase
     * @apiPermission student
     * @apiDescription 支付 删除CartRecord表相应购物车数据；Bank表里余额需要修改
     * @apiParamExample Code Snippets
     * String uuid1 = cartRecordViewModel1.getCartRecordID();
     * String uuid2 = cartRecordViewModel2.getCartRecordID();
     * List<String> purchases = new ArrayList<>();
     * purchases.add(uuid1);
     * purchases.add(uuid2);
     * WebResponse res = api.post("/store/purchase", JSON.toJSONString(purchases, String.class));
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Post(route = "purchase")
    public String purchase(@FromBody(String.class) List<String> recordIds) {
        dbSession.beginTransaction();
        double total_price = 0.0;
        List<CartRecordEntity> records = new ArrayList<>();
        for (String recordId : recordIds) {
            CartRecordEntity record = dbSession.get(CartRecordEntity.class, recordId);
            if (record.getCartRecordId() != null && !record.getIsPurchased()) {
                total_price += record.getGoodsByGoodsId().getPrice() * record.getGoodsCnt();
                records.add(record);
            } else {
                webContext.response.setStatusCode("400");
                return "Invalid cart";
            }
        }

        BankAccountEntity bank = account.getBankAccountByCampusCardId();
        if (bank.getBalance() < total_price) {
            webContext.response.setStatusCode("403");
            return "Balance inadequate";
        }
        for (CartRecordEntity record : records) {
            record.setIsSel(false);
            record.setIsPurchased(true);
            record.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            dbSession.update(record);
        }
        bank.setBalance(bank.getBalance() - total_price);
        BankRecordEntity bank_record = new BankRecordEntity();
        bank_record.setId(UUID.randomUUID().toString());
        bank_record.setBankAccountByCampusCardId(bank);
        bank_record.setIncomeAndExpense(-total_price);
        bank_record.setRecordTime(new Timestamp(System.currentTimeMillis()));
        bank_record.setReason("购买了" + records.stream().map(
            r -> r.getGoodsByGoodsId().getGoodsName() + "×" + r.getGoodsCnt()
        ).collect(Collectors.joining(", ")));
        dbSession.save(bank_record);
        dbSession.getTransaction().commit();
        return "OK";
    }

    /**
     * @apiGroup
     * @api {patch} /store/cart ModifyCart
     * @apiPermission student
     * @apiDescription 增加商品数量(cart record已有该商品)
     * @apiParamExample Code Snippets
     * String uuid = cartRecordViewModel.getCartRecordID();
     * int count = cartRecordViewModel.getGoodsCount();
     * WebResponse res = api.patch("/store/cart", String.format("{\"uuid\": %s, \"count\":%d}", uuid, count);
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......Store
     */
    @Patch(route = "cart")
    public void modifyCart(@FromBody CartRecordViewModel model) {
        dbSession.beginTransaction();
        CartRecordEntity record = dbSession.get(CartRecordEntity.class, model.getCartRecordID());
        if (record.getCartRecordId() != null && !record.getIsPurchased()) {
            record.setGoodsCnt(model.getGoodsCount());
            dbSession.update(record);
        }
        dbSession.getTransaction().commit();
    }

    /**
     * @apiGroup Store
     * @api {post} /store/clear DeleteCartGoods
     * @apiPermission student
     * @apiDescription 删除购物车商品
     * @apiParamExample Code Snippets
     * String uuid1 = cartRecordViewModel1.getCartRecordID();
     * String uuid2 = cartRecordViewModel2.getCartRecordID();
     * WebResponse res = api.post("/store/clear", String.format("[%s, %s]", uuid1, uuid2);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Post(route = "clear")
    public void deleteCartGoods(@FromBody(String.class) List<String> recordIds) {
        dbSession.beginTransaction();
        for (String recordId : recordIds) {
            CartRecordEntity record = dbSession.load(CartRecordEntity.class, recordId);
            if (record.getCartRecordId() != null && !record.getIsPurchased()) {
                dbSession.delete(record);
            }
        }
        dbSession.getTransaction().commit();
    }

    /**
     * 管理员增删改查商品可使用默认方法
     */

    /**
     * @apiGroup Store
     * @api {post} /goods CreateGoods
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

    /**
     * @apiGroup Store
     * @api {get} /goods/goodsId/{goodsId} GetGoodsById
     * @apiDescription 按ID查询商品
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

    /**
     * @apiGroup Store
     * @api {delete} /goods/goodsId/{goodsId} DeleteGoods
     * @apiDescription 删除商品
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

    /**
     * @apiGroup Store
     * @api {patch} /goods/goodsId/{goodsId} ModifyBook
     * @apiDescription 修改商品
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


}
