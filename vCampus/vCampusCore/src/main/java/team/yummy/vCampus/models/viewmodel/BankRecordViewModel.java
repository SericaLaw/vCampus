package team.yummy.vCampus.models.viewmodel;
import java.util.*;
import java.sql.Timestamp;

/**
 * @author Dailin
 * 交易信息表项�?
 * 用于前端展示银行账户交易记录
 */
public class BankRecordViewModel{
    private int bankRecordID;
    private String campusCardID;
    private double deposit;
    private Timestamp depositTime;

    public BankRecordViewModel(int bankRecordID,String campusCardID,double deposit,Timestamp depositTime){
        this.bankRecordID = bankRecordID;
        this.campusCardID = campusCardID;
        this.deposit = deposit;
        this.depositTime = depositTime;
    }

    public int getBankRecordID(){
        return this.bankRecordID;
    }

    public void setBankRecordID(int bankRecordID){
        this.bankRecordID = bankRecordID;
    }

    public String getCampusCardID() { 
        return this.campusCardID; 
    }

    public void setCampusCardID(String campusCardID) { 
        this.campusCardID = campusCardID; 
    }

    public double getDeposit(){
        return this.deposit;
    }

    public void setDeposit(double deposit){
        this.deposit = deposit;
    }

    public Timestamp getDepostTime(){
        return this.depositTime;
    }

    public void setDepositTime(Timestamp depositTime){
        this.depositTime = depositTime;
    }

}

