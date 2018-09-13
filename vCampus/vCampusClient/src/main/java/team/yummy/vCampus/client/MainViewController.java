package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.effects.JFXDepthManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import team.yummy.vCampus.models.*;

import team.yummy.vCampus.models.viewmodel.*;
import team.yummy.vCampus.web.WebResponse;

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

import com.jfoenix.controls.*;

public class MainViewController extends ViewController implements Initializable {

    @FXML public Button Bt_Init, Bt_StuInfo, Bt_Course, Bt_Dorm, Bt_Bank, Bt_Store, Bt_Account, Bt_Library;
    @FXML public StackPane rootStackPane;
    @FXML public GridPane title;
    @FXML public AnchorPane InitPane;
    @FXML public AnchorPane StuInfoPane;
    @FXML public AnchorPane CoursePane;
    @FXML public AnchorPane DormPane;
    @FXML public AnchorPane LibraryPane;
    @FXML public AnchorPane BankPane;
    @FXML public AnchorPane StorePane;
    @FXML public AnchorPane AccountMagPane;
    @FXML public Label win_close;
    @FXML public Label win_mini;

    /**
     * members for stu info page
     */

    @FXML public Label si_GPA;
    @FXML public Label si_SRTP;
    @FXML public Label si_LAC;
    @FXML public Label si_Name;
    @FXML public Label si_CampusCardID;
    @FXML public Label si_StudentID;
    @FXML public Label si_Department;
    @FXML public Label si_Major;
    @FXML public Label si_EnrollmentYear;
    @FXML public Button editorSaveStuInfo;
    @FXML public JFXTextField si_IDNum;
    @FXML public JFXComboBox si_Sex;
    @FXML public DatePicker si_Birthdate;
    @FXML public JFXComboBox si_Birthplace;
    @FXML public JFXTextField si_Phone;
    @FXML public JFXTextField si_Email;
    @FXML public JFXTextField si_Address;
    @FXML public JFXTextField si_SeniorHigh;
    @FXML public Label si_errorText;

    StuInfoViewModel stuInfoViewModel;


    @FXML public Label content__Store__Cart__GrandTotalPrice;
    @FXML public Button content__Store__Cart__BatchRemove;
    @FXML public Button content__Store__Cart__Pay;

    @FXML public Label am_CampusCardID;
    @FXML public Label am_Nickname;
    @FXML public Label am_Role;
    @FXML public Label am_Name;

    @FXML private Label Lbank_name;
    @FXML private Label Lbank_num;
    @FXML private Label Lbank_balance;
    @FXML private Label Lbank_wat_elec;
    @FXML private Label Lbank_tuition;

    @FXML private Label DormID;
    @FXML private Label DormScoreID;
    @FXML private Label DormCostID;
    @FXML private TableView DormTable;
    @FXML private TableColumn content__d__Year;
    @FXML private TableColumn content__d__Month;
    @FXML private TableColumn content__d__Score;
    @FXML private TableColumn content__d__Fare;

    @FXML public VBox library_inquireBox;
    @FXML public TextField library_InquireText;
    @FXML protected VBox library_borrowedBox;
    public List<BorrowRecordViewModel> borrowedbookList=new ArrayList<>();

    @FXML private TableColumn time;
    @FXML private TableColumn money;
    @FXML private TableView  main_bankTV;

    /**
     * members for store page
     */
    @FXML public VBox store_newItemBox;
    @FXML public VBox store_popItemBox;
    @FXML public VBox store_favItemBox;

    @FXML public VBox store_CartBox;

    // 这里放商品列表数据
    public List<GoodsViewModel> goodList = new ArrayList<>();
    public List<GoodsViewModel> goodsToBuy = new ArrayList<>();

    /**
     * members for course schedule
     */
    @FXML public GridPane course_scheduleGrid;

    public List<CourseScheduleViewModel> courseScheduleViewModels = new ArrayList<>();

    /**
     * members for course report
     */
    @FXML public VBox course_reportBox;
    @FXML public VBox course_reportContent;
    @FXML public Label score_avgScore;
    @FXML public Label score_avgGPA;
    @FXML public Label score_totalCredit;
    @FXML public HBox course_reportHeading;

    /**
     * members for course register
     */
    @FXML public HBox course_registerBox;
    @FXML public VBox register_courseNameCol;
    @FXML public VBox course_venueCol;
    @FXML public VBox register_scheduleCol;
    @FXML public VBox register_statusCol;
    @FXML public VBox register_opCol;
    // 数据
    public List<CourseReportViewModel> courseReportViewModels = new ArrayList<>();

    public double xOffset = 0;
    public double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {     //点其他栏目的时候要不initpane上的图案淡一点？
        togglePane(InitPane, Bt_Init);


//        // 模拟服务器返回的信息
//
//        Goods good1 = new Goods("1","鞋子","123","白色", "./images/item.png");
//        Goods good2 = new Goods("2","鞋子","123", "红色","./images/item.png");
//        Goods good3 = new Goods("3","鞋子","123", "黑色","./images/item.png");
//        Goods good4 = new Goods("4","鞋子","123", "蓝色","./images/item.png");
//        Goods good5 = new Goods("5","鞋子","123", "黑色","./images/item.png");
//        Goods good6 = new Goods("6","鞋子","123", "蓝色","./images/item.png");
//
//        goodList.add(good1);
//        goodList.add(good2);
//        goodList.add(good3);
//        goodList.add(good4);
//        goodList.add(good5);
//        goodList.add(good6);
//
//        goodsToBuy.add(good1);
//        goodsToBuy.add(good2);

        CourseScheduleViewModel CourseScheduleViewModel1 = new CourseScheduleViewModel("1001", 1, 1, 3, "数据结构", "邓俊辉","J2-102");
        CourseScheduleViewModel CourseScheduleViewModel2 = new CourseScheduleViewModel("2001", 1, 12, 13, "算法", "图灵","J2-202");
        courseScheduleViewModels.add(CourseScheduleViewModel1);
        courseScheduleViewModels.add(CourseScheduleViewModel2);
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
    protected void switchAccountMng(ActionEvent actionEvent) {
        togglePane(AccountMagPane, Bt_Account);

        am_CampusCardID.setText(currentAccount.getCampusCardId());
        am_Nickname.setText(currentAccount.getNickname());
        am_Role.setText(currentAccount.getRole());
        am_Name.setText(currentAccount.getFirstName()+currentAccount.getLastName());
    }

    @FXML
    protected void switchStuInfo(ActionEvent actionEvent) {
        togglePane(StuInfoPane, Bt_StuInfo);

        WebResponse res = api.get("/stuInfo/gpa");
        res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardId());

        stuInfoViewModel = res.dataList(StuInfoViewModel.class, 0);

        si_Name.setText(currentAccount.getLastName()+currentAccount.getFirstName());
        si_GPA.setText(stuInfoViewModel.getGpa().toString());
        si_SRTP.setText(stuInfoViewModel.getSrtp().toString());
        si_LAC.setText(stuInfoViewModel.getLectureAttendCount().toString());
        si_CampusCardID.setText(stuInfoViewModel.getCampusCardId());
        si_StudentID.setText(stuInfoViewModel.getStudentId());
        si_Department.setText(stuInfoViewModel.getDepartment());
        si_Major.setText(stuInfoViewModel.getMajor());
        si_EnrollmentYear.setText(stuInfoViewModel.getEnrollmentYear().toString());
        si_IDNum.setText(stuInfoViewModel.getIdNum());
        si_Sex.setValue(stuInfoViewModel.getSex());
        //LocalDate d=stuInfoViewModel.getBirthDate();
        Date date = stuInfoViewModel.getBirthdate();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate d = instant.atZone(zoneId).toLocalDate();
        si_Birthdate.setValue(d);
        si_Birthplace.setValue(stuInfoViewModel.getBirthplace());
        si_Phone.setText(stuInfoViewModel.getPhone());
        si_Email.setText(stuInfoViewModel.getEmail());
        si_Address.setText(stuInfoViewModel.getAddress());
        si_SeniorHigh.setText(stuInfoViewModel.getSeniorHigh());
    }



    @FXML
    protected void switchCourse(ActionEvent actionEvent) {
        togglePane(CoursePane, Bt_Course);
        WebResponse res = api.get("/stuInfo/gpa");
        res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardId());
        stuInfoViewModel = res.dataList(StuInfoViewModel.class, 0);

        JFXDepthManager.setDepth(course_reportHeading, 1);

        CourseViewFactory factory = new CourseViewFactory(this);

        res = api.get("/course/schedule");
        courseScheduleViewModels = res.dataList(CourseScheduleViewModel.class);
        factory.createCourseSchedule(courseScheduleViewModels);

        /**
         * 成绩查询
         */

        res = api.get("/course/report");
        courseReportViewModels = res.dataList(CourseReportViewModel.class);
        factory.createCourseReport(courseReportViewModels);

        /**
         * 选课
         */
        res = api.get("/course/register");
        List<CourseRegisterViewModel> courseRegister = res.dataList(CourseRegisterViewModel.class);
        factory.createCourseRegister(courseRegister);
    }
    @FXML
    protected void switchDorm(ActionEvent actionEvent)
    {
        togglePane(DormPane, Bt_Dorm);
        DormPane.setVisible(true);
        BankPane.setVisible(false);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        WebResponse res = api.get("/dorm/info");
        List<DormInfoViewModel> dormInfoList = res.dataList(DormInfoViewModel.class);

/*        for(DormInfoViewModel DormInfo:dormInfoList)
        {
            if(DormInfo.getCampusCardID() == currentAccount.getCampusCardId())
            {
                DormID.setText(DormInfo.getDormID());
//                DormScoreID.setText(String.valueOf(DormInfo.getRecords()));
//                DormCostID.setText(String.valueOf(DormInfo.get));
                break;
            }
        }*/
        //获取卫生和水电记录

        content__d__Year.setCellValueFactory(new PropertyValueFactory<>("year"));
        content__d__Month.setCellValueFactory(new PropertyValueFactory<>("month"));
        content__d__Score.setCellValueFactory(new PropertyValueFactory<>("DormScore"));
        content__d__Fare.setCellValueFactory(new PropertyValueFactory<>("DormCost"));

        final ObservableList<Dorm> data = FXCollections.observableArrayList(
                new Dorm("2015","5","15","25")
        );

        DormTable.setItems(data);

    }
    @FXML
    protected void switchBank(ActionEvent actionEvent)
    {
        togglePane(BankPane, Bt_Bank);
        BankPane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
        WebResponse res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardId());
        // StuInfo stuInfoViewModel = res.dataList(StuInfo.class, 0);
        Lbank_name.setText(currentAccount.getFirstName()+currentAccount.getLastName());
        // Lbank_num.setText(currentAccount.get);  获取账号尾号
        // Lbank_balance.setText(currentAccount.get);  获取账户余额
        // Lbank_wat_elec.setText(currentAccount.get);  获取水电费
        // Lbank_tuition.setText(currentAccount.get);  获取学费
        money.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        time.setCellValueFactory(new PropertyValueFactory<>("deposittime"));
        final ObservableList<Bank> BANK = FXCollections.observableArrayList(
                new Bank("不知道","25点")
        );
        main_bankTV.setItems(BANK);

    }
    @FXML
    protected void switchLibrary(ActionEvent actionEvent) {
        togglePane(LibraryPane, Bt_Library);
        WebResponse res = api.get("/library/book");
        List<BookViewModel> bookList = res.dataList(BookViewModel.class);
        LibraryViewFactory libraryViewFactory = new LibraryViewFactory(rootStackPane, this);
        List<HBox> row = libraryViewFactory.createBookRows(bookList, 3);
        if (library_inquireBox.getChildren().size() != 0) {
            library_inquireBox.getChildren().clear();
            library_inquireBox.getChildren().addAll(row);
        } else {
            library_inquireBox.getChildren().addAll(row);
        }

        res = api.get("/library/borrow");
        List<BorrowRecordViewModel> borrowedBookList = res.dataList(BorrowRecordViewModel.class);
        List<HBox> borrowedRows = libraryViewFactory.createBorrowedbookRows(borrowedBookList);
        library_borrowedBox.getChildren().addAll(borrowedRows);

    }
    @FXML
    protected void switchStore(ActionEvent actionEvent) {
        togglePane(StorePane, Bt_Store);

        WebResponse res = api.get("/goods");
        List<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);
        List<GoodsViewModel> goodsList1=new ArrayList<>();
        List<GoodsViewModel> goodsList2=new ArrayList<>();
        List<GoodsViewModel> goodsList3=new ArrayList<>();
        StoreViewFactory storeViewFactory = new StoreViewFactory(rootStackPane, this);
        //List<HBox> row = storeViewFactory.createStoreRows(goodsList, 3);

        for (GoodsViewModel goods : goodsList) {
            if(goods.getTag() == 1)
            {
                goodsList1.add(goods);
            }
            else if(goods.getTag() == 2)
            {
                goodsList2.add(goods);
            }
            else if(goods.getTag() == 3)
            {
                goodsList3.add(goods);
            }
        }

        List<HBox> row1 = storeViewFactory.createStoreRows(goodsList1, 2);
        List<HBox> row2 = storeViewFactory.createStoreRows(goodsList2, 2);
        List<HBox> row3 = storeViewFactory.createStoreRows(goodsList3, 2);

        if(store_newItemBox.getChildren().size() != 0) {
            store_newItemBox.getChildren().clear();
            store_newItemBox.getChildren().addAll(row1);
        } else {
            store_newItemBox.getChildren().addAll(row1);
        }

        if(store_popItemBox.getChildren().size() != 0) {
            store_popItemBox.getChildren().clear();
            store_popItemBox.getChildren().addAll(row2);
        } else {
            store_popItemBox.getChildren().addAll(row2);
        }

        if(store_favItemBox.getChildren().size() != 0) {
            store_favItemBox.getChildren().clear();
            store_favItemBox.getChildren().addAll(row3);
        } else {
            store_favItemBox.getChildren().addAll(row3);
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
            api.patch("/stuInfo/campusCardID/"+ currentAccount.getCampusCardId(), infoToModify.toJSONString());

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
                togglePane(InitPane, Bt_Init);
                stageController.setStage(App.WELCOME_VIEW_NAME, App.MAIN_VIEW_NAME);
                dialog.setVisible(false);
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
                api.delete("/account/campusCardId/"+ currentAccount.getCampusCardId());
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
    protected void libraryInquire(ActionEvent actionEvent) {
        String keyword = library_InquireText.getText();
        if (keyword==null||keyword.equals("")) {
            library_InquireText.setPromptText("请输入关键字");
        } else {
            WebResponse res = api.post("/library/book", keyword);
            List<BookViewModel> bookList_keyword = res.dataList(BookViewModel.class);
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
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            String keyword = library_InquireText.getText();
            if (keyword==null||keyword.equals("")) {
                library_InquireText.setPromptText("请输入关键字");
            } else {
                WebResponse res = api.post("/library/book", keyword);
                List<BookViewModel> bookList_keyword = res.dataList(BookViewModel.class);
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

    }


    @FXML
    protected void paywatelec(ActionEvent actionEvent)
    {
        final JFXDialog dialog = new JFXDialog();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        VBox vb = new VBox();
        Label text =new Label("请输入密码：");
        text.setFont(Font.font(20));
        text.setTextFill(Color.web("black"));
        text.setPadding(new Insets(10,0,0,40));
        text.setPrefSize(160,200);
        JFXPasswordField password = new JFXPasswordField();
        password.setPadding(new Insets(10,0,0,0));
        hb1.setPadding(new Insets(0,10,30,280));
        JFXButton ok=new JFXButton("确定");
        JFXButton cancel=new JFXButton("取消");
        ok.setFont(Font.font(17));
        ok.setPrefSize(80,35);
        ok.setBackground(new Background(new BackgroundFill(Color.web("#B15BFF"),null,null)));
        ok.setTextFill(Color.web("#fff"));
        cancel.setFont(Font.font(17));
        cancel.setPrefSize(80,35);
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#707070"),null,null)));
        cancel.setTextFill(Color.web("#fff"));
        hb1.setSpacing(35);
        hb1.getChildren().addAll(ok,cancel);
        vb.setPrefSize(500,250);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(text,password);
        vb.getChildren().addAll(hb2,hb1);
        dialog.setContent(vb);
        dialog.show(rootStackPane);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //支付的操作
                // 1.比较密码输入是否正确
                // 2.比较余额是否足够支付
                // 3.成功支付后 欠费清零 余额减少 消费记录增加
                dialog.setVisible(false);
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.setVisible(false);            }
        });
    }

    @FXML
    protected void paytuition(ActionEvent actionEvent)
    {
        final JFXDialog dialog = new JFXDialog();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        VBox vb = new VBox();
        Label text =new Label("请输入密码：");
        text.setFont(Font.font(20));
        text.setTextFill(Color.web("black"));
        text.setPadding(new Insets(10,0,0,40));
        text.setPrefSize(160,200);
        JFXPasswordField password = new JFXPasswordField();
        password.setPadding(new Insets(10,0,0,0));
        hb1.setPadding(new Insets(0,10,30,280));
        JFXButton ok=new JFXButton("确定");
        JFXButton cancel=new JFXButton("取消");
        ok.setFont(Font.font(17));
        ok.setPrefSize(80,35);
        ok.setBackground(new Background(new BackgroundFill(Color.web("#B15BFF"),null,null)));
        ok.setTextFill(Color.web("#fff"));
        cancel.setFont(Font.font(17));
        cancel.setPrefSize(80,35);
        cancel.setBackground(new Background(new BackgroundFill(Color.web("#707070"),null,null)));
        cancel.setTextFill(Color.web("#fff"));
        hb1.setSpacing(35);
        hb1.getChildren().addAll(ok,cancel);
        vb.setPrefSize(500,250);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(text,password);
        vb.getChildren().addAll(hb2,hb1);
        dialog.setContent(vb);
        dialog.show(rootStackPane);

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //支付的操作
                // 1.比较密码输入是否正确
                // 2.比较余额是否足够支付
                // 3.成功支付后 欠费清零 余额减少 消费记录增加
                dialog.setVisible(false);
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.setVisible(false);            }
        });
    }
    public void switchInit(ActionEvent actionEvent) {
        togglePane(InitPane, Bt_Init);
    }
    public void togglePane(Pane paneToDisplay, Button button) {
        StorePane.setVisible(false);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        DormPane.setVisible(false);
        LibraryPane.setVisible(false);
        BankPane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        String defaultStyle = "-fx-pref-width: 250;\n" +
                "    -fx-background-color: #fff;\n" +
                "    -fx-pref-height: 50;\n" +
                "    -fx-padding:  0 20;\n" +
                "    -fx-border-width: 0 0 0 6px;\n" +
                "    -fx-border-height: 50;\n" +
                "    -fx-border-color: transparent;\n" +
                "    -fx-font-size: 20;\n" +
                "    -fx-alignment: center-left;";

        String focusedStyle = "-fx-text-fill: #673AB7; -fx-border-color: transparent transparent transparent linear-gradient(to bottom right, #7C4DFF, #673AB7);";

        // 暂时性代码
        Bt_Init.setStyle(defaultStyle);
        Bt_StuInfo.setStyle(defaultStyle);
        Bt_Course.setStyle(defaultStyle);
        Bt_Library.setStyle(defaultStyle);
        Bt_Bank.setStyle(defaultStyle);
        Bt_Dorm.setStyle(defaultStyle);
        Bt_Store.setStyle(defaultStyle);
        Bt_Account.setStyle(defaultStyle);

        button.setStyle(focusedStyle);

        paneToDisplay.setVisible(true);
    }

    public class Bank {
        private final SimpleStringProperty deposit;
        private final SimpleStringProperty deposittime;


        private Bank(String Deposit, String Deposittime) {
            this.deposit = new SimpleStringProperty(Deposit);
            this.deposittime = new SimpleStringProperty(Deposittime);

        }

        public String getDeposit() {
            return deposit.get();
        }

        public void setDeposit(String Deposit) {
            deposit.set(Deposit);
        }
        public String getDeposittime() {
            return deposittime.get();
        }

        public void setDeposittime(String Deposittime) {
            deposittime.set(Deposittime);
        }
    }
    public class Dorm
    {
        private final SimpleStringProperty year;
        private final SimpleStringProperty month;
        private final SimpleStringProperty DormScore;
        private final SimpleStringProperty DormCost;

        private Dorm(String Year,String mon,String Sco,String cost)
        {
            this.year = new SimpleStringProperty(Year);
            this.month = new SimpleStringProperty(mon);
            this.DormScore = new SimpleStringProperty(Sco);
            this.DormCost = new SimpleStringProperty(cost);
        }

        public String getYear()
        {
            return year.get();
        }

        public void setYear(String y)
        {
            year.set(y);
        }

        public String getMonth()
        {
            return month.get();
        }

        public void setMonth(String m)
        {
            month.set(m);
        }

        public String getScore()
        {
            return DormScore.get();
        }

        public void setScore(String s)
        {
            DormScore.set(s);
        }

        public String getCost()
        {
            return DormCost.get();
        }

        public void setCost(String c)
        {
            DormCost.set(c);
        }
    }

}





