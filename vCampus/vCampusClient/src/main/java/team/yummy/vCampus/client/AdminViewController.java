package team.yummy.vCampus.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import team.yummy.vCampus.models.*;

import team.yummy.vCampus.models.viewmodel.BookViewModel;
import team.yummy.vCampus.models.viewmodel.CourseRegisterViewModel;
import team.yummy.vCampus.models.viewmodel.GoodsViewModel;
import team.yummy.vCampus.web.WebResponse;

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
import java.util.*;

public class AdminViewController extends ViewController implements Initializable {
    @FXML private StackPane rootStackPane;
    @FXML private GridPane title;
    @FXML private AnchorPane InitPane;
    @FXML private AnchorPane StuInfoPane;
    @FXML private AnchorPane CoursePane;
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
     * memebers for course page
     */
    List<CourseRegisterViewModel> courseRegister = new ArrayList<CourseRegisterViewModel>();
    @FXML public VBox course_inquireBox;


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
        CoursePane.setVisible(false);
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
                CoursePane.setVisible(false);
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
        CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        InitPane.setVisible(false);

        am_Username.setText(currentAccount.getNickname());
        am_Role.setText(currentAccount.getRole());
    }

    @FXML
    protected void switchStuInfo(ActionEvent actionEvent) {
        StuInfoPane.setVisible(true);
        CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
        //WebResponse res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardID());
        //StuInfo stuInfoViewModel = res.dataList(StuInfo.class, 0);

        /*LocalDate d=stuInfoViewModel.getBirthDate();
        Date date = stuInfoViewModel.getBirthDate();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate d = instant.atZone(zoneId).toLocalDate();
        */
    }


    @FXML
    protected void switchCourse(ActionEvent actionEvent) {
        CoursePane.setVisible(true);
        StuInfoPane.setVisible(false);
        LibraryPane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        //WebResponse res = api.get("/library/book");
        //List<BookViewModel> bookList = res.dataList(BookViewModel.class);
//        AdminCourseViewFactory admincourseViewFactory = new AdminCourseViewFactory(rootStackPane,this);
//        List<HBox> row = admincourseViewFactory.createCourseRows(bookList);
//        if (course_inquireBox.getChildren().size() != 0) {
//            course_inquireBox.getChildren().clear();
//            course_inquireBox.getChildren().addAll(row);
//        } else {
//            course_inquireBox.getChildren().addAll(row);
//        }
    }


    @FXML
    protected void switchLibrary(ActionEvent actionEvent) {
        LibraryPane.setVisible(true);
        StuInfoPane.setVisible(false);
        CoursePane.setVisible(false);
        StorePane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);
        WebResponse res = api.get("/library/book");
        List<BookViewModel> bookList = res.dataList(BookViewModel.class);
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
        CoursePane.setVisible(false);
        LibraryPane.setVisible(false);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        WebResponse res = api.get("/goods");
        List<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);
        AdminStoreViewFactory adminstoreViewFactory = new AdminStoreViewFactory(rootStackPane, this);
        List<HBox> row = adminstoreViewFactory.createFullGoodsRows(goodsList);
        if(store_newItemBox.getChildren().size() != 0) {
            store_newItemBox.getChildren().clear();
            store_newItemBox.getChildren().addAll(row);
        } else {
            store_newItemBox.getChildren().addAll(row);
        }
    }


    @FXML
    protected void booksearch(KeyEvent keyEvent)
    {
        String keyword = library_InquireText.getText();
        if (keyword==null||keyword.equals("")) {
            library_InquireText.setPromptText("按书名搜索");
        } else {
            WebResponse res = api.post("/library/book", keyword);
            List<BookViewModel> bookList_keyword = res.dataList(BookViewModel.class);
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
    protected void addBook(ActionEvent actionEvent) {
        WebResponse res = api.get("/library/book");
        List<BookViewModel> bookList = res.dataList(BookViewModel.class);
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
    protected void addGoods(ActionEvent actionEvent) {
        WebResponse res = api.get("/goods");
        List<GoodsViewModel> goodsList = res.dataList(GoodsViewModel.class);
        AdminStoreViewFactory adminstoreViewFactory = new AdminStoreViewFactory(rootStackPane, this);
        List<HBox> row=adminstoreViewFactory.createEmptyGoodsRows();
        row.addAll(adminstoreViewFactory.createFullGoodsRows(goodsList));
        if (store_newItemBox.getChildren().size() != 0) {
            store_newItemBox.getChildren().clear();
            store_newItemBox.getChildren().addAll(row);
        } else {
            store_newItemBox.getChildren().addAll(row);
        }
    }

    @FXML
    protected void addStudent(ActionEvent actionEvent)
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
    private void editStudent(ActionEvent actionEvent)
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

    @FXML
    private void addCourse(ActionEvent actionEvent) {
        //WebResponse res = api.get("/library/book");
        //List<BookViewModel> bookList = res.dataList(BookViewModel.class);
        AdminCourseViewFactory admincourseViewFactory = new AdminCourseViewFactory(rootStackPane, this);
        List<HBox> row=admincourseViewFactory.createEmptyCourseRows();
        //row.addAll(admincourseViewFactory.createFullCourseRows(bookList));
        if (course_inquireBox.getChildren().size() != 0) {
            course_inquireBox.getChildren().clear();
            course_inquireBox.getChildren().addAll(row);
        } else {
            course_inquireBox.getChildren().addAll(row);
        }
    }
}