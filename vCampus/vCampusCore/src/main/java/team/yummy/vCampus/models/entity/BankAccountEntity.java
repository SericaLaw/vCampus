package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "BankAccount", schema = "\".\"", catalog = "\".\"")
public class BankAccountEntity {
    private Double balance;
    private String id;
    private AccountEntity accountByCampusCardId;
    private Collection<BankRecordEntity> bankRecordsById;

    @Basic
    @Column(name = "Balance")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return Objects.equals(balance, that.balance) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, id);
    }

    @OneToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID", nullable = false)
    public AccountEntity getAccountByCampusCardId() {
        return accountByCampusCardId;
    }

    public void setAccountByCampusCardId(AccountEntity accountByCampusCardId) {
        this.accountByCampusCardId = accountByCampusCardId;
    }

    @OneToMany(mappedBy = "bankAccountByBankAccountId")
    public Collection<BankRecordEntity> getBankRecordsById() {
        return bankRecordsById;
    }

    public void setBankRecordsById(Collection<BankRecordEntity> bankRecordsById) {
        this.bankRecordsById = bankRecordsById;
    }
}
