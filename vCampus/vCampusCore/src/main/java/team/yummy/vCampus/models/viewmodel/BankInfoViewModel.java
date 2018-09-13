package team.yummy.vCampus.models.viewmodel;
import team.yummy.vCampus.models.entity.BankAccountEntity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dailin
 * 银行信息表项?
 * 用于前端展示银行账户信息
 */
public class BankInfoViewModel{
    private String campusCardID;
    private double balance;
    private List<BankRecordViewModel> bankRecordList;

    public BankInfoViewModel(BankAccountEntity entity) {
        this.campusCardID = entity.getCampusCardId();
        this.balance = entity.getBalance();
        this.bankRecordList = entity.getBankRecordsByCampusCardId().stream().map(
            r -> new BankRecordViewModel(r)
        ).collect(Collectors.toList());
    }

    public String getCampusCardID() { 
        return this.campusCardID; 
    }

    public void setCampusCardId(String campusCardID) { 
        this.campusCardID = campusCardID; 
    }
    
    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public List<BankRecordViewModel> getBankList(){
        return this.bankRecordList;
    }

    public void setBankList(List<BankRecordViewModel> bankRecordList){
        this.bankRecordList = bankRecordList;
    }
}