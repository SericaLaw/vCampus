package team.yummy.vCampus.client.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import team.yummy.vCampus.client.api.Api;
import team.yummy.vCampus.util.Logger;

import java.util.HashMap;

public class StageController {
    private Logger logger = new Logger("Stage Controller");
    public Api api = new Api();
    public String authData = null;
    //建立一个专门存储Stage的Map，全部用于存放Stage对象
    private HashMap<String, Stage> stages = new HashMap<String, Stage>();
    private HashMap<String, Controllable> controllers = new HashMap<String, Controllable>();

    public void addController(String name, Controllable controller) {
        controllers.put(name, controller);
    }

    public Controllable getController(String name) {
        return controllers.get(name);
    }

    /**
     * 将加载好的Stage放到Map中进行管理
     *
     * @param name  设定Stage的名称
     * @param stage Stage的对象
     */
    public void addStage(String name, Stage stage) {
        stages.put(name, stage);
    }


    /**
     * 通过Stage名称获取Stage对象
     *
     * @param name Stage的名称
     * @return 对应的Stage对象
     */
    public Stage getStage(String name) {
        return stages.get(name);
    }


    /**
     * 将主舞台的对象保存起来，这里只是为了以后可能需要用，目前还不知道用不用得上
     *
     * @param primaryStageName 设置主舞台的名称
     * @param primaryStage     主舞台对象，在Start()方法中由JavaFx的API建立
     */
    public void setPrimaryStage(String primaryStageName, Stage primaryStage) {
        this.addStage(primaryStageName, primaryStage);
    }


    public boolean loadStage(String name, String resource) {
        try {
            //加载FXML资源文件

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(resource));
            Parent root = (Parent) loader.load();

            //通过Loader获取FXML对应的ViewCtr，并将本StageController注入到ViewCtr中
            Controllable controlledStage = (Controllable) loader.getController();
            controlledStage.setStageController(this);
            controlledStage.setApi(this.api);
            controlledStage.setAuthData(this.authData);

            //构造对应的Stage
            Scene tempScene = new Scene(root);
            Stage tempStage = new Stage();
            tempStage.setScene(tempScene);


            //将设置好的Stage放到HashMap中
            this.addStage(name, tempStage);
            this.addController(name, controlledStage);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 显示Stage但不隐藏任何Stage
     *
     * @param name 需要显示的窗口的名称
     * @return 是否显示成功
     */
    public boolean setStage(String name) {
        // TODO:窗口间状态传送
        logger.log(this.toString());

        Controllable controlledStage = this.getController(name);
        controlledStage.setApi(this.api);
        controlledStage.setAuthData(this.authData);

        this.getStage(name).show();
        return true;
    }


    /**
     * 显示Stage并隐藏对应的窗口
     *
     * @param show  需要显示的窗口
     * @param close 需要删除的窗口
     * @return
     */
    public boolean setStage(String show, String close) {
        // TODO:窗口间状态传送
        logger.log(this.toString());

        Controllable controlledStage = this.getController(show);
        controlledStage.setApi(this.api);
        controlledStage.setAuthData(this.authData);

        getStage(close).close();
        setStage(show);
        return true;
    }


    /**
     * 在Map中删除Stage加载对象
     *
     * @param name 需要删除的fxml窗口文件名
     * @return 是否删除成功
     */
    public boolean unloadStage(String name) {
        if (stages.remove(name) == null) {
            System.out.println("窗口不存在，请检查名称");
            return false;
        } else {
            System.out.println("窗口移除成功");
            return true;
        }
    }

    public String toString() {
        return String.format("State [ authData = %s ]", authData);
    }

}
