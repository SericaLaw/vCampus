package team.yummy.vCampus.client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import team.yummy.vCampus.web.WebResponse;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeViewController extends ViewController implements Initializable {

    public TextField loginUsername;
    public PasswordField loginPassword;
    public GridPane loginGrid;
    public GridPane registerGrid;
    public Text errorText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void login(ActionEvent actionEvent) {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        if(username.length() == 0 || password.length() == 0)
            errorText.setText("用户名和密码不能为空");

        WebResponse res = api.post("/account/login", String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password));
        switch (res.getStatusCode()) {
            case "200":
                setAccountJsonData(res.getJsonData());
                api.setAuth(currentAccount.getUsername(), currentAccount.getCampusCardID(),currentAccount.getPassword());
                // 切换页面
                stageController.setStage(App.MAIN_VIEW_NAME, App.WELCOME_VIEW_NAME);
                break;
            case "404":
                errorText.setText("用户不存在");
                break;
            case "403":
                errorText.setText("密码错误");
                break;
        }

    }

    public void switchToRegister(ActionEvent actionEvent) {
        loginGrid.setVisible(false);
        registerGrid.setVisible(true);
    }

    public void switchToLogin(ActionEvent actionEvent) {
        registerGrid.setVisible(false);
        loginGrid.setVisible(true);
    }
}
