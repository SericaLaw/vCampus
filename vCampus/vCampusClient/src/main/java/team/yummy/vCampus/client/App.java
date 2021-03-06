package team.yummy.vCampus.client;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

/**
 * 程序主入口类，在此配置程序的资源文件，并将其交给StageController管理，启动时用StageController加载欢登陆页面
 * @author Serica
 */
public class App extends Application {
    public final static String MAIN_VIEW_NAME = "MainView";
//    public final static String MAIN_VIEW_RESOURCE = "/vCampus/vCampusClient/target/classes/main.fxml";
    public final static String MAIN_VIEW_RESOURCE = "main.fxml";
    public final static String WELCOME_VIEW_NAME = "WelcomeView";
    public final static String WELCOME_VIEW_RESOURCE = "welcome.fxml";
//    public final static String WELCOME_VIEW_RESOURCE = "/vCampus/vCampusClient/target/classes/welcome.fxml";
    public final static String ADMIN_VIEW_NAME = "AdminView";
    public final static String ADMIN_VIEW_RESOURCE = "admin.fxml";
//    public final static String ADMIN_VIEW_RESOURCE ="/vCampus/vCampusClient/target/classes/admin.fxml";
    public final static String TEACHER_VIEW_NAME = "TeacherView";
    public final static String TEACHER_VIEW_RESOURCE = "teacher.fxml";
//    public final static String TEACHER_VIEW_RESOURCE = "/vCampus/vCampusClient/target/classes/teacher.fxml";
    @Override
    public void start(Stage primaryStage) {

        StageController stageController = new StageController();

        // 将primaryStage交给stageController处理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        // 加载两个Stage，一个页面对应一个舞台
        stageController.loadStage(WELCOME_VIEW_NAME, WELCOME_VIEW_RESOURCE);
        stageController.loadStage(MAIN_VIEW_NAME, MAIN_VIEW_RESOURCE);
        stageController.loadStage(ADMIN_VIEW_NAME, ADMIN_VIEW_RESOURCE);
        stageController.loadStage(TEACHER_VIEW_NAME, TEACHER_VIEW_RESOURCE);

        // 显示欢迎界面
        stageController.setStage(WELCOME_VIEW_NAME);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
