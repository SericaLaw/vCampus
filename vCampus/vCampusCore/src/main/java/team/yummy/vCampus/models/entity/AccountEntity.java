package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Account", schema = "\".\"", catalog = "\".\"")
public class AccountEntity {
    private String nickname;
    private String password;
    private String lastName;
    private String firstName;
    private String campusCardId;
    private String role;
    private Collection<BorrowRecordEntity> borrowRecordsByCampusCardId;
    private Collection<CourseRecordEntity> courseRecordsByCampusCardId;
    private Collection<DormEntity> dormsByCampusCardId;
    private StuInfoEntity stuInfoByCampusCardId;

    @Basic
    @Column(name = "Nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Id
    @Column(name = "CampusCardID")
    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }

    @Basic
    @Column(name = "Role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (campusCardId != null ? !campusCardId.equals(that.campusCardId) : that.campusCardId != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (campusCardId != null ? campusCardId.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "accountByCampusCardId")
    public Collection<BorrowRecordEntity> getBorrowRecordsByCampusCardId() {
        return borrowRecordsByCampusCardId;
    }

    public void setBorrowRecordsByCampusCardId(Collection<BorrowRecordEntity> borrowRecordsByCampusCardId) {
        this.borrowRecordsByCampusCardId = borrowRecordsByCampusCardId;
    }

    @OneToMany(mappedBy = "accountByCampusCardId")
    public Collection<CourseRecordEntity> getCourseRecordsByCampusCardId() {
        return courseRecordsByCampusCardId;
    }

    public void setCourseRecordsByCampusCardId(Collection<CourseRecordEntity> courseRecordsByCampusCardId) {
        this.courseRecordsByCampusCardId = courseRecordsByCampusCardId;
    }

    @OneToMany(mappedBy = "accountByCampusCardId")
    public Collection<DormEntity> getDormsByCampusCardId() {
        return dormsByCampusCardId;
    }

    public void setDormsByCampusCardId(Collection<DormEntity> dormsByCampusCardId) {
        this.dormsByCampusCardId = dormsByCampusCardId;
    }

    @OneToOne(mappedBy = "accountByCampusCardId")
    public StuInfoEntity getStuInfoByCampusCardId() {
        return stuInfoByCampusCardId;
    }

    public void setStuInfoByCampusCardId(StuInfoEntity stuInfoByCampusCardId) {
        this.stuInfoByCampusCardId = stuInfoByCampusCardId;
    }
}
