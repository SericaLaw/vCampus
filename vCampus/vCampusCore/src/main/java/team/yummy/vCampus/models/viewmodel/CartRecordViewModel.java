package team.yummy.vCampus.models.viewmodel;
import java.sql.Timestamp;

/**
 * @author Dailin
 * 购物车表项类
 * 存放关联查询的结果，用于前端展示购物车内信息
 */


public class CartRecordViewModel {
    private int cartRecordID;
    private String campusCardID;
    private int goodsID;
    private String goodsName;
    private double price;
    private String info;
    private String imgUrl;
    private int goodsCount;
    private Boolean isSelected;
    private Timestamp createdTime;

    public CartRecordViewModel(int cartRecordID, String campusCardID,int goodsID, String goodsName,double price,String info,String imgUrl,int goodsCount, Boolean isSelected, Timestamp createdTime){
        this.cartRecordID = cartRecordID;
        this.campusCardID = campusCardID;
        this.goodsID = goodsID;
        this.goodsName = goodsName;
        this.price = price;
        this.info = info;
        this.imgUrl = imgUrl;
        this.goodsCount = goodsCount;
        this.isSelected = isSelected;
        this.createdTime = createdTime;   
    }

    public int getCartRecordID(){
        return this.cartRecordID;
    }

    public String getCampusCardID() { 
        return this.campusCardID; 
    }

    public void setCampusCardId(String campusCardID) { 
        this.campusCardID = campusCardID; 
    }

    public int getGoodsID() { 
        return this.goodsID; 
    }

    public void setGoodsID(int goodsID) { 
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

    public String getInfo(){
        return this.info;
    }
    
    public void setInfo(String info){
        this.info = info;
    }

    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public int getGoodsCount(){
        return this.goodsCount;
    }

    public void setGoodsCount(int goodsCount){
        this.goodsCount = goodsCount;
    }

    public Boolean getIsSelected(){
        return this.isSelected;
    }

    public void setIsSelected(Boolean isSelected){
        this.isSelected = isSelected;
    }

    public Timestamp getCreatedTime(){
        return this.createdTime;
    }

    public void setCreatedTime(Timestamp createdTime){
        this.createdTime = createdTime;
    }
}