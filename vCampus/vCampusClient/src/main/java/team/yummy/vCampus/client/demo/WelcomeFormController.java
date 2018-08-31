package team.yummy.vCampus.client.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.web.WebResponse;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class WelcomeFormController implements Controllable,Initializable {
    @FXML private Text errorText;
    @FXML private TextField loginUsername;
    @FXML private PasswordField loginPassword;
    @FXML private GridPane loginGrid;
    @FXML private GridPane registerGrid;


    private String username;
    private String password;

    StageController stageController;
    Api api;
    String authData;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @Override
    public void setApi(Api api) {
        this.api = api;
    }

    @Override
    public void setAuthData(String authData) {
        this.authData = authData;
    }

    @FXML
    protected void switchToRegister(ActionEvent actionEvent) {
        loginGrid.setVisible(false);
        registerGrid.setVisible(true);
    }

    @FXML
    protected void login(ActionEvent actionEvent) {
        username = loginUsername.getText();
        password = loginPassword.getText();
        if(username.length() == 0 || password.length() == 0)
            errorText.setText("用户名和密码不能为空");


        WebResponse res = api.post("/account/login", String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password));
        if(res.getStatusCode().equals("200")) {
            // 设置权限
            Map<String, String> resData = res.data(Map.class);
            // 窗口间数据通信
            stageController.authData = res.getJsonData();
            stageController.api.setAuth(resData.get("Username"), resData.get("Password"));

            // 打开mainForm，同时关闭welcomeForm
            stageController.setStage(App.mainFormViewName, App.welcomeFormViewName);

        }
        else if(res.getStatusCode().equals("404"))
            errorText.setText("用户不存在");
        else if(res.getStatusCode().equals("403"))
            errorText.setText("密码错误");
    }
    @FXML
    protected void switchToLogin(ActionEvent actionEvent) {
        registerGrid.setVisible(false);
        loginGrid.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
