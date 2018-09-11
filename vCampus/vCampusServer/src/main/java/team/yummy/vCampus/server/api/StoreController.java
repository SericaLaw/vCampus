package team.yummy.vCampus.server.api;

import team.yummy.vCampus.server.annotation.*;

public class StoreController extends Controller {

    /**
     * @apiGroup Store
     * @api {get} /store/goods GetGoodsList
     * @apiPermission all
     * @apiDescription 获取商品列表
     * @apiSuccess List_GoodsViewModel List of GoodsViewModel
     * @apiParamExample Code Snippets
     * WebResponse res = api.get("/store/goods");
     * List<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Get(route = "goods")
    public void getGoodsList() {

    }

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

    }

    /**
     * @apiGroup Store
     * @api {post} /store/purchase Purchase
     * @apiPermission student
     * @apiDescription 支付 删除CartRecord表相应购物车数据；Bank表里余额需要修改
     * @apiParamExample Code Snippets
     * WebResponse res = api.post("/store/purchase", JSON.toJSONString(List<CartRecordViewModel>, CartRecordViewModel.class));
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Post(route = "purchase")
    public void purchase() {

    }

    /**
     * @apiGroup Store
     * @api {patch} /store/cart ModifyCart
     * @apiPermission student
     * @apiDescription 增加商品数量(cart record已有该商品)
     * @apiParamExample Code Snippets
     * WebResponse res = api.patch("/store/cart", "{"uid":uid, "count":"count"}");
     *
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Patch(route = "cart")
    public void modifyCart() {

    }

    /**
     * @apiGroup Store
     * @api {delete} /store/cart/{uid} DeleteCartGoods
     * @apiPermission student
     * @apiDescription 删除购物车商品
     * @apiParamExample Code Snippets
     * WebResponse res = api.delete("/store/cart/{uid}");
     * @apiSuccessExample Success-Response:
     *      200 OK
     *      ......
     */
    @Delete(route = "cart")
    public void deleteCartGoods() {

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
