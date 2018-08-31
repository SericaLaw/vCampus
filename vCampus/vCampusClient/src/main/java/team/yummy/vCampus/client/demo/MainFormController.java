package team.yummy.vCampus.client.demo;

import com.alibaba.fastjson.JSON;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.web.WebResponse;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainFormController implements Controllable, Initializable {

    @FXML
    public Button homeCourseButton;
    @FXML
    public Button homeLibraryButton;
    @FXML
    public Button homeShopButton;
    @FXML
    public GridPane home;
    @FXML
    private Button homeStuInfoButton;

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

    public void getStuInfo(ActionEvent actionEvent) {
        Map<String, String> auth = JSON.parseObject(authData, Map.class);

        WebResponse res = api.get("/stuInfo/campusCardID/" + auth.get("CampusCardID"));
    }

    @FXML
    protected void animate(ActionEvent actionEvent) {
//        System.out.println(homeStuInfoButton.getScene().getWindow().getX());
//        System.out.println(homeStuInfoButton.getScene().getWindow().getY());
//        System.out.println(homeStuInfoButton.getScene().getX());
//        System.out.println(homeStuInfoButton.getScene().getY());
//        System.out.println(homeStuInfoButton.localToScene(0, 0).getX());
//        System.out.println(homeStuInfoButton.localToScene(0, 0).getY());
//
////        System.out.println(getScreenX((Button) actionEvent.getSource()));
////
////        System.out.println(getScreenY((Button) actionEvent.getSource()));
//
//        double x = homeStuInfoButton.getScene().getX();
//        double y = homeStuInfoButton.getScene().getY();
//        Path path = new Path();
//
//        path.getElements().add(new MoveTo(x, y));
//        path.getElements().add(new LineTo(x+40, y));
////        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
////        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
//        PathTransition pathTransition = new PathTransition();
//        pathTransition.setDuration(Duration.millis(4000));
//        pathTransition.setPath(path);
//        pathTransition.setNode(homeStuInfoButton);
//        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pathTransition.setCycleCount(Timeline.INDEFINITE);
//        pathTransition.setAutoReverse(true);
//        pathTransition.play();
    }
//    public static double getScreenX(Control control){
//        return control.getScene().getWindow().getX()+control.getScene().getX()+ control.localToScene(0, 0).getX();
//    }
//    public static double getScreenY(Control control){
//        return control.getScene().getWindow().getY()+control.getScene().getY()+ control.localToScene(0, 0).getY();
//    }
//    public static double getWidth(Control control){
//        return control.getBoundsInParent().getWidth();
//    }
//    public static double getHeight(Control control){
//        return control.getBoundsInParent().getHeight();
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


}
