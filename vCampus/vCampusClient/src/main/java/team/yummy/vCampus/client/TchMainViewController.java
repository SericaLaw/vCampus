package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.omg.CORBA.PRIVATE_MEMBER;
import team.yummy.vCampus.models.*;

import team.yummy.vCampus.models.viewmodel.CourseScheduleViewModel;
import team.yummy.vCampus.models.viewmodel.TeacherCourseReportViewModel;
import team.yummy.vCampus.web.WebResponse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
/*import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;*/
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.jfoenix.controls.*;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;



public class TchMainViewController extends ViewController implements  Initializable{
    @FXML public GridPane course_scheduleGrid;
    @FXML private StackPane rootStackPane;
    @FXML private GridPane title;
    @FXML private AnchorPane InitPane;
    @FXML private AnchorPane CoursePane;
    @FXML private AnchorPane AccountMagPane;
    @FXML private Label win_close;
    @FXML private Label win_mini;

    @FXML private Label am_CampusCardID;
    @FXML private Label am_Username;
    @FXML private Label am_Role;
    @FXML private Label am_Name;

    @FXML private TableColumn campusIDcardCol;
    @FXML private TableColumn studentnameCol;
    @FXML private TableColumn subjectCol;
    @FXML private TableColumn termCol;
    @FXML private TableColumn gradeCol;
    @FXML private TableView Tch_GradeTV;
    private double xOffset = 0;
    private double yOffset = 0;

    // @FXML public GridPane course_scheduleGrid;
    // data
    List<CourseScheduleViewModel> courseScheduleViewModels = new ArrayList<CourseScheduleViewModel>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {     //点其他栏目的时候要不initpane上的图案淡一点？
        InitPane.setVisible(true);
        CoursePane.setVisible(false);
        AccountMagPane.setVisible(false);

      CourseScheduleViewModel CourseScheduleViewModel1 = new CourseScheduleViewModel("1001", 1, 1, 3, "计算机组成原理", "任国林","J2-102");
        CourseScheduleViewModel CourseScheduleViewModel2 = new CourseScheduleViewModel("2001", 3, 6, 8, "计算机组成原理", "任国林","J2-202");
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
    protected void switchAccountmag(ActionEvent actionEvent) {
        AccountMagPane.setVisible(true);
        CoursePane.setVisible(false);
        InitPane.setVisible(false);

//        WebResponse res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardID());
//        StuInfo stuInfoViewModel = res.dataList(StuInfo.class, 0);
        am_CampusCardID.setText(currentAccount.getCampusCardId());
        am_Username.setText(currentAccount.getNickname());
        am_Role.setText(currentAccount.getRole());
        am_Name.setText(currentAccount.getFirstName()+currentAccount.getLastName());
    }

    @FXML
    protected void switchCourse(ActionEvent actionEvent) {
        CoursePane.setVisible(true);
        AccountMagPane.setVisible(false);
        InitPane.setVisible(false);

        String[] colors = {"#DD9708", "#56AF5A", "#E9433f", "#0EB5CA", "#512DA8"};
        List<String> listColors = new ArrayList<String>();
        for (String color : colors) {
            listColors.add(color);
        }

        courseScheduleViewModels = api.get("/teacher/course").dataList(CourseScheduleViewModel.class);
        for (CourseScheduleViewModel course : courseScheduleViewModels) {
            CourseViewFactory.CourseScheduleViewData data = new CourseViewFactory.CourseScheduleViewData(course);
            JFXButton courseItem = new JFXButton();
            courseItem.textProperty().bindBidirectional(data.contentProperty());
            courseItem.setPrefHeight(200);
            courseItem.setPrefWidth(200);

            if (listColors.isEmpty()) {
                for (String color : colors) {
                    listColors.add(color);
                }
            }
            int colorIndex = (int) (Math.random() * (listColors.size() - 1));
            String itemColor = listColors.get(colorIndex);
            listColors.remove(colorIndex);
            courseItem.setBackground(new Background(new BackgroundFill(Color.web(itemColor), null, null)));
            courseItem.setTextFill(Color.web("#fff"));
            courseItem.setFont(Font.font(14));

            course_scheduleGrid.add(courseItem, course.getWeekDay() * 2 - 1, course.getSpanStart(), 1, course.getSpanEnd() - course.getSpanStart() + 1);
        }

        WebResponse res = api.get("/teacher/record");
        List<TeacherCourseReportViewModel> reportList = res.dataList(TeacherCourseReportViewModel.class);
        List<Person> reportData = new ArrayList<>();
        for(TeacherCourseReportViewModel reort : reportList) {
            reportData.add(new Person(reort));
        }


        campusIDcardCol.setCellValueFactory(new PropertyValueFactory<>("campusIDCard"));
        studentnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        termCol.setCellValueFactory(new PropertyValueFactory<>("term"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));


        final ObservableList<Person> data = FXCollections.observableArrayList();
        data.addAll(reportData);


        Tch_GradeTV.setEditable(true);

        Tch_GradeTV.setItems(data);
        gradeCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
//        gradeCol.setOnEditCommit(
//                (CellEditEvent<Person, String> t) -> {
//                    ((Person) t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())
//                    ).setGrade(t.getNewValue());
//                });
        gradeCol.setOnEditCommit(new EventHandler<CellEditEvent>() {
            @Override
            public void handle(CellEditEvent event) {
                Person s = ((Person) event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                ));
                s.setGrade((String)event.getNewValue());
                String patch = String.format("{\"id\":\"%s\", \"score\":\"%s\"}", s.getUuid(), s.getGrade());
                WebResponse res = api.patch("/teacher/record", patch);
            }
        });

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
                CoursePane.setVisible(false);
                AccountMagPane.setVisible(false);
                dialog.setVisible(false);
                stageController.setStage(App.WELCOME_VIEW_NAME, App.TEACHER_VIEW_NAME);
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.setVisible(false);
            }
        });




    }


    public static class Person{
        private final SimpleStringProperty name = new SimpleStringProperty();
        private final SimpleStringProperty campusIDcard = new SimpleStringProperty();
        private  final SimpleStringProperty term = new SimpleStringProperty();
        private  final SimpleStringProperty grade = new SimpleStringProperty();
        private  final SimpleStringProperty subject = new SimpleStringProperty();
        private final SimpleStringProperty uuid = new SimpleStringProperty();

        public Person(TeacherCourseReportViewModel r) {
            setName(r.getLastName() + r.getFirstName());
            setCampusIDcard(r.getReport().getCampusCardId());
            setTerm(r.getReport().getSemester());
            setGrade(String.valueOf(r.getReport().getScore()));
            setSubject(r.getReport().getCourseName());
            setUuid(r.getReport().getId());
        }

        public String getName(){
            return name.get();
        }

        public void setName(String Name){
            name.set(Name);
        }

        public String getCampusIDCard(){
            return  campusIDcard.get();
        }

        public void setCampusIDcard(String CampusIDCard){
            campusIDcard.set(CampusIDCard);
        }

        public String getTerm(){
            return term.get();
        }

        public void setTerm(String Term){
            term.set(Term);
        }

        public String getGrade(){
            return grade.get();
        }

        public void setGrade(String Grade){
            grade.set(Grade);
        }

        public String getSubject(){
            return subject.get();
        }

        public void setSubject(String Grade){
            subject.set(Grade);
        }
//
//        public TeacherCourseReportViewModel getTeacherCourseReportViewModel() {
//            return teacherCourseReportViewModel;
//        }
//
//        public void setTeacherCourseReportViewModel(TeacherCourseReportViewModel teacherCourseReportViewModel) {
//            this.teacherCourseReportViewModel = teacherCourseReportViewModel;
//        }

        public String getUuid() {
            return uuid.get();
        }

        public void setUuid(String uuid) {
            this.uuid.set(uuid);
        }

        public SimpleStringProperty uuidProperty() {
            return uuid;
        }
    }

    /*public void createCourseSchedule(List<CourseScheduleViewModel> items) {
        String[] colors = {"#DD9708", "#56AF5A", "#E9433f", "#0EB5CA", "#512DA8"};
        List<String> listColors = new ArrayList<String>();
        for (String color : colors) {
            listColors.add(color);
        }

        for (CourseScheduleViewModel course : items) {
            CourseViewFactory.CourseScheduleViewData data = new CourseViewFactory.CourseScheduleViewData(course);
            JFXButton courseItem = new JFXButton();
            courseItem.textProperty().bindBidirectional(data.contentProperty());
            courseItem.setPrefHeight(200);
            courseItem.setPrefWidth(200);

            if (listColors.isEmpty()) {
                for (String color : colors) {
                    listColors.add(color);
                }
            }
            int colorIndex = (int) (Math.random() * (listColors.size() - 1));
            String itemColor = listColors.get(colorIndex);
            listColors.remove(colorIndex);
            courseItem.setBackground(new Background(new BackgroundFill(Color.web(itemColor), null, null)));
            courseItem.setTextFill(Color.web("#fff"));
            courseItem.setFont(Font.font(14));

            course_scheduleGrid.add(courseItem, course.getWeekDay() * 2 - 1, course.getSpanStart(), 1, course.getSpanEnd() - course.getSpanStart() + 1);
        }
    }*/


}
