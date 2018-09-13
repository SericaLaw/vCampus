package team.yummy.vCampus.models.viewmodel;

/**
 * 宿舍表项的视图模型类，用于前端展示宿舍评分和水电信息
 * @author Dailin
 */
import team.yummy.vCampus.models.entity.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class DormInfoViewModel {
    private String campusCardID;
    private String dormID;
    private Integer bedNo;
    private List<DormRecordViewModel> records;

    public DormInfoViewModel() {}

    public DormInfoViewModel(DormEntity entity) {
        campusCardID = entity.getAccountByCampusCardId().getCampusCardId();
        dormID = entity.getDormId();
        bedNo = entity.getBedNo();
        records = entity.getDormRecordsByDormId().stream().map(d -> new DormRecordViewModel(d)).collect(Collectors.toList());
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

    public List<DormRecordViewModel> getRecords() {
        return records;
    }

    public void setRecords(List<DormRecordViewModel> records) {
        this.records = records;
    }

    public Integer getBedNo() {
        return bedNo;
    }

    public void setBedNo(Integer bedNo) {
        this.bedNo = bedNo;
    }
}
