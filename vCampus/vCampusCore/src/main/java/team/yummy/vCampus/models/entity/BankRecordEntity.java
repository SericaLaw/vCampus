package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "BankRecord", schema = "\".\"", catalog = "\".\"")
public class BankRecordEntity {
    private Integer deposit;
    private Timestamp depositTime;
    private String id;
    private BankAccountEntity bankAccountByBankAccountId;

    @Basic
    @Column(name = "Deposit")
    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "DepositTime")
    public Timestamp getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(Timestamp depositTime) {
        this.depositTime = depositTime;
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
        BankRecordEntity that = (BankRecordEntity) o;
        return Objects.equals(deposit, that.deposit) &&
                Objects.equals(depositTime, that.depositTime) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deposit, depositTime, id);
    }

    @ManyToOne
    @JoinColumn(name = "BankAccountID", referencedColumnName = "ID")
    public BankAccountEntity getBankAccountByBankAccountId() {
        return bankAccountByBankAccountId;
    }

    public void setBankAccountByBankAccountId(BankAccountEntity bankAccountByBankAccountId) {
        this.bankAccountByBankAccountId = bankAccountByBankAccountId;
    }
}
