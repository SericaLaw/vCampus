package team.yummy.vCampus.client.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import team.yummy.vCampus.client.api.Api;

public class App extends Application {
    // TODO: 在这里配置窗口名 - 资源相对地址映射
    public static String mainFormViewName = "MainFormView";
    public static String mainFormViewResource = "./demo/main_form.fxml";

    public static String welcomeFormViewName = "WelcomeFormView";
    public static String welcomeFormViewResource = "./demo/welcome_form.fxml";

    private StageController stageController;

    @Override
    public void start(Stage primaryStage) {
        //新建一个StageController控制器
        stageController = new StageController();

        //将主舞台交给控制器处理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        //加载两个舞台，每个界面一个舞台
        stageController.loadStage(welcomeFormViewName, welcomeFormViewResource);
        stageController.loadStage(mainFormViewName, mainFormViewResource);

        //显示MainView舞台
        stageController.setStage(welcomeFormViewName);
    }


    public static void main(String[] args) {
        launch(args);
    }
}