package team.yummy.vCampus.models.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.UUID;

@Entity
@Table(name = "CourseRecord", schema = "\".\"", catalog = "\".\"")
public class CourseRecordEntity {
    private Integer score;
    private String semester;
    private String scoreType;
    private String id = UUID.randomUUID().toString();
    private AccountEntity accountByCampusCardId;
    private CourseEntity courseByCourseId;

    @Basic
    @Column(name = "Score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "Semester")
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Basic
    @Column(name = "ScoreType")
    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
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

        CourseRecordEntity that = (CourseRecordEntity) o;

        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) return false;
        if (scoreType != null ? !scoreType.equals(that.scoreType) : that.scoreType != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (scoreType != null ? scoreType.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CampusCardID", referencedColumnName = "CampusCardID", nullable = false, insertable = false, updatable = false)
    public AccountEntity getAccountByCampusCardId() {
        return accountByCampusCardId;
    }

    public void setAccountByCampusCardId(AccountEntity accountByCampusCardId) {
        this.accountByCampusCardId = accountByCampusCardId;
    }

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false, insertable = false, updatable = false)
    public CourseEntity getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(CourseEntity courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }
}
