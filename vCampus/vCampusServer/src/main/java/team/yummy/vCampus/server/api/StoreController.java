package team.yummy.vCampus.server.api;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.hibernate.Transaction;
import team.yummy.vCampus.models.entity.*;
import team.yummy.vCampus.models.viewmodel.*;
import team.yummy.vCampus.server.annotation.*;

import java.sql.Timestamp;
import java.util.*;

public class StoreController extends Controller {

//    /**
//     * @apiGroup Store
//     * @api {get} /store/goods GetGoodsList
//     * @apiPermission all
//     * @apiDescription 获取商品列表
//     * @apiSuccess List_GoodsViewModel List of GoodsViewModel
//     * @apiParamExample Code Snippets
//     * WebResponse res = api.get("/store/goods");
//     * List<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);
//     * @apiSuccessExample Success-Response:
//     *      200 OK
//     *      ......
//     */
//    @Get(route = "goods")
//    public void getGoodsList() {
//        Transaction tx = dbSession.beginTransaction();
//        List<GoodsEntity> goodsList = (List<GoodsEntity>)dbSession.createQuery(
//            "select goods from GoodsEntity goods"
//        ).setFirstResult(0).setMaxResults(20).list();
//        tx.commit();
//
//        webContext.response.setBody(JSON.toJSONString(
//            goodsList.stream().map(goods -> new GoodsViewModel(goods)).toArray()
//        ));
//    }

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
     * WebResponse res = api.post("/store/cart", goodsId);
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Post(route = "cart")
    public void addToCart() {
        String goodsId = webContext.request.getBody();
        CartRecordEntity record = account.getCartRecordsByCampusCardId().stream()
            .filter(r -> !r.getIsPurchased())
            .filter(r -> r.getGoodsByGoodsId().getGoodsId().equals(goodsId))
            .findFirst().orElse(new CartRecordEntity(goodsId, account.getCampusCardId()));

        // 不管是购物车中已有记录还是新生成的，自增1即可
        record.incGoodsCnt();
        dbSession.beginTransaction();
        dbSession.update(record);
        dbSession.getTransaction().commit();
    }

    /**
     * @apiGroup Store
     * @api {post} /store/purchase Purchase
     * @apiPermission student
     * @apiDescription 支付 删除CartRecord表相应购物车数据；Bank表里余额需要修改
     * @apiParamExample Code Snippets
     * WebResponse res = api.post("/store/purchase", JSON.toJSONString(List<String>, String.class));
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Post(route = "purchase")
    public String purchase() {
        dbSession.beginTransaction();
        double total_price = 0.0;
        List<CartRecordEntity> records = new ArrayList<>();
        for (String recordId : webContext.request.deserializeList(String.class)) {
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
        bank_record.setBankAccountByBankAccountId(bank);
        bank_record.setDeposit((int) total_price);
        bank_record.setDepositTime(new Timestamp(System.currentTimeMillis()));
        dbSession.save(bank_record);
        dbSession.getTransaction().commit();
        return "OK";
    }

    /**
     * @apiGroup Store
     * @api {patch} /store/cart ModifyCart
     * @apiPermission student
     * @apiDescription 增加商品数量(cart record已有该商品)
     * @apiParamExample Code Snippets
<<<<<<< HEAD
     * WebResponse res = api.patch("/store/cart", "{"uuid":uuid, "count":count}");
=======
     * WebResponse res = api.patch("/store/cart", "{"uid":uid, "count":"count"}");
>>>>>>> 32eade46167b4f10c72e5bcb7133527268b07afd
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Patch(route = "cart")
    public void modifyCart() {
        CartRecordViewModel request = webContext.request.deserializeBody(CartRecordViewModel.class);
        dbSession.beginTransaction();
        CartRecordEntity record = dbSession.get(CartRecordEntity.class, request.getCartRecordID());
        if (record.getCartRecordId() != null && !record.getIsPurchased()) {
            record.setGoodsCnt(request.getGoodsCount());
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
     * WebResponse res = api.post("/store/clear", "["uuid1", "uuid2"]");
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Post(route = "clear")
    public void deleteCartGoods() {
        dbSession.beginTransaction();
        for (String recordId : webContext.request.deserializeList(String.class)) {
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


}