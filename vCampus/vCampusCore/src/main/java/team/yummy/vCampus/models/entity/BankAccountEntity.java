package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "BankAccount", schema = "\".\"", catalog = "\".\"")
public class BankAccountEntity {
    private Double balance;
    private String campusCardId;
    private AccountEntity accountByCampusCardId;
    private Collection<BankRecordEntity> bankRecordsByCampusCardId;

    @Basic
    @Column(name = "Balance")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }

    @OneToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID", nullable = false)
    public AccountEntity getAccountByCampusCardId() {
        return accountByCampusCardId;
    }

    public void setAccountByCampusCardId(AccountEntity accountByCampusCardId) {
        this.accountByCampusCardId = accountByCampusCardId;
    }

    @OneToMany(mappedBy = "bankAccountByCampusCardId")
    public Collection<BankRecordEntity> getBankRecordsByCampusCardId() {
        return bankRecordsByCampusCardId;
    }

    public void setBankRecordsByCampusCardId(Collection<BankRecordEntity> bankRecordsById) {
        this.bankRecordsByCampusCardId = bankRecordsById;
    }

    @Id
    @Column(name = "CampusCardID")
    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }
}
