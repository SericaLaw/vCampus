package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Dorm", schema = "\".\"", catalog = "\".\"")
public class DormEntity {
    private String dormId;
    private Integer bedNo;
    private AccountEntity accountByCampusCardId;
    private Collection<DormRecordEntity> dormRecordsByDormId;

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
        return Objects.equals(dormId, that.dormId) &&
                Objects.equals(bedNo, that.bedNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dormId, bedNo);
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
    public Collection<DormRecordEntity> getDormRecordsByDormId() {
        return dormRecordsByDormId;
    }

    public void setDormRecordsByDormId(Collection<DormRecordEntity> dormScoresByDormId) {
        this.dormRecordsByDormId = dormScoresByDormId;
    }
}
