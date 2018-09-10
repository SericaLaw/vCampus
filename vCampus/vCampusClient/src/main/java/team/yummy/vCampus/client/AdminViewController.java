package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.omg.CORBA.PRIVATE_MEMBER;
import team.yummy.vCampus.models.*;

import team.yummy.vCampus.web.WebResponse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
import java.util.Date;
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.jfoenix.controls.*;

public class AdminViewController extends ViewController implements Initializable {
    @FXML private StackPane rootStackPane;
    @FXML private GridPane title;
    @FXML private AnchorPane InitPane;
    @FXML private AnchorPane StuInfoPane;
    //@FXML private AnchorPane CoursePane;
    @FXML private AnchorPane LibraryPane;
    @FXML private AnchorPane StorePane;
    @FXML private AnchorPane AccountMagPane;
    @FXML private Label win_close;
    @FXML private Label win_mini;
    @FXML private RadioButton register_radiostudent;
    @FXML private RadioButton register_radiominis;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML private AnchorPane li_InquirePane;

    @FXML private Label am_Username;
    @FXML private Label am_Role;

    @FXML public VBox library_inquireBox;
    @FXML private TextField library_InquireText;

    @FXML private GridPane stuinfopane;
    @FXML private JFXButton editStu;
    @FXML private JFXTextField si_name;
    @FXML private JFXTextField si_enrollmentyear;
    @FXML private JFXTextField si_campuscardID;
    @FXML private JFXTextField si_studentID;
    @FXML private JFXTextField si_department;
    @FXML private JFXTextField si_major;
    @FXML private Label stuinfo_errortext;

    /**
     * members for store page
     */
    @FXML private VBox store_newItemBox;
    @FXML private VBox store_popItemBox;
    @FXML private VBox store_favItemBox;     ///////////////////

    // 这里放商品列表数据
    private List<Goods> goodList = new ArrayList<Goods>();

    /**
     * members for course register
     */
//    @FXML private HBox course_registerBox;
//    @FXML private VBox register_courseNameCol;
//    @FXML private VBox course_venueCol;
//    @FXML private VBox register_scheduleCol;
//    @FXML private VBox register_statusCol;
//    @FXML private VBox register_opCol;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InitPane.setVisible(true);
        StuInfoPane.setVisible(false);
//        CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
    }

    @FXML
    protected void winclose(MouseEvent mouseEvent) {             //MouseEvent是个好东西啊！！！！
        Stage stage = (Stage)win_close.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void winmini(MouseEvent mouseEvent) {
        Stage stage = (Stage)win_mini.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    protected void titlepressed(MouseEvent mouseEvent) {
        xOffset = mouseEvent.getSceneX();
        yOffset = mouseEvent.getSceneY();
    }

    @FXML
    protected void titledragged(MouseEvent mouseEvent) {
        Stage stage = (Stage)title.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - xOffset);
        stage.setY(mouseEvent.getScreenY() - yOffset);
    }

    @FXML
    protected void logout(ActionEvent actionEvent)
    {
        final JFXDialog dialog = new JFXDialog();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        VBox vb = new VBox();
        Label text =new Label("确定要登出吗？");
        text.setFont(Font.font(20));
        text.setTextFill(Color.web("black"));
        text.setPadding(new Insets(10,0,0,180));
        text.setPrefSize(400,200);
        hb1.setPadding(new Insets(0,10,30,280));
        JFXButton ok=new JFXButton("确定");
        JFXButton cancel=new JFXButton("取消");
        ok.setFont(Font.font(17));
        ok.setPrefSize(80,35);
        ok.setBackground(new Background(new BackgroundFill(Color.web("#FF4500"),null,null)));
        ok.setTextFill(Color.web("#fff"));
        cancel.setFont(Font.font(17));
        cancel.setPrefSize(80,35);
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#707070"),null,null)));
        cancel.setTextFill(Color.web("#fff"));
        hb1.setSpacing(35);
        hb1.getChildren().addAll(ok,cancel);
        vb.setPrefSize(500,250);
        hb2.getChildren().addAll(text);
        vb.getChildren().addAll(hb2,hb1);
        dialog.setContent(vb);
        dialog.show(rootStackPane);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //登出的操作
                InitPane.setVisible(true);
                StuInfoPane.setVisible(false);
                //CoursePane.setVisible(false);
                LibraryPane.setVisible(false);
                StorePane.setVisible(false);
                AccountMagPane.setVisible(false);
                dialog.setVisible(false);
                stageController.setStage(App.WELCOME_VIEW_NAME, App.MAIN_VIEW_NAME);
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.setVisible(false);            }
        });
    }

    @FXML
    protected void swithAccountmag(ActionEvent actionEvent) {
        AccountMagPane.setVisible(true);
        StuInfoPane.setVisible(false);
        //CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        InitPane.setVisible(false);

        am_Username.setText(currentAccount.getUsername());
        am_Role.setText(currentAccount.getRole());
    }

    @FXML
    protected void switchStuInfo(ActionEvent actionEvent) {
        StuInfoPane.setVisible(true);
        //CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
        //WebResponse res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardID());
        //StuInfo stuInfoGot = res.dataList(StuInfo.class, 0);

        /*LocalDate d=stuInfoGot.getBirthDate();
        Date date = stuInfoGot.getBirthDate();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate d = instant.atZone(zoneId).toLocalDate();
        */
    }

    /*
    @FXML
    protected void switchCourse(ActionEvent actionEvent) {
        CoursePane.setVisible(true);
        StuInfoPane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        WebResponse res = api.get("/course/schedule");
        courseScheduleItems = res.dataList(CourseScheduleItem.class);

        // 改成添加课程
        /**
         * 选课
         */
        /*res = api.get("/course/register");
        CourseRegister courseRegister = res.data(CourseRegister.class);

        for (CourseRegisterItem course : courseRegister.getCourseList()) {
            VBox courseInfoCol = new VBox();

            courseInfoCol.setStyle("-fx-spacing: 10");


            Label courseName = new Label(course.getCourseName());
            courseName.getStyleClass().add("register-item__course-name");

            HBox courseDetailInfo = new HBox();
//            courseDetailInfo.getStyleClass().add("register-item__course-detail");
            courseDetailInfo.setStyle("-fx-alignment: center-left");
            courseDetailInfo.setStyle("-fx-spacing: 10");

            Label courseTeacher = new Label("教师：" + course.getProfName());
            courseTeacher.setStyle("-fx-text-fill: #757575");
            Label courseCredit = new Label("学分：" + course.getCredit());
            courseCredit.setStyle("-fx-text-fill: #757575");
            courseDetailInfo.getChildren().addAll(courseTeacher, courseCredit);
            courseInfoCol.getChildren().addAll(courseName, courseDetailInfo);

            courseInfoCol.getStyleClass().add("register-item");
            register_courseNameCol.getChildren().addAll(courseInfoCol);

            VBox courseVenueCol = new VBox();

            Label courseVenue = new Label("@" + course.getCourseVenue());
            courseVenue.setStyle("-fx-font-size: 18");
            courseVenueCol.getChildren().addAll(courseVenue);
            courseVenue.getStyleClass().add("register-item");
            course_venueCol.getChildren().addAll(courseVenueCol);

            VBox courseScheduleCol = new VBox();
            for (Schedule s : course.getCourseSchedule()) {
                String schedule = "";
                schedule += "星期" + s.getWeekDay() + "（" + s.getSpanStart() + "-" + s.getSpanEnd() + "）";
                Label courseSchedule = new Label(schedule);
                courseScheduleCol.getChildren().addAll(courseSchedule);
            }

            courseScheduleCol.getStyleClass().add("register-item");
            register_scheduleCol.getChildren().addAll(courseScheduleCol);

            VBox courseStatusCol = new VBox();
            Label courseStatus = new Label(String.valueOf(course.getStuAttendCount()) + " / " + String.valueOf(course.getStuLimitCount()));
            courseStatus.setStyle("-fx-font-size: 18");
            courseStatusCol.getChildren().addAll(courseStatus);
            courseStatusCol.getStyleClass().add("register-item");
            register_statusCol.getChildren().addAll(courseStatusCol);

            VBox opCol = new VBox();
            opCol.setStyle("-fx-min-width: 50");
            opCol.setStyle("-fx-pref-width: 50");


            JFXButton buttonOp = null;

            // TODO: 按钮事件
            if (course.getStatus() == CourseStatusEnum.AVAILABLE) {
                buttonOp = new JFXButton("选择");
                buttonOp.setStyle("-fx-background-color: #673AB7;-fx-text-fill: #fff;-fx-font-size: 18;");
            } else if (course.getStatus() == CourseStatusEnum.CONFLICT) {
                buttonOp = new JFXButton("冲突");
                buttonOp.setStyle("-fx-font-size: 18;");
                buttonOp.setDisable(true);
            } else if (course.getStatus() == CourseStatusEnum.NOT_AVAILABLE) {
                buttonOp = new JFXButton("已满");
                buttonOp.setStyle("-fx-font-size: 18;");
                buttonOp.setDisable(true);
            } else if (course.getStatus() == CourseStatusEnum.SELECTED) {
                buttonOp = new JFXButton("退选");
                buttonOp.setStyle("-fx-background-color: #ff2300;-fx-text-fill: #fff;-fx-font-size: 18;");

            }
            buttonOp.setButtonType(JFXButton.ButtonType.RAISED);
            opCol.getChildren().addAll(buttonOp);
            opCol.getStyleClass().add("register-item");

            register_opCol.getChildren().addAll(opCol);
        }
    }*/


    @FXML
    protected void switchLibrary(ActionEvent actionEvent) {
        LibraryPane.setVisible(true);
        StuInfoPane.setVisible(false);
        //CoursePane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
        WebResponse res = api.get("/book");
        List<Book> bookList = res.dataList(Book.class);
        AdminLibraryViewFactory adminlibraryViewFactory = new AdminLibraryViewFactory(rootStackPane,this);
        List<HBox> row = adminlibraryViewFactory.createFullBookRows(bookList);
        if (library_inquireBox.getChildren().size() != 0) {
            library_inquireBox.getChildren().clear();
            library_inquireBox.getChildren().addAll(row);
        } else {
            library_inquireBox.getChildren().addAll(row);
        }

    }

    @FXML
    protected void switchStore(ActionEvent actionEvent) {
        StorePane.setVisible(true);
        StuInfoPane.setVisible(false);
        //CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        /*StoreViewFactory storeViewFactory = new StoreViewFactory(rootStackPane, this);
        List<HBox> row = storeViewFactory.createStoreRows(goodList, 3);
        if(store_newItemBox.getChildren().size() != 0) {
            store_newItemBox.getChildren().clear();
            store_newItemBox.getChildren().addAll(row);
        } else {
            store_newItemBox.getChildren().addAll(row);
        }*/
    }


    @FXML
    protected void booksearch(KeyEvent keyEvent)
    {
        String keyword = library_InquireText.getText();
        if (keyword==null||keyword.equals("")) {
            library_InquireText.setPromptText("按书名搜索");
        } else {
            WebResponse res = api.get("/book/bookName/" + keyword + "/like");
            List<Book> bookList_keyword = res.dataList(Book.class);
            AdminLibraryViewFactory adminlibraryViewFactory = new AdminLibraryViewFactory(rootStackPane, this);
            List<HBox> row = adminlibraryViewFactory.createFullBookRows(bookList_keyword);
            if (library_inquireBox.getChildren().size() != 0) {
                library_inquireBox.getChildren().clear();
                library_inquireBox.getChildren().addAll(row);
            } else {
                library_inquireBox.getChildren().addAll(row);
            }

        }
    }

    @FXML
    protected void bookadd(ActionEvent actionEvent) {
        WebResponse res = api.get("/book");
        List<Book> bookList = res.dataList(Book.class);
        AdminLibraryViewFactory adminlibraryViewFactory = new AdminLibraryViewFactory(rootStackPane, this);
        List<HBox> row=adminlibraryViewFactory.createEmptyBookRows();
        row.addAll(adminlibraryViewFactory.createFullBookRows(bookList));
        if (library_inquireBox.getChildren().size() != 0) {
            library_inquireBox.getChildren().clear();
            library_inquireBox.getChildren().addAll(row);
        } else {
            library_inquireBox.getChildren().addAll(row);
        }
    }

    @FXML
    protected void addstudent(ActionEvent actionEvent)
    {
        editStu.setVisible(true);
        stuinfopane.setVisible(true);
        si_name.setText("");
        si_enrollmentyear.setText("");
        si_campuscardID.setText("");
        si_studentID.setText("");
        si_department.setText("");
        si_major.setText("");
        si_name.setDisable(false);
        si_enrollmentyear.setDisable(false);
        si_campuscardID.setDisable(false);
        si_studentID.setDisable(false);
        si_department.setDisable(false);
        si_major.setDisable(false);
        editStu.setText("保存信息");
    }

    @FXML
    private void editstudent(ActionEvent actionEvent)
    {
        if(editStu.getText().equals("编辑信息"))
        {
            si_name.setDisable(false);
            si_enrollmentyear.setDisable(false);
            si_campuscardID.setDisable(false);
            si_studentID.setDisable(false);
            si_department.setDisable(false);
            si_major.setDisable(false);
            editStu.setText("保存信息");
        }
        else
        {
            String name = si_name.getText();
            String enrollmentyear = si_enrollmentyear.getText();
            String campuscardID = si_campuscardID. getText();
            String studentID = si_studentID.getText();
            String department = si_department.getText();
            String major = si_major.getText();
            if(name.length()==0||enrollmentyear.length()==0||campuscardID.length()==0||
                    studentID.length()==0||department.length()==0||major.length()==0)
                stuinfo_errortext.setText("请完善学生信息！");

            else
            {
                // 和后端信息交互

                stuinfo_errortext.setText("");
                si_name.setDisable(true);
                si_enrollmentyear.setDisable(true);
                si_campuscardID.setDisable(true);
                si_studentID.setDisable(true);
                si_department.setDisable(true);
                si_major.setDisable(true);
                editStu.setText("编辑信息");
            }

        }
    }
}