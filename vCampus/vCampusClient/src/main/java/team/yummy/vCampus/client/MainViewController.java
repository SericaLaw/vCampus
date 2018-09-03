package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.omg.CORBA.PRIVATE_MEMBER;
import team.yummy.vCampus.models.StuInfo;
import team.yummy.vCampus.web.WebResponse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

public class MainViewController extends ViewController implements Initializable {

    @FXML private AnchorPane main_title;
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
    @FXML private TextField si_IDNum;
    @FXML private ComboBox si_Sex;
    @FXML private ComboBox si_Birthyear;
    @FXML private ComboBox si_Birthmonth;
    @FXML private ComboBox si_Birthday;
    @FXML private ComboBox si_Birthplace;
    @FXML private TextField si_Phone;
    @FXML private TextField si_Email;
    @FXML private TextField si_Address;
    @FXML private TextField si_SeniorHigh;
    @FXML private Label si_errorText;

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
    }

    /*public void getStuInfo(ActionEvent actionEvent) {
        WebResponse res = api.get("/stuInfo/campusCardID/" + currentAccount.getCampusCardID());
    }*/

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
        Stage stage = (Stage)main_title.getScene().getWindow();
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
        si_CampusCardID.setText(stuInfoGot.getGPA());
        si_StudentID.setText(stuInfoGot.getStudentID());
        si_Department.setText(stuInfoGot.getDepartment());
        si_Major.setText(stuInfoGot.getMajor());
        si_EnrollmentYear.setText(stuInfoGot.getEnrollmentYear());
        si_IDNum.setText(stuInfoGot.getIDNum());
        si_Sex.setValue(stuInfoGot.getSex());
        Date d=stuInfoGot.getBirthDate();
        Calendar c=Calendar.getInstance();
        c.setTime(d);
        String by=Integer.toString(c.get(Calendar.YEAR));
        String bm=Integer.toString(c.get(Calendar.MONTH)+1);
        String bd=Integer.toString(c.get(Calendar.DATE));
        si_Birthyear.setValue(by);
        si_Birthmonth.setValue(bm);
        si_Birthday.setValue(bd);
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
    }
    @FXML
    protected void editorSave(ActionEvent actionEvent) {
        if(editorSaveStuInfo.getText().equals("编辑"))
        {
            si_IDNum.setDisable(false);
            si_Sex.setDisable(false);
            si_Birthyear.setDisable(false);
            si_Birthmonth.setDisable(false);
            si_Birthday.setDisable(false);
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
            String Birthyear=si_Birthyear.getValue().toString();
            String Birthmonth=si_Birthmonth.getValue().toString();
            String Birthday=si_Birthday.getValue().toString();
            String Birthplace=si_Birthplace.getValue().toString();
            String Phone=si_Phone.getText();
            String Email=si_Email.getText();
            String Address=si_Address.getText();
            String SeniorHigh=si_SeniorHigh.getText();

            if(IDNum.length() == 0 || Sex.length() == 0 || Birthyear.length() == 0 || Birthmonth.length() == 0 || Birthday.length() == 0 ||
                    Birthplace.length() == 0 || Phone.length() == 0 || Phone.length() == 0 || Email.length() == 0 || Address.length() == 0 || SeniorHigh.length() == 0 )
                si_errorText.setText("有空选项!");

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
            String str = Birthyear+"-"+Birthmonth+"-"+Birthday;
            String si_birthdate="";
            try {
                Date d = sdf.parse(str);
                si_birthdate=d.toString();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject infoToModify = new JSONObject();
            infoToModify.put("IDNum", IDNum);
            infoToModify.put("Sex",Sex);
            infoToModify.put("Birthdate", si_birthdate);
            infoToModify.put("Birthplace",Birthplace);
            infoToModify.put("Phone", Phone);
            infoToModify.put("Email",Email);
            infoToModify.put("Address", Address);
            infoToModify.put("SeniorHigh",SeniorHigh);
            api.patch("/stuInfo/campusCardID/"+ currentAccount.getCampusCardID(), infoToModify.toJSONString());

            si_IDNum.setDisable(true);
            si_Sex.setDisable(true);
            si_Birthyear.setDisable(true);
            si_Birthmonth.setDisable(true);
            si_Birthday.setDisable(true);
            si_Birthplace.setDisable(true);
            si_Phone.setDisable(true);
            si_Email.setDisable(true);
            si_Address.setDisable(true);
            si_SeniorHigh.setDisable(true);
            editorSaveStuInfo.setText("编辑");
        }
    }


}
