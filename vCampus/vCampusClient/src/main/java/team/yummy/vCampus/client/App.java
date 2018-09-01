package team.yummy.vCampus.client;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public final static String MAIN_VIEW_NAME = "MainView";
    public final static String MAIN_VIEW_RESOURCE = "./main.fxml";
    public final static String WELCOME_VIEW_NAME = "WelcomeView";
    public final static String WELCOME_VIEW_RESOURCE = "./welcome.fxml";

    @Override
    public void start(Stage primaryStage) {
        StageController stageController = new StageController();

        // 将primaryStage交给stageController处理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        // 加载两个Stage，一个页面对应一个舞台
        stageController.loadStage(WELCOME_VIEW_NAME, WELCOME_VIEW_RESOURCE);
        stageController.loadStage(MAIN_VIEW_NAME, MAIN_VIEW_RESOURCE);

        // 显示欢迎界面
        stageController.setStage(WELCOME_VIEW_NAME);
    }


    public static void main(String[] args) {
        launch(args);
    }
}