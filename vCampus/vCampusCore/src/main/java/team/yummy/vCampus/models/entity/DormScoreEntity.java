package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "DormScore", schema = "\".\"", catalog = "\".\"")
public class DormScoreEntity {
    private Integer id;
    private String dormId;
    private Timestamp scoringDate;
    private String score;
    private DormEntity dormByDormId;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DormID")
    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

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
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DormScoreEntity that = (DormScoreEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dormId != null ? !dormId.equals(that.dormId) : that.dormId != null) return false;
        if (scoringDate != null ? !scoringDate.equals(that.scoringDate) : that.scoringDate != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dormId != null ? dormId.hashCode() : 0);
        result = 31 * result + (scoringDate != null ? scoringDate.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "DormID", referencedColumnName = "DormID")
    public DormEntity getDormByDormId() {
        return dormByDormId;
    }

    public void setDormByDormId(DormEntity dormByDormId) {
        this.dormByDormId = dormByDormId;
    }
}
