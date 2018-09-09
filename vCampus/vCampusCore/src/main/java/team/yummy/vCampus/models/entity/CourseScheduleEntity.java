package team.yummy.vCampus.models.entity;

import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.UUID;
import java.util.stream.IntStream;

@Entity
@Table(name = "CourseSchedule", schema = "\".\"", catalog = "\".\"")
public class CourseScheduleEntity {
    private String id = UUID.randomUUID().toString();
    private Integer weekDay;
    private Integer spanStart;
    private Integer spanEnd;
    private CourseEntity courseByCourseId;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    @Basic
    @Column(name = "WeekDay")
    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    @Basic
    @Column(name = "SpanStart")
    public Integer getSpanStart() {
        return spanStart;
    }

    public void setSpanStart(Integer spanStart) {
        this.spanStart = spanStart;
    }

    @Basic
    @Column(name = "SpanEnd")
    public Integer getSpanEnd() {
        return spanEnd;
    }

    public void setSpanEnd(Integer spanEnd) {
        this.spanEnd = spanEnd;
    }

    public int[] span() {
        return IntStream.range(spanStart, spanEnd + 1).toArray();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseScheduleEntity that = (CourseScheduleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (weekDay != null ? !weekDay.equals(that.weekDay) : that.weekDay != null) return false;
        if (spanStart != null ? !spanStart.equals(that.spanStart) : that.spanStart != null) return false;
        if (spanEnd != null ? !spanEnd.equals(that.spanEnd) : that.spanEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (weekDay != null ? weekDay.hashCode() : 0);
        result = 31 * result + (spanStart != null ? spanStart.hashCode() : 0);
        result = 31 * result + (spanEnd != null ? spanEnd.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false)
    public CourseEntity getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(CourseEntity courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }
}
