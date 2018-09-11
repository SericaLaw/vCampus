package team.yummy.vCampus.models.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

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
    private Collection<CartRecordEntity> cartRecordsByCampusCardId;
    private Collection<CourseRecordEntity> courseRecordsByCampusCardId;
    private Collection<DormEntity> dormsByCampusCardId;
    private StuInfoEntity stuInfoByCampusCardId;
    private BankAccountEntity bankAccountByCampusCardId;

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
        return Objects.equals(nickname, that.nickname) &&
                Objects.equals(password, that.password) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(campusCardId, that.campusCardId) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, password, lastName, firstName, campusCardId, role);
    }

    @OneToMany(mappedBy = "accountByCampusCardId")
    public Collection<BorrowRecordEntity> getBorrowRecordsByCampusCardId() {
        return borrowRecordsByCampusCardId;
    }

    public void setBorrowRecordsByCampusCardId(Collection<BorrowRecordEntity> borrowRecordsByCampusCardId) {
        this.borrowRecordsByCampusCardId = borrowRecordsByCampusCardId;
    }

    @OneToMany(mappedBy = "accountByCampusCardId")
    public Collection<CartRecordEntity> getCartRecordsByCampusCardId() {
        return cartRecordsByCampusCardId;
    }

    public void setCartRecordsByCampusCardId(Collection<CartRecordEntity> cartRecordsByCampusCardId) {
        this.cartRecordsByCampusCardId = cartRecordsByCampusCardId;
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

    @OneToOne(mappedBy = "accountByCampusCardId")
    public BankAccountEntity getBankAccountByCampusCardId() {
        return bankAccountByCampusCardId;
    }

    public void setBankAccountByCampusCardId(BankAccountEntity bankAccountByCampusCardId) {
        this.bankAccountByCampusCardId = bankAccountByCampusCardId;
    }
}