package team.yummy.vCampus.models.viewmodel;

import team.yummy.vCampus.models.entity.DormEntity;
import team.yummy.vCampus.models.entity.DormRecordEntity;

import java.sql.Timestamp;

public class DormRecordViewModel {
    private String id;
    private Timestamp scoringDate;
    private Integer score;
    private Double fees;

    public DormRecordViewModel(DormRecordEntity entity) {
        id = entity.getId();
        scoringDate = entity.getScoringDate();
        score = entity.getScore();
        fees = entity.getFees();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getScoringDate() {
        return scoringDate;
    }

    public void setScoringDate(Timestamp scoringDate) {
        this.scoringDate = scoringDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }
}
