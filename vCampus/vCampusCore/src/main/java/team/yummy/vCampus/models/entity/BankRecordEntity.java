package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "BankRecord", schema = "\".\"", catalog = "\".\"")
public class BankRecordEntity {
    private String id;
    private Double incomeAndExpense;
    private Timestamp recordTime;
    private String reason;
    private BankAccountEntity bankAccountByCampusCardId;

    @Basic
    @Column(name = "IncomeAndExpense")
    public Double getIncomeAndExpense() {
        return incomeAndExpense;
    }

    public void setIncomeAndExpense(Double deposit) {
        this.incomeAndExpense = deposit;
    }

    @Basic
    @Column(name = "RecordTime")
    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp depositTime) {
        this.recordTime = depositTime;
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
        return Objects.equals(incomeAndExpense, that.incomeAndExpense) &&
                Objects.equals(recordTime, that.recordTime) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incomeAndExpense, recordTime, id);
    }

    @ManyToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID")
    public BankAccountEntity getBankAccountByCampusCardId() {
        return bankAccountByCampusCardId;
    }

    public void setBankAccountByCampusCardId(BankAccountEntity bankAccountByBankAccountId) {
        this.bankAccountByCampusCardId = bankAccountByBankAccountId;
    }

    @Basic
    @Column(name = "Reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
