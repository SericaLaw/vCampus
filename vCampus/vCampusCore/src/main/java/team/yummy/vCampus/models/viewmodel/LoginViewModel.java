package team.yummy.vCampus.models.viewmodel;

/**
 * 登录页面的视图模型类，用于前端展示用户名和密码以并作为与后端数据交互的媒介
 * @author Vigilans
 */
public class LoginViewModel {
    private String campusCardId;
    private String password;

    public LoginViewModel(String campusCardId, String password) {
        this.campusCardId = campusCardId;
        this.password = password;
    }

    public String getCampusCardId() {
        return campusCardId;
    }

    public void setCampusCardId(String campusCardId) {
        this.campusCardId = campusCardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
