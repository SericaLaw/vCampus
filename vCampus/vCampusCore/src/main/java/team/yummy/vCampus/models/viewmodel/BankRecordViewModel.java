package team.yummy.vCampus.models.viewmodel;
import team.yummy.vCampus.models.entity.BankRecordEntity;

import java.util.*;
import java.sql.Timestamp;

/**
 * @author Dailin
 * 交易信息表项?
 * 用于前端展示银行账户交易记录
 */
public class BankRecordViewModel {
    private Double incomeAndExpense;
    private Timestamp recordTime;
    private String reason;

    public BankRecordViewModel() {}

    public BankRecordViewModel(BankRecordEntity entity) {
        this.incomeAndExpense = entity.getIncomeAndExpense();
        this.recordTime = entity.getRecordTime();
        this.reason = entity.getReason();
    }

    public Double getIncomeAndExpense() {
        return incomeAndExpense;
    }

    public void setIncomeAndExpense(Double incomeAndExpense) {
        this.incomeAndExpense = incomeAndExpense;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

