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
import static javafx.geometry.Pos.BASELINE_CENTER;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;
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
            VBox courseInfoCol = new VBox();
            courseInfoCol.setStyle("-fx-spacing: 10");

            JFXTextField courseName = new JFXTextField(course.getCourseName());
            courseName.getStyleClass().add("register-item__course-name");

            HBox courseDetailInfo = new HBox();
            courseDetailInfo.setStyle("-fx-alignment: center-left");
            courseDetailInfo.setStyle("-fx-spacing: 10");

            JFXTextField courseTeacher = new JFXTextField(course.getProfName());
            courseTeacher.setStyle("-fx-text-fill: #757575");
            JFXTextField courseCredit = new JFXTextField(course.getCredit().toString());
            courseCredit.setStyle("-fx-text-fill: #757575");
            courseDetailInfo.getChildren().addAll(courseTeacher, courseCredit);
            courseInfoCol.getChildren().addAll(courseName, courseDetailInfo);
            courseInfoCol.getStyleClass().add("register-item");
            newRow.getChildren().add(courseInfoCol);

            VBox courseVenueCol = new VBox();

            JFXTextField courseVenue = new JFXTextField(course.getCourseVenue());
            courseVenue.setStyle("-fx-font-size: 18");
            courseVenueCol.getChildren().addAll(courseVenue);
            courseVenue.getStyleClass().add("register-item");
            newRow.getChildren().add(courseVenueCol);

            VBox courseScheduleCol = new VBox();
            for(CourseScheduleViewModel s : course.getCourseSchedule()) {

                HBox courseScheduleRow = new HBox();
                Label title_weekday=new Label("星期");
                JFXTextField weekday=new JFXTextField(Integer.toString(s.getWeekDay()));
                JFXTextField spanstart=new JFXTextField(Integer.toString(s.getSpanStart()));
                Label slash=new Label("-");
                JFXTextField spanend=new JFXTextField(Integer.toString(s.getSpanEnd()));
                courseScheduleRow.getChildren().addAll(title_weekday,weekday,spanstart,slash,spanend);
                courseScheduleCol.getChildren().addAll(courseScheduleRow);
            }
            newRow.getChildren().add(courseScheduleCol);

            VBox opCol = new VBox();
            opCol.setStyle("-fx-min-width: 50");
            opCol.setStyle("-fx-pref-width: 50");

            JFXButton buttonOp = new JFXButton("编辑");

            // TODO: 按钮事件


            buttonOp.setButtonType(JFXButton.ButtonType.RAISED);
            opCol.getChildren().addAll(buttonOp);
            newRow.getChildren().addAll(opCol);

            rows.add(newRow);
        }
        return rows;
    }


    public List<HBox>  createEmptyCourseRows() {
        List<HBox> rows = new ArrayList<>();

        final HBox newRow = new HBox();
        VBox courseInfoCol = new VBox();
        courseInfoCol.setStyle("-fx-spacing: 10");

        JFXTextField courseName = new JFXTextField();
        courseName.setPromptText("课程名称");
        courseName.getStyleClass().add("register-item__course-name");

        HBox courseDetailInfo = new HBox();
        courseDetailInfo.setStyle("-fx-alignment: center-left");
        courseDetailInfo.setStyle("-fx-spacing: 10");

        JFXTextField courseTeacher = new JFXTextField();
        courseTeacher.setPromptText("教师姓名");
        courseTeacher.setStyle("-fx-text-fill: #757575");
        JFXTextField courseCredit = new JFXTextField();
        courseCredit.setPromptText("学分");
        courseCredit.setStyle("-fx-text-fill: #757575");
        courseDetailInfo.getChildren().addAll(courseTeacher, courseCredit);
        courseInfoCol.getChildren().addAll(courseName, courseDetailInfo);
        courseInfoCol.getStyleClass().add("register-item");
        newRow.getChildren().add(courseInfoCol);

        VBox courseVenueCol = new VBox();

        JFXTextField courseVenue = new JFXTextField();
        courseVenue.setPromptText("教室：如“J6-103”");
        courseVenue.setStyle("-fx-font-size: 18");
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
        JFXButton buttonOp = new JFXButton("编辑");

        // TODO: 按钮事件


        buttonOp.setButtonType(JFXButton.ButtonType.RAISED);
        opCol.getChildren().addAll(buttonOp);
        newRow.getChildren().addAll(opCol);

        rows.add(newRow);
        return rows;
    }

}

