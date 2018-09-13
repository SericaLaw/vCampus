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
    private Double balance;
    private List<BankRecordViewModel> bankRecordList;

    public BankInfoViewModel() {}

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
    
    public Double getBalance(){
        return this.balance;
    }

    public void setBalance(Double balance){
        this.balance = balance;
    }

    public List<BankRecordViewModel> getBankRecordList(){
        return this.bankRecordList;
    }

    public void setBankRecordList(List<BankRecordViewModel> bankRecordList){
        this.bankRecordList = bankRecordList;
    }
}