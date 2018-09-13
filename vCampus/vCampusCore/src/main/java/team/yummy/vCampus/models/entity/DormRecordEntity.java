package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DormScore", schema = "\".\"", catalog = "\".\"")
public class DormRecordEntity {
    private String id;
    private Timestamp scoringDate;
    private Integer score;
    private Double fees;
    private DormEntity dormByDormId;

    @Basic
    @Column(name = "ScoringDate")
    public Timestamp getScoringDate() {
        return scoringDate;
    }

    public void setScoringDate(Timestamp scoringDate) {
        this.scoringDate = scoringDate;
    }

    @Basic
    @Column(name = "Score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
        DormRecordEntity that = (DormRecordEntity) o;
        return Objects.equals(scoringDate, that.scoringDate) &&
                Objects.equals(score, that.score) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoringDate, score, id);
    }

    @ManyToOne
    @JoinColumn(name = "DormID", referencedColumnName = "DormID")
    public DormEntity getDormByDormId() {
        return dormByDormId;
    }

    public void setDormByDormId(DormEntity dormByDormId) {
        this.dormByDormId = dormByDormId;
    }

    @Basic
    @Column(name = "Fees")
    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }
}
