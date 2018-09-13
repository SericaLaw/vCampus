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
    private double incomeAndExpense;
    private Timestamp recordTime;

    public BankRecordViewModel(BankRecordEntity entity) {
        this.incomeAndExpense = entity.getIncomeAndExpense();
        this.recordTime = entity.getRecordTime();
    }

    public double getIncomeAndExpense() {
        return incomeAndExpense;
    }

    public void setIncomeAndExpense(double incomeAndExpense) {
        this.incomeAndExpense = incomeAndExpense;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }
}

