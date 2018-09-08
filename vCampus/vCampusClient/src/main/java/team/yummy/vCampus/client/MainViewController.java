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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.omg.CORBA.PRIVATE_MEMBER;
import team.yummy.vCampus.models.CourseScheduleItem;
import team.yummy.vCampus.models.Goods;
import team.yummy.vCampus.models.StuInfo;

import team.yummy.vCampus.web.WebResponse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.jfoenix.controls.*;

public class MainViewController extends ViewController implements Initializable {

    @FXML private StackPane rootStackPane;
    @FXML private GridPane title;
    @FXML private AnchorPane InitPane;
    @FXML private AnchorPane StuInfoPane;
    @FXML private AnchorPane CoursePane;
    @FXML private AnchorPane DormPane;
    @FXML private AnchorPane LibraryPane;
    @FXML private AnchorPane BankPane;
    @FXML private AnchorPane StorePane;
    @FXML private AnchorPane AccountMagPane;
    @FXML private Label win_close;
    @FXML private Label win_mini;
    @FXML private RadioButton register_radiostudent;
    @FXML private RadioButton register_radiominis;

    @FXML private Label si_GPA;
    @FXML private Label si_SRTP;
    @FXML private Label si_LAC;
    @FXML private Label si_Name;
    @FXML private Label si_CampusCardID;
    @FXML private Label si_StudentID;
    @FXML private Label si_Department;
    @FXML private Label si_Major;
    @FXML private Label si_EnrollmentYear;
    @FXML private Button editorSaveStuInfo;
    @FXML private JFXTextField si_IDNum;
    @FXML private JFXComboBox si_Sex;
    @FXML private DatePicker si_Birthdate;
    @FXML private JFXComboBox si_Birthplace;
    @FXML private JFXTextField si_Phone;
    @FXML private JFXTextField si_Email;
    @FXML private JFXTextField si_Address;
    @FXML private JFXTextField si_SeniorHigh;
    @FXML private Label si_errorText;

    @FXML private AnchorPane li_BorrowedPane;
    @FXML private AnchorPane li_InquirePane;

    @FXML public Label content__Store__Cart__GrandTotalPrice;

    @FXML private Label am_CampusCardID;
    @FXML private Label am_Username;
    @FXML private Label am_Role;
    @FXML private Label am_Name;


    /**
     * members for store page
     */
    @FXML private VBox store_newItemBox;
    @FXML private VBox store_popItemBox;
    @FXML private VBox store_favItemBox;

    @FXML public VBox store_CartBox;

    // 这里放商品列表数据
    private List<Goods> goodList = new ArrayList<Goods>();
    // 这里存放购物车数据

    public List<Goods> goodsToBuy = new ArrayList<>();

    /**
     * members for course schedule
     */
    @FXML private GridPane course_scheduleGrid;

    public List<CourseScheduleItem> courseScheduleItems = new ArrayList<>();

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {     //点其他栏目的时候要不initpane上的图案淡一点？
        InitPane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);

        // 模拟服务器返回的信息

        Goods good1 = new Goods("1","鞋子","123","白色", "./images/item.png");
        Goods good2 = new Goods("2","鞋子","123", "红色","./images/item.png");
        Goods good3 = new Goods("3","鞋子","123", "黑色","./images/item.png");
        Goods good4 = new Goods("4","鞋子","123", "蓝色","./images/item.png");
        Goods good5 = new Goods("5","鞋子","123", "黑色","./images/item.png");
        Goods good6 = new Goods("6","鞋子","123", "蓝色","./images/item.png");

        goodList.add(good1);
        goodList.add(good2);
        goodList.add(good3);
        goodList.add(good4);
        goodList.add(good5);
        goodList.add(good6);

        goodsToBuy.add(good1);
        goodsToBuy.add(good2);

        CourseScheduleItem courseScheduleItem1 = new CourseScheduleItem("1001", 1, 1, 3, "数据结构", "邓俊辉","J2-102");
        CourseScheduleItem courseScheduleItem2 = new CourseScheduleItem("2001", 1, 12, 13, "算法", "图灵","J2-202");
        courseScheduleItems.add(courseScheduleItem1);
        courseScheduleItems.add(courseScheduleItem2);
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
    protected void swithAccountmag(ActionEvent actionEvent) {
        AccountMagPane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        StorePane.setVisible(false);
        InitPane.setVisible(false);

        //WebResponse res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardID());
        //StuInfo stuInfoGot = res.dataList(StuInfo.class, 0);
        am_CampusCardID.setText(currentAccount.getCampusCardID());
        am_Username.setText(currentAccount.getUsername());
        am_Role.setText(currentAccount.getRole());
        am_Name.setText(currentAccount.getFirstName()+currentAccount.getLastName());
    }

    @FXML
    protected void switchStuInfo(ActionEvent actionEvent) {
        StuInfoPane.setVisible(true);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
        WebResponse res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardID());
        StuInfo stuInfoGot = res.dataList(StuInfo.class, 0);
        si_Name.setText(currentAccount.getLastName()+currentAccount.getFirstName());
        si_GPA.setText(stuInfoGot.getGPA());
        si_SRTP.setText(stuInfoGot.getSRTP());
        si_LAC.setText(stuInfoGot.getLectureAttendCount());
        si_CampusCardID.setText(stuInfoGot.getCampusCardID());
        si_StudentID.setText(stuInfoGot.getStudentID());
        si_Department.setText(stuInfoGot.getDepartment());
        si_Major.setText(stuInfoGot.getMajor());
        si_EnrollmentYear.setText(stuInfoGot.getEnrollmentYear());
        si_IDNum.setText(stuInfoGot.getIDNum());
        si_Sex.setValue(stuInfoGot.getSex());
        //LocalDate d=stuInfoGot.getBirthDate();
        Date date = stuInfoGot.getBirthDate();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate d = instant.atZone(zoneId).toLocalDate();
        si_Birthdate.setValue(d);
        si_Birthplace.setValue(stuInfoGot.getBirthplace());
        si_Phone.setText(stuInfoGot.getPhone());
        si_Email.setText(stuInfoGot.getEmail());
        si_Address.setText(stuInfoGot.getAddress());
        si_SeniorHigh.setText(stuInfoGot.getSeniorHigh());
    }

    @FXML
    protected void switchCourse(ActionEvent actionEvent) {
        CoursePane.setVisible(true);
        StuInfoPane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        for(CourseScheduleItem course : courseScheduleItems) {
            JFXButton courseItem = new JFXButton(course.getCourseName()+"@"+course.getCourseVenue());
            courseItem.setPrefHeight(200);
            courseItem.setPrefWidth(200);
            courseItem.setBackground(new Background(new BackgroundFill(Color.web("#512DA8"), null, null)));
            courseItem.setTextFill(Color.web("#fff"));
            courseItem.setFont(Font.font(14));
//            courseItem.borderProperty().setValue(new Border(new BorderStroke(null, null, new CornerRadii(50), new BorderWidths(2))));
//            courseItem.setBorder(new Border(new BorderStroke(Color.web("#512DA8"), BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(2))));
//            courseItem.getStyleClass().add("course-schedule-item");
            course_scheduleGrid.add(courseItem, course.getWeekDay(), course.getSpanStart(), 1, course.getSpanEnd()-course.getSpanStart()+1);
        }
    }
    @FXML
    protected void switchDorm(ActionEvent actionEvent) {
        DormPane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
    }
    @FXML
    protected void switchLibrary(ActionEvent actionEvent) {
        LibraryPane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        BankPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
    }
    @FXML
    protected void switchBank(ActionEvent actionEvent) {
        BankPane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
    }
    @FXML
    protected void switchStore(ActionEvent actionEvent) {
        StorePane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        StoreViewFactory storeViewFactory = new StoreViewFactory(rootStackPane, this);
        List<HBox> row = storeViewFactory.createStoreRows(goodList, 3);
        if(store_newItemBox.getChildren().size() != 0) {
            store_newItemBox.getChildren().clear();
            store_newItemBox.getChildren().addAll(row);
        } else {
            store_newItemBox.getChildren().addAll(row);
        }

        List<HBox> cartRows = storeViewFactory.createCartRows(goodsToBuy);
        store_CartBox.getChildren().addAll(cartRows);
    }
    @FXML
    protected void editorSave(ActionEvent actionEvent) {
        if(editorSaveStuInfo.getText().equals("编辑"))
        {
            si_IDNum.setDisable(false);
            si_Sex.setDisable(false);
            si_Birthdate.setDisable(false);
            si_Birthplace.setDisable(false);
            si_Phone.setDisable(false);
            si_Email.setDisable(false);
            si_Address.setDisable(false);
            si_SeniorHigh.setDisable(false);
            editorSaveStuInfo.setText("保存");
        }

        else {
            String IDNum=si_IDNum.getText();
            String Sex=si_Sex.getValue().toString();
            //String Birthdate=si_Birthdate.getValue().format();
            LocalDate Birthdate=si_Birthdate.getValue();
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = Birthdate.atStartOfDay(zoneId);
            Date date = Date.from(zdt.toInstant());
            String Birthplace=si_Birthplace.getValue().toString();
            String Phone=si_Phone.getText();
            String Email=si_Email.getText();
            String Address=si_Address.getText();
            String SeniorHigh=si_SeniorHigh.getText();

            while(IDNum.length() == 0 || Sex.length() == 0 || Birthplace.length() == 0 ||
                    Phone.length() == 0 || Email.length() == 0 || Address.length() == 0 || SeniorHigh.length() == 0 )
                si_errorText.setText("有空选项!");

            /*SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
            String str = Birthdate;
            String si_birthdate="";
            try {
                Date d = sdf.parse(str);
                si_birthdate=d.toString();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/

            JSONObject infoToModify = new JSONObject();
            infoToModify.put("IDNum", IDNum);
            infoToModify.put("Sex",Sex);
            infoToModify.put("Birthdate", Birthdate);
            infoToModify.put("Birthplace",Birthplace);
            infoToModify.put("Phone", Phone);
            infoToModify.put("Email",Email);
            infoToModify.put("Address", Address);
            infoToModify.put("SeniorHigh",SeniorHigh);
            api.patch("/stuInfo/campusCardID/"+ currentAccount.getCampusCardID(), infoToModify.toJSONString());

            si_errorText.setText("");
            si_IDNum.setDisable(true);
            si_Sex.setDisable(true);
            si_Birthdate.setDisable(true);
            si_Birthplace.setDisable(true);
            si_Phone.setDisable(true);
            si_Email.setDisable(true);
            si_Address.setDisable(true);
            si_SeniorHigh.setDisable(true);
            editorSaveStuInfo.setText("编辑");
        }
    }

    @FXML
    protected void switchLibBorrowed(ActionEvent actionEvent) {
        li_InquirePane.setVisible(false);
        li_BorrowedPane.setDisable(true);

    }

    @FXML
    protected void switchLibInquire(ActionEvent actionEvent) {
        li_InquirePane.setVisible(true);
        li_BorrowedPane.setDisable(false);
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
    protected void unregister(ActionEvent actionEvent)
    {
        final JFXDialog dialog = new JFXDialog();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        VBox vb = new VBox();
        Label text =new Label("确定要销号吗？");
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
                //销号的操作
                api.delete("/account/campusCardId/"+ currentAccount.getCampusCardID());
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
    protected void cartBatchRemove(ActionEvent actionEvent) {
        //
    }


    protected void choosestudent(ActionEvent actionEvent)
    {
        register_radiominis.setPickOnBounds(false);
    }
    @FXML
    protected void chooseadminis(ActionEvent actionEvent)
    {
        register_radiostudent.setPickOnBounds(false);
    }
}
