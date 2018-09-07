package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import team.yummy.vCampus.web.WebResponse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeViewController extends ViewController implements Initializable {

    @FXML public TextField login_Tusername;
    @FXML public PasswordField login_Tpassword;
    @FXML public TextField register_Tfirstname;
    @FXML public TextField register_Tlastname;
    @FXML public TextField register_Tcard;
    @FXML public TextField register_Tusername;
    @FXML public TextField register_Tpassword;
    @FXML public Label LoginerrorText;
    @FXML public Label RegistererrorText;

    @FXML private AnchorPane loginpane;
    @FXML private AnchorPane registerpane;

    @FXML private Label windowclose;
    @FXML private Label windowmini;

    @FXML private ToggleGroup radiogroup;
    @FXML private JFXRadioButton register_radiostudent;
    @FXML private JFXRadioButton register_radioadminis;

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


    public void login(ActionEvent actionEvent) {
        String username = login_Tusername.getText();
        String password = login_Tpassword.getText();
        JFXSnackbar bar = new JFXSnackbar(loginpane);



        if(username.length() == 0 || password.length() == 0)
            bar.enqueue(new JFXSnackbar.SnackbarEvent("用户名密码不能为空"));


        else
        {

            WebResponse res = api.post("/account/login", String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password));
            switch (res.getStatusCode()) {
                case "200":
                    setAccountJsonData(res.getJsonData());
                    api.setAuth(currentAccount.getUsername(), currentAccount.getCampusCardID(),currentAccount.getPassword());
                    // 切换页面
                    stageController.setStage(App.MAIN_VIEW_NAME, App.WELCOME_VIEW_NAME);
                    //让后面账户登出回到这个界面时上面没有之前的账户和密码
                    login_Tpassword.setText("");
                    login_Tusername.setText("");
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

    public void presslogin(KeyEvent keyEvent)
    {
        String username = login_Tusername.getText();
        String password = login_Tpassword.getText();
        JFXSnackbar bar = new JFXSnackbar(loginpane);



        if(username.length() == 0 || password.length() == 0)
            bar.enqueue(new JFXSnackbar.SnackbarEvent("用户名密码不能为空"));


        else
        {
            WebResponse res = api.post("/account/login", String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password));
            switch (res.getStatusCode()) {
                case "200":
                    setAccountJsonData(res.getJsonData());
                    api.setAuth(currentAccount.getUsername(), currentAccount.getCampusCardID(),currentAccount.getPassword());
                    // 切换页面
                    stageController.setStage(App.MAIN_VIEW_NAME, App.WELCOME_VIEW_NAME);
                    //让后面账户登出回到这个界面时上面没有之前的账户和密码
                    login_Tpassword.setText("");
                    login_Tusername.setText("");
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

    public void signup(ActionEvent actionEvent)
    {
        String firstname=register_Tfirstname.getText();
        String lastname=register_Tlastname.getText();
        String card=register_Tcard.getText();
        String username=register_Tusername.getText();
        String password=register_Tpassword.getText();

        JFXSnackbar bar = new JFXSnackbar(registerpane);
        if(firstname.length() == 0 || lastname.length()==0||card.length()==0||username.length()==0||password.length() == 0||radiogroup.getSelectedToggle()==null)
            bar.enqueue(new JFXSnackbar.SnackbarEvent("请完善所有信息"));

        else
        {
            register_radiostudent.setUserData("student");
            register_radioadminis.setUserData("administrator");
            String role=radiogroup.getSelectedToggle().getUserData().toString();
            JSONObject info = new JSONObject();
            info.put("CampusCardID",card);
            info.put("Username",username);
            info.put("Password",password);
            info.put("FirstName",firstname);
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
}
