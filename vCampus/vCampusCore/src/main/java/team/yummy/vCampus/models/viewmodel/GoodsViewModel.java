package team.yummy.vCampus.models.viewmodel;

import java.util.UUID;

/**
 * @author Dailin
 * 商店表项类
 * 存放关联查询的结果，用于前端展示商店首页商品信息
 */

public class GoodsViewModel {
    private String goodsID = UUID.randomUUID().toString();
    private String info;
    private String goodsName;
    private double price;
    private String imgUrl;
    private int tag;

    public GoodsViewModel() {}
    public GoodsViewModel(String goodsID, String goodsName, double price, String imgUrl, int tag) {
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.price = price;
        this.imgUrl = imgUrl;
        this.tag = tag;
    }

    public String getGoodsID(){
        return this.goodsID;
    }

    public void setGoodsID(String goodsID){
        this.goodsID = goodsID;
    }

    public String getGoodsName(){
        return this.goodsName;
    }

    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public int getTag(){
        return this.tag;
    }

    public void setTag(int tag){
        this.tag = tag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}