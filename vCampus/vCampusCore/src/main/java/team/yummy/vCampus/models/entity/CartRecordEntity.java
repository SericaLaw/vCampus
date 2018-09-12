package team.yummy.vCampus.models.entity;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import team.yummy.vCampus.models.viewmodel.AccountViewModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "CartRecord", schema = "\".\"", catalog = "\".\"")
public class CartRecordEntity {
    private Timestamp createdTime;
    private Integer goodsCnt;
    private Boolean isSel;
    private Boolean isPurchased;
    private String cartRecordId;
    private AccountEntity accountByCampusCardId;
    private GoodsEntity goodsByGoodsId;

    public CartRecordEntity() {}

    public CartRecordEntity(String goodsId, String campusCardId) {
        this.goodsByGoodsId = new GoodsEntity();
        this.goodsByGoodsId.setGoodsId(goodsId);
        this.accountByCampusCardId = new AccountEntity();
        this.accountByCampusCardId.setCampusCardId(campusCardId);
        this.cartRecordId = UUID.randomUUID().toString();
        this.createdTime = new Timestamp(System.currentTimeMillis());
        this.goodsCnt = 0;
        this.isSel = true;
        this.isPurchased = false;
    }

    @Basic
    @Column(name = "CreatedTime")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "GoodsCnt")
    public Integer getGoodsCnt() {
        return goodsCnt;
    }

    public void setGoodsCnt(Integer goodsCnt) {
        this.goodsCnt = goodsCnt;
    }

    public void incGoodsCnt() { this.goodsCnt += 1; }

    public void decGoodsCnt() { this.goodsCnt -= 1; }

    @Basic
    @Column(name = "IsSel")
    public Boolean getIsSel() {
        return isSel;
    }

    public void setIsSel(Boolean sel) {
        isSel = sel;
    }

    @Id
    @Column(name = "CartRecordID")
    public String getCartRecordId() {
        return cartRecordId;
    }

    public void setCartRecordId(String cartRecordId) {
        this.cartRecordId = cartRecordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartRecordEntity that = (CartRecordEntity) o;
        return Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(goodsCnt, that.goodsCnt) &&
                Objects.equals(isSel, that.isSel) &&
                Objects.equals(cartRecordId, that.cartRecordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdTime, goodsCnt, isSel, cartRecordId);
    }

    @ManyToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID", nullable = false)
    public AccountEntity getAccountByCampusCardId() {
        return accountByCampusCardId;
    }

    public void setAccountByCampusCardId(AccountEntity accountByCampusCardId) {
        this.accountByCampusCardId = accountByCampusCardId;
    }

    @ManyToOne
    @JoinColumn(name = "GoodsID", referencedColumnName = "GoodsID")
    public GoodsEntity getGoodsByGoodsId() {
        return goodsByGoodsId;
    }

    public void setGoodsByGoodsId(GoodsEntity goodsByGoodsId) {
        this.goodsByGoodsId = goodsByGoodsId;
    }

    @Basic
    @Column(name = "IsPurchased")
    public Boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Boolean purchased) {
        isPurchased = purchased;
    }
}
