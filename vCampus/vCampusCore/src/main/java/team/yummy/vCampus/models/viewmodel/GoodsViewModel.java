package team.yummy.vCampus.models.viewmodel;

import team.yummy.vCampus.models.entity.GoodsEntity;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * 商店表项的视图模型类，用于前端展示商店首页商品信息
 * @author Dailin
 */

 public class GoodsViewModel{
    private String goodsId = UUID.randomUUID().toString();
    private String goodsName;
    private String info;
    private double price;
    private String imgUrl;
    private int tag;

    public GoodsViewModel() {}

    public GoodsViewModel(String goodsId, String goodsName, double price, String imgUrl, int tag){
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.imgUrl = imgUrl;
        this.tag = tag;
    }

    public GoodsViewModel(GoodsEntity entity) {
        for (Field field : this.getClass().getFields()) {
            try {
                field.set(this, entity.getClass().getMethod(
                    "get"
                    + Character.toUpperCase(field.getName().charAt(0))
                    + field.getName().substring(1)
                ).invoke(entity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getGoodsId(){
        return this.goodsId;
    }

    public void setGoodsId(String goodsId){
        this.goodsId = goodsId;
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

