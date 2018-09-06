package team.yummy.vCampus.models;

/**
 * @author Serica
 * 暂时性的Goods类
 * 理应还要设置一个购物车的商品类
 */
public class Goods {

    private String goodsID;
    private String name;
    private String price;
    private String info;
    private String imgUrl;


    public Goods(String goodsID, String name, String price, String info, String imgUrl) {
        this.goodsID = goodsID;
        this.name = name;
        this.price = price;
        this.info = info;
        this.imgUrl = imgUrl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return String.format("Goods [ goodsID = %s, name = %s, price = %s, info = %s, imageUrl = %s ]", goodsID, name, price, info, imgUrl);
    }
}