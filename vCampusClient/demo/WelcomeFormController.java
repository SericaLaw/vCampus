import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import vCampus.server.api.Api;
import vCampus.server.http.HttpResponse;

public class WelcomeFormController {
    @FXML private Text errorText;
    @FXML private TextField loginUsername;
    @FXML private PasswordField loginPassword;
    @FXML private GridPane loginGrid;
    @FXML private GridPane registerGrid;

    private String username;
    private String password;

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
        // some other logic...

        // api
        HttpResponse res = Api.post("/account/login", String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password));
        if(res.getStatusCode().equals("200"))
            errorText.setText("登录成功");
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
}
