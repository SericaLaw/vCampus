package team.yummy.vCampus.models.viewmodel;

import team.yummy.vCampus.models.entity.AccountEntity;

/**
 * 账户的视图模型类，用于前端展示账户信息
 * @author Serica
 */
public class AccountViewModel {
    private String nickname;
    private String password;
    private String lastName;
    private String firstName;
    private String campusCardId;
    private String role;

    public AccountViewModel() {}

    public AccountViewModel(AccountEntity account) {
        this.campusCardId = account.getCampusCardId();
        this.password = account.getPassword();
        this.nickname = account.getNickname();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.role = account.getRole();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}