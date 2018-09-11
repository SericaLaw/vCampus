package team.yummy.vCampus.models.viewmodel;

/**
 * @author Dailin
 * 宿舍表项类
 * 存放关联查询的结果，用于前端展示宿舍评分&水电信息
 */
import team.yummy.vCampus.models.entity.*;
import java.sql.Timestamp;


public class DormInfoViewModel {
    private String campusCardID;
    private String dormID;
    private int score;
    private Timestamp scoringDate;
    private double waterAndElectricityFees;

    public DormInfoViewModel() {}

    public DormInfoViewModel(String campusCardID, String dormID,int score, Timestamp scoringDate, double waterAndElectricityFees){
        this.campusCardID = campusCardID;
        this.dormID = dormID;
        this.score = score;
        this.scoringDate = scoringDate;
        this.waterAndElectricityFees = waterAndElectricityFees;
    }

    public String getCampusCardID() { 
        return this.campusCardID; 
    }

    public void setCampusCardId(String campusCardID) { 
        this.campusCardID = campusCardID; 
    }

    public String getDormID() { 
        return this.dormID; 
    }

    public void setDormID(String dormID) { 
        this.dormID = dormID; 
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public Timestamp getScoringDate(){
        return this.scoringDate;
    }

    public void setScoringDate(Timestamp scoringDate){
        this.scoringDate = scoringDate;
    }

    public double getWaterAndElectricityFees(){
        return this.waterAndElectricityFees;
    }

    public void setWaterAndElectricityFees(double waterAndElectricityFees){
        this.waterAndElectricityFees = waterAndElectricityFees;
    }

    
}
