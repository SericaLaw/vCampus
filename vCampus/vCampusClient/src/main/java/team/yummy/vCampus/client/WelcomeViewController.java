package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import team.yummy.vCampus.models.viewmodel.LoginViewModel;
import team.yummy.vCampus.web.WebResponse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeViewController extends ViewController implements Initializable {

    @FXML public TextField login_TcampusCardId;
    @FXML public PasswordField login_Tpassword;
    @FXML public TextField register_Tfirstname;
    @FXML public TextField register_Tlastname;
    @FXML public TextField register_Tcard;
    @FXML public TextField register_Tusername;
    @FXML public TextField register_Tpassword;

    @FXML private AnchorPane welcomebackground;
    @FXML private AnchorPane loginpane;
    @FXML private AnchorPane registerpane;

    @FXML private Label windowclose;
    @FXML private Label windowmini;

    @FXML private ToggleGroup radiogroup;
    @FXML private JFXRadioButton register_radiostudent;
    @FXML private JFXRadioButton register_radioadminis;
    @FXML private JFXRadioButton register_radioteacher;


    public double xOffset = 0;
    public double yOffset = 0;

    public WelcomeViewController() {
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    protected void window_close(MouseEvent mouseEvent)
    {
        Stage stage = (Stage)windowclose.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void window_mini(MouseEvent mouseEvent)
    {
        Stage stage = (Stage)windowmini.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        _login();
    }

    @FXML
    public void presslogin(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            _login();
        }
    }

    @FXML
    protected void titlepressed(MouseEvent mouseEvent) {
        xOffset = mouseEvent.getSceneX();
        yOffset = mouseEvent.getSceneY();
    }

    @FXML
    protected void titledragged(MouseEvent mouseEvent) {
        Stage stage = (Stage)welcomebackground.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - xOffset);
        stage.setY(mouseEvent.getScreenY() - yOffset);
    }

    public void signup(ActionEvent actionEvent)
    {
        String firstName=register_Tfirstname.getText();
        String lastname=register_Tlastname.getText();
        String card=register_Tcard.getText();
        String username=register_Tusername.getText();
        String password=register_Tpassword.getText();

        JFXSnackbar bar = new JFXSnackbar(registerpane);
        if(firstName.length() == 0 || lastname.length()==0||card.length()==0||username.length()==0||password.length() == 0||radiogroup.getSelectedToggle()==null)
            bar.enqueue(new JFXSnackbar.SnackbarEvent("请完善所有信息"));

        else
        {
            register_radiostudent.setUserData("student");
            register_radioadminis.setUserData("administrator");
            register_radioteacher.setUserData("teacher");
            String role=radiogroup.getSelectedToggle().getUserData().toString();
            JSONObject info = new JSONObject();
            info.put("CampusCardID",card);
            info.put("Username",username);
            info.put("Password",password);
            info.put("FirstName",firstName);
            info.put("LastName",lastname);
            info.put("role",role);
            WebResponse res = api.post("/account",info.toJSONString());
            switch (res.getStatusCode())
            {
                case"201":
                    //切换到登录界面
                    registerpane.setVisible(false);
                    loginpane.setVisible(true);
                    break;
                case"403":
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("该账号已存在！"));
                    break;
            }

        }
    }

    public void switchToSignup(ActionEvent actionEvent)
    {
        registerpane.setVisible(true);
        loginpane.setVisible(false);

    }

    public void switchToLogin(ActionEvent actionEvent)
    {
        registerpane.setVisible(false);
        loginpane.setVisible(true);
    }

    private void _login() {
        String username = login_TcampusCardId.getText();
        String password = login_Tpassword.getText();
        JFXSnackbar bar = new JFXSnackbar(loginpane);

        if(username.length() == 0 || password.length() == 0)
            bar.enqueue(new JFXSnackbar.SnackbarEvent("用户名密码不能为空"));


        else
        {
            LoginViewModel login = new LoginViewModel(username, password);
            WebResponse res = api.post("/account/login", JSON.toJSONString(login));
            switch (res.getStatusCode()) {
                case "200":
                    setAccountJsonData(res.getBody());
                    // 切换页面
                    stageController.setStage(App.ADMIN_VIEW_NAME, App.WELCOME_VIEW_NAME);
                    //让后面账户登出回到这个界面时上面没有之前的账户和密码
                    login_Tpassword.setText("");
                    login_TcampusCardId.setText("");
                    break;
                case "404":
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("用户不存在"));
                    break;
                case "403":
                    bar.enqueue(new JFXSnackbar.SnackbarEvent("密码错误"));
                    break;

            }
        }

    }
}
