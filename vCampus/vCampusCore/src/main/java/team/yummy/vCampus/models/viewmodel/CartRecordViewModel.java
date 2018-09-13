package team.yummy.vCampus.models.viewmodel;
import team.yummy.vCampus.models.entity.CartRecordEntity;

import java.sql.Timestamp;

/**
 * 购物车表项的视图模型类，用于前端展示购物车内信息
 * @author Dailin
 */


public class CartRecordViewModel {
    private String cartRecordId;
    private String campusCardId;
    private String goodsId;
    private String goodsName;
    private Double price;
    private String info;
    private String imgUrl;
    private Integer goodsCount;
    private Boolean isSelected;
    private Timestamp createdTime;

    public CartRecordViewModel() {}

    public CartRecordViewModel(String cartRecordId, String campusCardId, String goodsId, String goodsName, Double price, String info, String imgUrl, Integer goodsCount, Boolean isSelected, Timestamp createdTime){
        this.cartRecordId = cartRecordId;
        this.campusCardId = campusCardId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.info = info;
        this.imgUrl = imgUrl;
        this.goodsCount = goodsCount;
        this.isSelected = isSelected;
        this.createdTime = createdTime;   
    }

    public CartRecordViewModel(CartRecordEntity record) {
        this.cartRecordId = record.getCartRecordId();
        this.campusCardId = record.getAccountByCampusCardId().getCampusCardId();
        this.goodsId = record.getGoodsByGoodsId().getGoodsId();
        this.goodsName = record.getGoodsByGoodsId().getGoodsName();
        this.price = record.getGoodsByGoodsId().getPrice();
        this.info = record.getGoodsByGoodsId().getInfo();
        this.imgUrl = record.getGoodsByGoodsId().getImgUrl();
        this.isSelected = record.getIsSel();
        this.createdTime = record.getCreatedTime();
    }

    public String getCartRecordID(){
        return this.cartRecordId;
    }

    public void setCartRecordID(String cartRecordID){
        this.cartRecordId = cartRecordID;
    }

    public String getCampusCardId() {
        return this.campusCardId;
    }

    public void setCampusCardId(String campusCardID) { 
        this.campusCardId = campusCardID;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
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