package team.yummy.vCampus.client;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import team.yummy.vCampus.models.*;

import java.util.ArrayList;
import java.util.List;

public class CourseViewFactory {
    MainViewController controller;
    public CourseViewFactory(MainViewController controller) {
        this.controller = controller;
    }

    public void createCourseSchedule(List<CourseScheduleItem> items) {
        String[] colors = {"#DD9708", "#56AF5A", "#E9433f", "#0EB5CA", "#512DA8"};
        List<String> listColors = new ArrayList<String>();
        for (String color : colors) {
            listColors.add(color);
        }

        for (CourseScheduleItem course : items) {
            MainViewController.CourseScheduleViewData data = new MainViewController.CourseScheduleViewData(course);
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

            controller.course_scheduleGrid.add(courseItem, course.getWeekDay() * 2 - 1, course.getSpanStart(), 1, course.getSpanEnd() - course.getSpanStart() + 1);
        }
    }

    public void createCourseReport(List<CourseReportItem> items) {
        double totalScore = 0;
        double totalCredit = 0;
        for(CourseReportItem report : items) {
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

            controller.course_reportBox.getChildren().add(newRow);
        }

        controller.score_avgScore.setText(String.valueOf(totalScore / items.size()));
        controller.score_totalCredit.setText(String.valueOf(totalCredit));
        String gpa = String.valueOf(controller.calculateGPA(items));
        if(gpa.length() > 3)
            gpa = gpa.substring(0, 4);
        controller.score_avgGPA.setText(gpa);
    }

    public void createCourseRegister(CourseRegister items) {
        for(CourseRegisterItem course : items.getCourseList()) {
            VBox courseInfoCol = new VBox();

            courseInfoCol.setStyle("-fx-spacing: 10");


            Label courseName = new Label(course.getCourseName());
            courseName.getStyleClass().add("register-item__course-name");

            HBox courseDetailInfo = new HBox();
            courseDetailInfo.setStyle("-fx-alignment: center-left");
            courseDetailInfo.setStyle("-fx-spacing: 10");

            Label courseTeacher = new Label("教师：" + course.getProfName());
            courseTeacher.setStyle("-fx-text-fill: #757575");
            Label courseCredit = new Label("学分：" + course.getCredit());
            courseCredit.setStyle("-fx-text-fill: #757575");
            courseDetailInfo.getChildren().addAll(courseTeacher, courseCredit);
            courseInfoCol.getChildren().addAll(courseName, courseDetailInfo);

            courseInfoCol.getStyleClass().add("register-item");
            controller.register_courseNameCol.getChildren().addAll(courseInfoCol);

            VBox courseVenueCol = new VBox();

            Label courseVenue = new Label("@"+course.getCourseVenue());
            courseVenue.setStyle("-fx-font-size: 18");
            courseVenueCol.getChildren().addAll(courseVenue);
            courseVenue.getStyleClass().add("register-item");
            controller.course_venueCol.getChildren().addAll(courseVenueCol);

            VBox courseScheduleCol = new VBox();
            for(Schedule s : course.getCourseSchedule()) {
                String schedule = "";
                schedule += "星期" + s.getWeekDay() + "（" + s.getSpanStart() + "-" + s.getSpanEnd() + "）";
                Label courseSchedule = new Label(schedule);
                courseScheduleCol.getChildren().addAll(courseSchedule);
            }

            courseScheduleCol.getStyleClass().add("register-item");
            controller.register_scheduleCol.getChildren().addAll(courseScheduleCol);

            VBox courseStatusCol = new VBox();
            Label courseStatus = new Label(String.valueOf(course.getStuAttendCount()) + " / " + String.valueOf(course.getStuLimitCount()));
            courseStatus.setStyle("-fx-font-size: 18");
            courseStatusCol.getChildren().addAll(courseStatus);
            courseStatusCol.getStyleClass().add("register-item");
            controller.register_statusCol.getChildren().addAll(courseStatusCol);

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

            controller.register_opCol.getChildren().addAll(opCol);
        }
    }
}
