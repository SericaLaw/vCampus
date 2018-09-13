package team.yummy.vCampus.models.viewmodel;
import java.util.*;
/**
 * 银行信息表项的视图模型类，用于前端展示银行账户信息
 * @author Dailin
 */
public class BankInfoViewModel{
    private String campusCardID;
    private double balance;
    private String bankAccount;
    private List<BankRecordViewModel> bankRecordList;

    public BankInfoViewModel(String campusCardID, double balance, String bankAccount, List<BankRecordViewModel> bankRecordList){
        this.campusCardID = campusCardID;
        this.balance = balance;
        this.bankAccount = bankAccount;
        this.bankRecordList = bankRecordList;
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

    public String getBankAccount(){
        return this.bankAccount;
    }

    public void setBankAccount(String bankAccount){
        this.bankAccount = bankAccount;
    }

    public List<BankRecordViewModel> getBankList(){
        return this.bankRecordList;
    }

    public void setBankList(List<BankRecordViewModel> bankRecordList){
        this.bankRecordList = bankRecordList;
    }
}