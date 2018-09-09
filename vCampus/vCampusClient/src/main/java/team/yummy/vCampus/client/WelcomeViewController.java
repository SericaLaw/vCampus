package team.yummy.vCampus.client;

import com.jfoenix.controls.JFXSnackbar;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import team.yummy.vCampus.web.WebResponse;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;


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

    @FXML private ToggleGroup RadioGroup;
    @FXML private RadioButton register_choosestudent;
    @FXML private RadioButton register_chooseadminis;


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


         WebResponse res = api.post("/account/login", String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password));
        switch (res.getStatusCode()) {
            case "200":
                setAccountJsonData(res.getBody());
                api.setAuth(currentAccount.getUsername(), currentAccount.getCampusCardID(),currentAccount.getPassword());
                // 切换页面
                stageController.setStage(App.MAIN_VIEW_NAME, App.WELCOME_VIEW_NAME);
                break;
            case "404":
                bar.enqueue(new JFXSnackbar.SnackbarEvent("用户不存在"));
                break;
            case "403":
                bar.enqueue(new JFXSnackbar.SnackbarEvent("密码错误"));
                break;

        }

    }

    public void presslogin(KeyEvent keyEvent)
    {
        String username = login_Tusername.getText();
        String password = login_Tpassword.getText();
        JFXSnackbar bar = new JFXSnackbar(loginpane);



        if(username.length() == 0 || password.length() == 0)
            bar.enqueue(new JFXSnackbar.SnackbarEvent("用户名密码不能为空"));


        WebResponse res = api.post("/account/login", String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password));
        switch (res.getStatusCode()) {
            case "200":
                setAccountJsonData(res.getBody());
                api.setAuth(currentAccount.getUsername(), currentAccount.getCampusCardID(),currentAccount.getPassword());
                // 切换页面
                stageController.setStage(App.MAIN_VIEW_NAME, App.WELCOME_VIEW_NAME);
                break;
            case "404":
                bar.enqueue(new JFXSnackbar.SnackbarEvent("用户不存在"));
                break;
            case "403":
                bar.enqueue(new JFXSnackbar.SnackbarEvent("密码错误"));
                break;

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
        if(firstname.length() == 0 || lastname.length()==0||card.length()==0||username.length()==0||password.length() == 0||RadioGroup.getSelectedToggle()==null)
            bar.enqueue(new JFXSnackbar.SnackbarEvent("请完善所有信息"));
//            RegistererrorText.setText("请完善所有信息！");
        else
        {
            RegistererrorText.setText("");
        }

      /* if(RadioGroup.getSelectedToggle().getUserData().toString())
       {
           RegistererrorText.setText("mia");
       }
       else RegistererrorText.setText("身份为管理员");*/

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
