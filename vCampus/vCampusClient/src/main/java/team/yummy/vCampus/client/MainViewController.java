package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.jfoenix.controls.*;

import static javafx.animation.Interpolator.EASE_BOTH;

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
    @FXML public Button content__Store__Cart__BatchRemove;
    @FXML public Button content__Store__Cart__Pay;

    @FXML private Label am_CampusCardID;
    @FXML private Label am_Username;
    @FXML private Label am_Role;
    @FXML private Label am_Name;

    @FXML public VBox library_inquireBox;
    @FXML private TextField library_InquireText;
    @FXML protected VBox library_borrowedBox;
    public List<BorrowedBook> borrowedbookList=new ArrayList<>();


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

    /**
     * members for course report
     */
    @FXML private VBox course_reportBox;
    @FXML private Label score_avgScore;
    @FXML private Label score_avgGPA;
    @FXML private Label score_totalCredit;

    @FXML private HBox course_reportHeading;


    /**
     * members for course register
     */
    @FXML private HBox course_registerBox;
    @FXML private VBox register_courseNameCol;
    @FXML private VBox course_venueCol;
    @FXML private VBox register_scheduleCol;
    @FXML private VBox register_statusCol;
    @FXML private VBox register_opCol;
    // 数据
    public List<CourseReportItem> courseReportItems = new ArrayList<>();

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

        JFXDepthManager.setDepth(course_reportHeading, 1);

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

        WebResponse res = api.get("/course/schedule");
        courseScheduleItems = res.dataList(CourseScheduleItem.class);

        String[] colors = {"#DD9708", "#56AF5A", "#E9433f", "#0EB5CA", "#512DA8"};
        List<String> listColors = new ArrayList<String>();
        for(String color : colors) {
            listColors.add(color);
        }

        for(CourseScheduleItem course : courseScheduleItems) {
            JFXButton courseItem = new JFXButton(course.getCourseName() + "@" + course.getCourseVenue());
            courseItem.setPrefHeight(200);
            courseItem.setPrefWidth(200);

            if(listColors.isEmpty()) {
                for(String color : colors) {
                    listColors.add(color);
                }
            }
            int colorIndex = (int)(Math.random()*(listColors.size()-1));
            String itemColor = listColors.get(colorIndex);
            listColors.remove(colorIndex);
            courseItem.setBackground(new Background(new BackgroundFill(Color.web(itemColor), null, null)));
            courseItem.setTextFill(Color.web("#fff"));
            courseItem.setFont(Font.font(14));
//            courseItem.borderProperty().setValue(new Border(new BorderStroke(null, null, new CornerRadii(50), new BorderWidths(2))));
//            courseItem.setBorder(new Border(new BorderStroke(Color.web("#512DA8"), BorderStrokeStyle.SOLID, new CornerRadii(50), new BorderWidths(2))));
//            courseItem.getStyleClass().add("course-schedule-item");
            course_scheduleGrid.add(courseItem, course.getWeekDay() * 2 - 1, course.getSpanStart(), 1, course.getSpanEnd()-course.getSpanStart()+1);
        }

        /**
         * 成绩查询
         */
        double totalScore = 0;
        double totalCredit = 0;

        res = api.get("/course/report");
        courseReportItems = res.dataList(CourseReportItem.class);

        for(CourseReportItem report : courseReportItems) {
            totalScore += report.getScore();
            totalCredit += report.getCredit();

            HBox newRow = new HBox();
            newRow.getStyleClass().add("report-item");

            VBox courseInfo = new VBox();
            courseInfo.getStyleClass().add("course-info");
            HBox courseDetailInfo = new HBox();
            courseDetailInfo.getStyleClass().add("course-detail");

            Label courseName = new Label(report.getCourseName());
            courseName.getStyleClass().add("course-name");

            Label courseCredit = new Label("学分: " + String.valueOf(report.getCredit()));
            Label scoreType = new Label(report.getScoreType());
            courseDetailInfo.getChildren().addAll(courseCredit, scoreType);

            courseInfo.getChildren().addAll(courseName, courseDetailInfo);

            Label score = new Label(String.valueOf(report.getScore()));
            score.getStyleClass().add("score");

            newRow.getChildren().addAll(courseInfo, score);

            course_reportBox.getChildren().add(newRow);
        }

        score_avgScore.setText(String.valueOf(totalScore / courseReportItems.size()));
        score_totalCredit.setText(String.valueOf(totalCredit));
        String gpa = String.valueOf(calculateGPA(courseReportItems));
        if(gpa.length() > 3)
            gpa = gpa.substring(0, 4);
        score_avgGPA.setText(gpa);

        /**
         * 选课
         */
        res = api.get("/course/register");
        CourseRegister courseRegister = res.data(CourseRegister.class);

        for(CourseRegisterItem course : courseRegister.getCourseList()) {
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

            Label courseVenue = new Label("@"+course.getCourseVenue());
            courseVenue.setStyle("-fx-font-size: 18");
            courseVenueCol.getChildren().addAll(courseVenue);
            courseVenue.getStyleClass().add("register-item");
            course_venueCol.getChildren().addAll(courseVenueCol);

            VBox courseScheduleCol = new VBox();
            for(Schedule s : course.getCourseSchedule()) {
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
            if(course.getStatus() == CourseStatusEnum.AVAILABLE) {
                buttonOp = new JFXButton("选择");
                buttonOp.setStyle("-fx-background-color: #673AB7;-fx-text-fill: #fff;-fx-font-size: 18;");
            }
            else if(course.getStatus() == CourseStatusEnum.CONFLICT) {
                buttonOp = new JFXButton("冲突");
                buttonOp.setStyle("-fx-font-size: 18;");
                buttonOp.setDisable(true);
            }
            else if(course.getStatus() == CourseStatusEnum.NOT_AVAILABLE) {
                buttonOp = new JFXButton("已满");
                buttonOp.setStyle("-fx-font-size: 18;");
                buttonOp.setDisable(true);
            }
            else if(course.getStatus() == CourseStatusEnum.SELECTED) {
                buttonOp = new JFXButton("退选");
                buttonOp.setStyle("-fx-background-color: #ff2300;-fx-text-fill: #fff;-fx-font-size: 18;");

            }
            buttonOp.setButtonType(JFXButton.ButtonType.RAISED);
            opCol.getChildren().addAll(buttonOp);
            opCol.getStyleClass().add("register-item");

            register_opCol.getChildren().addAll(opCol);
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
        WebResponse res = api.get("/book");
        List<Book> bookList = res.dataList(Book.class);
        LibraryViewFactory libraryViewFactory = new LibraryViewFactory(rootStackPane, this);
        List<HBox> row = libraryViewFactory.createBookRows(bookList, 3);
        if (library_inquireBox.getChildren().size() != 0) {
            library_inquireBox.getChildren().clear();
            library_inquireBox.getChildren().addAll(row);
        } else {
            library_inquireBox.getChildren().addAll(row);
        }

        WebResponse resed = api.get("/borrowBook");
        List<BorrowedBook> borrowedbookList=resed.dataList(BorrowedBook.class);
        List<HBox> borrowedRows=libraryViewFactory.createBorrowedbookRows(borrowedbookList);
        library_borrowedBox.getChildren().addAll(borrowedRows);

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

    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0:
                color = "#8F3F7E";
                break;
            case 1:
                color = "#B5305F";
                break;
            case 2:
                color = "#CE584A";
                break;
            case 3:
                color = "#DB8D5C";
                break;
            case 4:
                color = "#DA854E";
                break;
            case 5:
                color = "#E9AB44";
                break;
            case 6:
                color = "#FEE435";
                break;
            case 7:
                color = "#99C286";
                break;
            case 8:
                color = "#01A05E";
                break;
            case 9:
                color = "#4A8895";
                break;
            case 10:
                color = "#16669B";
                break;
            case 11:
                color = "#2F65A5";
                break;
            case 12:
                color = "#4E6A9C";
                break;
            default:
                break;
        }
        return color;
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
                InitPane.setVisible(true);
                StuInfoPane.setVisible(false);
                CoursePane.setVisible(false);
                DormPane.setVisible(false);
                LibraryPane.setVisible(false);
                BankPane.setVisible(false);
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
                InitPane.setVisible(true);
                StuInfoPane.setVisible(false);
                CoursePane.setVisible(false);
                DormPane.setVisible(false);
                LibraryPane.setVisible(false);
                BankPane.setVisible(false);
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


    protected void choosestudent(ActionEvent actionEvent)
    {
        register_radiominis.setPickOnBounds(false);
    }
    @FXML
    protected void chooseadminis(ActionEvent actionEvent)
    {
        register_radiostudent.setPickOnBounds(false);
    }

    @FXML
    protected void libraryInquire(ActionEvent actionEvent) {
        String keyword = library_InquireText.getText();
        if (keyword==null||keyword.equals("")) {
            library_InquireText.setPromptText("请输入关键字");
        } else {
            WebResponse res = api.get("/book/bookName/" + keyword + "/like");
            List<Book> bookList_keyword = res.dataList(Book.class);
            LibraryViewFactory libraryViewFactory = new LibraryViewFactory(rootStackPane, this);
            List<HBox> row = libraryViewFactory.createBookRows(bookList_keyword, 1);
            if (library_inquireBox.getChildren().size() != 0) {
                library_inquireBox.getChildren().clear();
                library_inquireBox.getChildren().addAll(row);
            } else {
                library_inquireBox.getChildren().addAll(row);
            }

        }
    }

    @FXML
    protected void booksearch(KeyEvent keyEvent)
    {
        String keyword = library_InquireText.getText();
        if (keyword==null||keyword.equals("")) {
            library_InquireText.setPromptText("请输入关键字");
        } else {
            WebResponse res = api.get("/book/bookName/" + keyword + "/like");
            List<Book> bookList_keyword = res.dataList(Book.class);
            LibraryViewFactory libraryViewFactory = new LibraryViewFactory(rootStackPane, this);
            List<HBox> row = libraryViewFactory.createBookRows(bookList_keyword, 1);
            if (library_inquireBox.getChildren().size() != 0) {
                library_inquireBox.getChildren().clear();
                library_inquireBox.getChildren().addAll(row);
            } else {
                library_inquireBox.getChildren().addAll(row);
            }

        }
    }

    /**
     * 计算GPA
     * @param reportItems
     * @return
     */
    double calculateGPA(List<CourseReportItem> reportItems) {
        double GPA = 0;
        double totalCredit = 0;
        for(CourseReportItem report : reportItems) {
            totalCredit += report.getCredit();
            int score = report.getScore();
            double credit = report.getCredit();
            if(score >= 96)
                GPA += 4.8 * credit;
            else if(score >= 93)
                GPA += 4.5 * credit;
            else if(score >= 90)
                GPA += 4.0 * credit;
            else if(score >= 86)
                GPA += 3.8 * credit;
            else if(score >= 83)
                GPA += 3.5 * credit;
            else if(score >= 80)
                GPA += 3.0 * credit;
            else if(score >= 76)
                GPA += 2.8 * credit;
            else if(score >= 73)
                GPA += 2.5 * credit;
            else if(score >= 70)
                GPA += 2.0 * credit;
            else if(score >= 66)
                GPA += 1.8 * credit;
            else if(score >= 63)
                GPA += 1.5 * credit;
            else if(score >=60)
                GPA += 1.0 * credit;
        }
        return GPA / totalCredit;
    }

    public void switchInit(ActionEvent actionEvent) {
        DormPane.setVisible(false);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(true);
    }
}


