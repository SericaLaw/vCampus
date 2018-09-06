package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.omg.CORBA.PRIVATE_MEMBER;
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
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    @FXML private AnchorPane li_BorrowedPane;
    @FXML private AnchorPane li_InquirePane;

    @FXML private Label am_CampusCardID;
    @FXML private Label am_Username;
    @FXML private Label am_Role;
    @FXML private Label am_Name;


    /**
     * variables for store page
     */
    @FXML private VBox store_newItemBox;
    @FXML private VBox store_popItemBox;
    @FXML private VBox store_favItemBox;

    @FXML private VBox store_CartBox;

    // 这里放商品列表数据
    private List<Goods> goodList = new ArrayList<Goods>();
    // 这里存放购物车数据

    public List<Goods> goodsToBuy = new ArrayList<>();

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
        am_Name.setText(currentAccount.getLastName()+currentAccount.getFirstName());
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
