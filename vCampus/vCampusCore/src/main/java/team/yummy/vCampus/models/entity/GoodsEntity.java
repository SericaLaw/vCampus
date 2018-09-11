package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Goods", schema = "\".\"", catalog = "\".\"")
public class GoodsEntity {
    private String goodsName;
    private String info;
    private Short tag;
    private String imgUrl;
    private Double price;
    private String goodsId;
    private Collection<CartRecordEntity> cartRecordsByGoodsId;

    @Basic
    @Column(name = "GoodsName")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "Info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "Tag")
    public Short getTag() {
        return tag;
    }

    public void setTag(Short tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "ImgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "Price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Id
    @Column(name = "GoodsID")
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsEntity that = (GoodsEntity) o;
        return Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(info, that.info) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(imgUrl, that.imgUrl) &&
                Objects.equals(price, that.price) &&
                Objects.equals(goodsId, that.goodsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsName, info, tag, imgUrl, price, goodsId);
    }

    @OneToMany(mappedBy = "goodsByGoodsId")
    public Collection<CartRecordEntity> getCartRecordsByGoodsId() {
        return cartRecordsByGoodsId;
    }

    public void setCartRecordsByGoodsId(Collection<CartRecordEntity> cartRecordsByGoodsId) {
        this.cartRecordsByGoodsId = cartRecordsByGoodsId;
    }
}
