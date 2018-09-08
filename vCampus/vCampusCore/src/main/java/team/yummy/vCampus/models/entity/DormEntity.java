package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Dorm", schema = "\".\"", catalog = "\".\"")
public class DormEntity {
    private String campusCardId;
    private String dormId;
    private Integer bedNo;
    private AccountEntity accountByCampusCardId;
    private Collection<DormScoreEntity> dormScoresByDormId;

    @Basic
    @Column(name = "CampusCardID")
    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }

    @Id
    @Column(name = "DormID")
    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    @Basic
    @Column(name = "BedNo")
    public Integer getBedNo() {
        return bedNo;
    }

    public void setBedNo(Integer bedNo) {
        this.bedNo = bedNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DormEntity that = (DormEntity) o;

        if (campusCardId != null ? !campusCardId.equals(that.campusCardId) : that.campusCardId != null) return false;
        if (dormId != null ? !dormId.equals(that.dormId) : that.dormId != null) return false;
        if (bedNo != null ? !bedNo.equals(that.bedNo) : that.bedNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = campusCardId != null ? campusCardId.hashCode() : 0;
        result = 31 * result + (dormId != null ? dormId.hashCode() : 0);
        result = 31 * result + (bedNo != null ? bedNo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID", nullable = false)
    public AccountEntity getAccountByCampusCardId() {
        return accountByCampusCardId;
    }

    public void setAccountByCampusCardId(AccountEntity accountByCampusCardId) {
        this.accountByCampusCardId = accountByCampusCardId;
    }

    @OneToMany(mappedBy = "dormByDormId")
    public Collection<DormScoreEntity> getDormScoresByDormId() {
        return dormScoresByDormId;
    }

    public void setDormScoresByDormId(Collection<DormScoreEntity> dormScoresByDormId) {
        this.dormScoresByDormId = dormScoresByDormId;
    }
}
