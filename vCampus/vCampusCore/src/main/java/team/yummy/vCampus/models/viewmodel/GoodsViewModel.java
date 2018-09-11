package team.yummy.vCampus.models.viewmodel;

/**
 * @author Dailin
 * 商店表项类
 * 存放关联查询的结果，用于前端展示商店首页商品信息
 */

 public class GoodsViewModel{
    private int goodsID;
    private String goodsName;
    private double price;
    private String imgUrl;
    private int tag;

    public GoodsViewModel(int goodsID, String goodsName,double price,String imgUrl,int tag){
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.price = price;
        this.imgUrl = imgUrl;
        this.tag = tag;
    }

    public int getGoodsID(){
        return this.goodsID;
    }

    public void setGoodsID(int goodsID){
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
    


 }