package team.yummy.vCampus.models.viewmodel;


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
