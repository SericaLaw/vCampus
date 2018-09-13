package team.yummy.vCampus.client;

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

import java.util.*;

import static java.lang.Math.min;
import static javafx.animation.Interpolator.EASE_BOTH;
import static javafx.geometry.Pos.*;
import static sun.swing.MenuItemLayoutHelper.max;


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
            newRow.setSpacing(100);
            newRow.setAlignment(CENTER);
            newRow.setPadding(new Insets(40, 30, 5, 40));
            VBox courseInfoCol = new VBox();
            courseInfoCol.setStyle("-fx-spacing: 15");

            JFXTextField courseName = new JFXTextField(course.getCourseName());
            courseName.getStyleClass().add("register-item__course-name");
            courseName.setStyle("-fx-font-size: 22;-fx-text-fill: #673AB7;");
            courseName.setMaxSize(240,50);
            courseName.setAlignment(CENTER);

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
            courseInfoCol.getChildren().addAll(courseName, courseDetailInfo);
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
////                JFXTextField spanstart=new JFXTextField(Integer.toString(s.getSpanStart()));
////                Label slash=new Label("-");
////                JFXTextField spanend=new JFXTextField(Integer.toString(s.getSpanEnd()));
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
            editcourse.setAlignment(Pos.BOTTOM_RIGHT);

            opCol.setAlignment(BASELINE_LEFT);
            opCol.getChildren().addAll(errorText,editcourse);
            newRow.getChildren().addAll(opCol);

            rows.add(newRow);

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
                        courseName.setDisable(false);
                        courseTeacher.setDisable(false);
                        courseCredit.setDisable(false);
                        courseVenue.setDisable(false);
                    }
                    else {

                        String Name=courseName.getText();
                        String Teacher=courseTeacher.getText();
                        String Credit=courseCredit.getText();
                        String Venue=courseVenue.getText();

                        if (Name.length() == 0 || Teacher.length() == 0 || Credit.length() == 0 || Venue.length() ==0)
                            errorText.setText("有空项!");

                        else {

//                            course newcourse = new GoodsViewModel();
//                            newGoods.setGoodsName(Name);
//                            newGoods.setImgUrl("./images/item.png");
//                            newGoods.setPrice(Double.valueOf(Price));
//                            newGoods.setTag(Integer.valueOf(Tag));
//
//                            WebResponse res = controller.api.post("/goods", JSON.toJSONString(newGoods));
//
//                            error_Text.setText("");
//                            goods_Name.setDisable(true);
//                            goods_Info.setDisable(true);
//                            goods_Price.setDisable(true);
//                            goods_Tag.setDisable(true);
//                            editgoods.setText("编辑");
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
        newRow.setSpacing(75);               //////////////////////////////////////////
        newRow.setAlignment(CENTER);
        newRow.setPadding(new Insets(40, 30, 5, 40));
        VBox courseInfoCol = new VBox();
        courseInfoCol.setStyle("-fx-spacing: 15");

        JFXTextField courseName = new JFXTextField();
        courseName.setPromptText("课程名称");
        courseName.getStyleClass().add("register-item__course-name");
        courseName.setStyle("-fx-font-size: 22;-fx-text-fill: #673AB7;");
        courseName.setMaxSize(240,50);
        courseName.setAlignment(CENTER);

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
        courseInfoCol.getChildren().addAll(courseName, courseDetailInfo);
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

        VBox courseScheduleCol = new VBox();
        HBox courseScheduleRow = new HBox();
        Label title_weekday=new Label("星期");
        JFXTextField weekday=new JFXTextField();
        JFXTextField spanstart=new JFXTextField();
        Label slash=new Label("-");
        JFXTextField spanend=new JFXTextField();
        courseScheduleCol.getChildren().addAll(courseScheduleRow);

        newRow.getChildren().add(courseScheduleCol);

        VBox opCol = new VBox();
        opCol.setStyle("-fx-min-width: 50");
        opCol.setStyle("-fx-pref-width: 50");
        Label errorText=new Label("");
        JFXButton editcourse = new JFXButton("保存");
        editcourse.setButtonType(JFXButton.ButtonType.RAISED);
        editcourse.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
        editcourse.setTextFill(Color.web("#FFF"));
        editcourse.setFont(Font.font(18));
        editcourse.setAlignment(Pos.BOTTOM_RIGHT);

        opCol.setAlignment(BASELINE_LEFT);
        opCol.getChildren().addAll(errorText,editcourse);
        newRow.getChildren().addAll(opCol);

        rows.add(newRow);

        // TODO: 按钮事件
        editcourse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    String Name=courseName.getText();
                    String Teacher=courseTeacher.getText();
                    String Credit=courseCredit.getText();
                    String Venue=courseVenue.getText();

                    if (Name.length() == 0 || Teacher.length() == 0 || Credit.length() == 0 || Venue.length() ==0)
                        errorText.setText("有空项!");

                    else {

//                            course newcourse = new GoodsViewModel();
//                            newGoods.setGoodsName(Name);
//                            newGoods.setImgUrl("./images/item.png");
//                            newGoods.setPrice(Double.valueOf(Price));
//                            newGoods.setTag(Integer.valueOf(Tag));
//
//                            WebResponse res = controller.api.post("/goods", JSON.toJSONString(newGoods));
//
//                            error_Text.setText("");
//                            goods_Name.setDisable(true);
//                            goods_Info.setDisable(true);
//                            goods_Price.setDisable(true);
//                            goods_Tag.setDisable(true);
//                            editgoods.setText("编辑");
                    }
                }
        });

        return rows;
    }

}

