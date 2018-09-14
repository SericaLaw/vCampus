package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import team.yummy.vCampus.models.CourseStatusEnum;
import team.yummy.vCampus.models.Goods;
import team.yummy.vCampus.models.viewmodel.CourseRegisterViewModel;
import team.yummy.vCampus.models.viewmodel.CourseScheduleViewModel;
import team.yummy.vCampus.web.WebResponse;

import java.util.*;

import static java.lang.Math.min;
import static javafx.animation.Interpolator.EASE_BOTH;
import static javafx.geometry.Pos.*;
import static sun.swing.MenuItemLayoutHelper.max;

/**
 * 管理员课程视图工厂，用于渲染管理员页面的ViewModel
 * @author peggywashington
 */
public class AdminCourseViewFactory {
    private StackPane rootStackPane;
    private AdminViewController controller;

    AdminCourseViewFactory(StackPane rootStackPane, AdminViewController adminViewController) {
        this.rootStackPane = rootStackPane;
        this.controller = adminViewController;
    }

    public List<HBox> createFullCourseRows(List<CourseRegisterViewModel> courses) {

        List<HBox> rows = new ArrayList<>();
        for(final CourseRegisterViewModel course : courses) {

            final HBox newRow = new HBox();
            newRow.setSpacing(30);
            newRow.setAlignment(CENTER);
            newRow.setPadding(new Insets(40, 30, 5, 40));
            VBox courseInfoCol = new VBox();
            courseInfoCol.setStyle("-fx-spacing: 15");

            HBox courseBasicInfo=new HBox();
            courseBasicInfo.setStyle("-fx-alignment: center-left");
            courseBasicInfo.setStyle("-fx-spacing: 10");

            JFXTextField courseID = new JFXTextField(course.getCourseID());
            courseID.setPromptText("ID");
            courseID.getStyleClass().add("register-item__course-id");
            courseID.setStyle("-fx-font-size: 22;-fx-text-fill: #673AB7;");
            courseID.setMaxSize(80,50);
            courseID.setAlignment(CENTER);

            JFXTextField courseName = new JFXTextField(course.getCourseName());
            courseName.getStyleClass().add("register-item__course-name");
            courseName.setStyle("-fx-font-size: 22;-fx-text-fill: #673AB7;");
            courseName.setMaxSize(160,50);
            courseName.setAlignment(CENTER_LEFT);

            courseBasicInfo.getChildren().addAll(courseID,courseName);

            HBox courseDetailInfo = new HBox();
            courseDetailInfo.setStyle("-fx-alignment: center-left");
            courseDetailInfo.setStyle("-fx-spacing: 10");

            JFXTextField courseTeacher = new JFXTextField(course.getProfName());
            courseTeacher.setStyle("-fx-text-fill: #757575");
            courseTeacher.setAlignment(CENTER);
            courseTeacher.setMaxWidth(130);

            JFXTextField courseCredit = new JFXTextField(course.getCredit().toString());
            courseCredit.setStyle("-fx-text-fill: #757575");
            courseCredit.setAlignment(CENTER);
            courseCredit.setMaxWidth(50);

            courseDetailInfo.getChildren().addAll(courseTeacher, courseCredit);
            courseInfoCol.getChildren().addAll(courseBasicInfo, courseDetailInfo);
            courseInfoCol.getStyleClass().add("register-item");
            newRow.getChildren().add(courseInfoCol);

            VBox courseVenueCol = new VBox();

            JFXTextField courseVenue = new JFXTextField(course.getCourseVenue());
            courseVenue.setStyle("-fx-font-size: 18");
            courseVenue.setAlignment(BASELINE_CENTER);
            courseVenue.setMinHeight(70);
            courseVenueCol.getChildren().addAll(courseVenue);
            courseVenue.getStyleClass().add("register-item");
            newRow.getChildren().add(courseVenueCol);

//            VBox courseScheduleCol = new VBox();
//            for(CourseScheduleViewModel s : course.getCourseSchedule()) {
//
//                HBox courseScheduleRow = new HBox();
//                Label title_weekday=new Label("星期");
//                JFXTextField weekday=new JFXTextField(Integer.toString(s.getWeekDay()));
//                JFXTextField spanstart=new JFXTextField(Integer.toString(s.getSpanStart()));
//                Label slash=new Label("-");
//                JFXTextField spanend=new JFXTextField(Integer.toString(s.getSpanEnd()));
//                courseScheduleRow.getChildren().addAll(title_weekday,weekday);
//                //courseScheduleRow.getChildren().addAll(spanstart,slash,spanend);
//                courseScheduleCol.getChildren().addAll(courseScheduleRow);
//            }
//            newRow.getChildren().add(courseScheduleCol);

            VBox opCol = new VBox();
            opCol.setStyle("-fx-min-width: 50");
            opCol.setStyle("-fx-pref-width: 50");

            Label errorText=new Label("");

            JFXButton editcourse = new JFXButton("编辑");
            editcourse.setButtonType(JFXButton.ButtonType.RAISED);
            editcourse.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
            editcourse.setTextFill(Color.web("#FFF"));
            editcourse.setFont(Font.font(18));
            editcourse.setAlignment(Pos.BOTTOM_CENTER);
            editcourse.setMinWidth(70);

            opCol.setAlignment(BASELINE_LEFT);
            opCol.getChildren().addAll(errorText,editcourse);
            newRow.getChildren().addAll(opCol);

            rows.add(newRow);

            courseID.setDisable(true);
            courseName.setDisable(true);
            courseTeacher.setDisable(true);
            courseCredit.setDisable(true);
            courseVenue.setDisable(true);

            // TODO: 按钮事件
            editcourse.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(editcourse.getText().equals("编辑")) {
                        editcourse.setText("保存");
                        courseID.setDisable(false);
                        courseName.setDisable(false);
                        courseTeacher.setDisable(false);
                        courseCredit.setDisable(false);
                        courseVenue.setDisable(false);
                    }
                    else {

                        String ID=courseID.getText();
                        String Name=courseName.getText();
                        String Teacher=courseTeacher.getText();
                        String Credit=courseCredit.getText();
                        String Venue=courseVenue.getText();

                        if (ID.length() == 0 || Name.length() == 0 || Teacher.length() == 0 || Credit.length() == 0 || Venue.length() ==0)
                            errorText.setText("有空项!");

                        else {

                            Map<String, String> courseToModify = new HashMap();
                            courseToModify.put("CourseId",ID);
                            courseToModify.put("CourseName", Name);
                            courseToModify.put("ProfName", Teacher);
                            courseToModify.put("Credit", Credit);
                            courseToModify.put("CourseVenue", Venue);

                            controller.api.patch("/course/courseId/" + course.getCourseID(), JSON.toJSONString(courseToModify));

                            errorText.setText("");
                            courseID.setDisable(true);
                            courseName.setDisable(true);
                            courseTeacher.setDisable(true);
                            courseCredit.setDisable(true);
                            courseVenue.setDisable(true);
                            editcourse.setText("编辑");
                        }
                    }
                }
            });
        }
        return rows;
    }


    public List<HBox>  createEmptyCourseRows() {
        List<HBox> rows = new ArrayList<>();

        final HBox newRow = new HBox();
        newRow.setSpacing(30);               //////////////////////////////////////////
        newRow.setAlignment(CENTER);
        newRow.setPadding(new Insets(40, 30, 5, 40));
        VBox courseInfoCol = new VBox();
        courseInfoCol.setStyle("-fx-spacing: 15");

        HBox courseBasicInfo=new HBox();
        courseBasicInfo.setStyle("-fx-alignment: center-left");
        courseBasicInfo.setStyle("-fx-spacing: 10");

        JFXTextField courseID = new JFXTextField();
        courseID.setPromptText("ID");
        courseID.getStyleClass().add("register-item__course-id");
        courseID.setStyle("-fx-font-size: 22;-fx-text-fill: #673AB7;");
        courseID.setMaxSize(80,50);
        courseID.setAlignment(CENTER);

        JFXTextField courseName = new JFXTextField();
        courseName.setPromptText("课程名称");
        courseName.getStyleClass().add("register-item__course-name");
        courseName.setStyle("-fx-font-size: 22;-fx-text-fill: #673AB7;");
        courseName.setMaxSize(160,50);
        courseName.setAlignment(CENTER_LEFT);

        courseBasicInfo.getChildren().addAll(courseID,courseName);

        HBox courseDetailInfo = new HBox();
        courseDetailInfo.setStyle("-fx-alignment: center-left");
        courseDetailInfo.setStyle("-fx-spacing: 10");

        JFXTextField courseTeacher = new JFXTextField();
        courseTeacher.setPromptText("教师姓名");
        courseTeacher.setStyle("-fx-text-fill: #757575");
        courseTeacher.setAlignment(CENTER);
        courseTeacher.setMaxWidth(130);

        JFXTextField courseCredit = new JFXTextField();
        courseCredit.setPromptText("学分");
        courseCredit.setStyle("-fx-text-fill: #757575");
        courseCredit.setAlignment(CENTER);
        courseCredit.setMaxWidth(50);

        courseDetailInfo.getChildren().addAll(courseTeacher, courseCredit);
        courseInfoCol.getChildren().addAll(courseBasicInfo, courseDetailInfo);
        courseInfoCol.getStyleClass().add("register-item");
        newRow.getChildren().add(courseInfoCol);

        VBox courseVenueCol = new VBox();

        JFXTextField courseVenue = new JFXTextField();
        courseVenue.setPromptText("教室：如“J6-103”");
        courseVenue.setStyle("-fx-font-size: 18");
        courseVenue.setAlignment(BASELINE_CENTER);
        courseVenue.setMinHeight(70);
        courseVenueCol.getChildren().addAll(courseVenue);
        courseVenue.getStyleClass().add("register-item");
        newRow.getChildren().add(courseVenueCol);

//        VBox courseScheduleCol = new VBox();
//        HBox courseScheduleRow = new HBox();
//        Label title_weekday=new Label("星期");
//        JFXTextField weekday=new JFXTextField();
//        JFXTextField spanstart=new JFXTextField();
//        Label slash=new Label("-");
//        JFXTextField spanend=new JFXTextField();
//        courseScheduleCol.getChildren().addAll(courseScheduleRow);
//
//        newRow.getChildren().add(courseScheduleCol);

        VBox opCol = new VBox();
        opCol.setStyle("-fx-min-width: 50");
        opCol.setStyle("-fx-pref-width: 50");
        Label errorText=new Label("");
        JFXButton editcourse = new JFXButton("保存");
        editcourse.setButtonType(JFXButton.ButtonType.RAISED);
        editcourse.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
        editcourse.setTextFill(Color.web("#FFF"));
        editcourse.setFont(Font.font(18));
        editcourse.setAlignment(Pos.BOTTOM_CENTER);
        editcourse.setMinWidth(70);

        opCol.setAlignment(BASELINE_LEFT);
        opCol.getChildren().addAll(errorText,editcourse);
        newRow.getChildren().addAll(opCol);

        rows.add(newRow);

        // TODO: 按钮事件
        editcourse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    String ID=courseID.getText();
                    String Name=courseName.getText();
                    String Teacher=courseTeacher.getText();
                    String Credit=courseCredit.getText();
                    String Venue=courseVenue.getText();

                if ( ID.length() == 0 || Name.length() == 0 || Teacher.length() == 0 || Credit.length() == 0 || Venue.length() ==0)
                    errorText.setText("有空项!");

                else {

                    Map<String, String> newCourse = new HashMap();
                    newCourse.put("CourseID",ID);
                    newCourse.put("CourseName", Name);
                    newCourse.put("ProfName", Teacher);
                    newCourse.put("Credit", Credit);
                    newCourse.put("CourseVenue", Venue);

                    controller.api.post("/course", JSON.toJSONString(newCourse));
                    WebResponse res = controller.api.get("/course");
                    List<CourseRegisterViewModel> bookList = res.dataList(CourseRegisterViewModel.class);
                    AdminCourseViewFactory admincourseViewFactory = new AdminCourseViewFactory(rootStackPane,controller);
                    List<HBox> row = admincourseViewFactory.createFullCourseRows(bookList);
                    if (controller.course_inquireBox.getChildren().size() != 0) {
                        controller.course_inquireBox.getChildren().clear();
                        controller.course_inquireBox.getChildren().addAll(row);
                    } else {
                        controller.course_inquireBox.getChildren().addAll(row);
                    }
                    errorText.setText("");
                    editcourse.setText("编辑");
                }
            }
        });

        return rows;
    }

}

