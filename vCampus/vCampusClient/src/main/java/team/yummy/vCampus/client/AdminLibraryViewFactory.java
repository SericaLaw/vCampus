package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import team.yummy.vCampus.models.Book;
import team.yummy.vCampus.models.BorrowBookRecord;
import team.yummy.vCampus.models.BorrowedBook;
import team.yummy.vCampus.web.WebRequest;
import team.yummy.vCampus.web.WebResponse;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.min;
import static sun.swing.MenuItemLayoutHelper.max;

public class AdminLibraryViewFactory {
    private StackPane rootStackPane;
    private AdminViewController adminViewController;

    AdminLibraryViewFactory(StackPane rootStackPane, AdminViewController adminViewController) {
        this.rootStackPane = rootStackPane;
        this.adminViewController = adminViewController;
    }

    public List<HBox> createBookRows(List<Book> bookList) {
        List<HBox> rows = new ArrayList<>();
        for(Book book:bookList){
            HBox newRow=new HBox();
            VBox InfoCard=new VBox();
            HBox newRow_1=new HBox();
            HBox newRow_2=new HBox();
            HBox newRow_3=new HBox();
            HBox newRow_4=new HBox();
            HBox newRow_5=new HBox();
            HBox newRow_6=new HBox();
            Label title_ID=new Label("ID: ");
            JFXTextField bookID = new JFXTextField(book.getBookID());
            newRow_1.getChildren().addAll(title_ID,bookID);
            Label title_Name1=new Label("书名: 《");
            JFXTextField bookName = new JFXTextField(book.getBookName());
            Label title_Name2=new Label("》");
            newRow_2.getChildren().addAll(title_Name1,bookName,title_Name2);
            Label title_Writer=new Label("作者： ");
            JFXTextField bookWriter=new JFXTextField(book.getWriter());
            newRow_3.getChildren().addAll(title_Writer,bookWriter);
            Label title_Publisher=new Label("出版社： ");
            JFXTextField bookPublisher=new JFXTextField(book.getPublisher());
            newRow_4.getChildren().addAll(title_Publisher,bookPublisher);
            Label title_TotalCount=new Label("图书总量： ");
            Label bookTotalCount=new Label(Integer.toString(book.getTotalCount()));
            newRow_5.getChildren().addAll(title_TotalCount,bookTotalCount);
            Label title_AvailableCount=new Label("剩余量： ");
            Label bookAvailableCount=new Label(Integer.toString(book.getAvailableCount()));
            newRow_6.getChildren().addAll(title_AvailableCount,bookAvailableCount);


            Label errorText=new Label("");
            bookID.setDisable(true);
            bookName.setDisable(true);
            bookWriter.setDisable(true);
            bookPublisher.setDisable(true);
            bookTotalCount.setDisable(true);
            bookAvailableCount.setDisable(true);


            final JFXButton editbook = new JFXButton("编辑");
            Image bookImage = new Image("./images/Library.jpg", 240, 160, false, true, true);
            ImageView bookImageContent = new ImageView(bookImage);
            InfoCard.getChildren().addAll(newRow_1,newRow_2,newRow_3,newRow_4,newRow_5,newRow_6);


            InfoCard.setSpacing(10);
            InfoCard.setMinWidth(240);

            HBox BUTTONBOX=new HBox();
            editbook.setButtonType(JFXButton.ButtonType.RAISED);
            editbook.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
            editbook.setTextFill(Color.web("#FFF"));
            editbook.setFont(Font.font(18));
            editbook.setAlignment(Pos.BOTTOM_RIGHT);

            final Book b = book;
            final JFXTextField book_ID=bookID;
            final JFXTextField book_Name=bookName;
            final JFXTextField book_Writer=bookWriter;
            final JFXTextField book_Publisher=bookPublisher;
            final Label error_Text=errorText;
            editbook.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(editbook.getText().equals("编辑")) {
                        editbook.setText("保存");
                        book_ID.setDisable(false);
                        book_Name.setDisable(false);
                        book_Writer.setDisable(false);
                        book_Publisher.setDisable(false);
                    }
                    else {
                        String ID=book_ID.getText();
                        String Name=book_Name.getText();
                        String Writer=book_Writer.getText();
                        String Publisher=book_Publisher.getText();

                        while (ID.length() == 0 || Name.length() == 0 || Writer.length() == 0 ||
                                Publisher.length() == 0)
                            error_Text.setText("有空选项!");

                        JSONObject infoToModify = new JSONObject();
                        infoToModify.put("BookID", ID);
                        infoToModify.put("BookName", Name);
                        infoToModify.put("Writer", Writer);
                        infoToModify.put("Publisher", Publisher);
                        adminViewController.api.patch("/book" + adminViewController.currentAccount.getCampusCardID(), infoToModify.toJSONString());

                        error_Text.setText("");
                        book_ID.setDisable(true);
                        book_Name.setDisable(true);
                        book_Writer.setDisable(true);
                        book_Publisher.setDisable(true);
                        editbook.setText("编辑");
                    }
                }
            });


            BUTTONBOX.getChildren().add(editbook);
            BUTTONBOX.setAlignment(Pos.BOTTOM_RIGHT);

            newRow.getChildren().addAll(bookImageContent,InfoCard,BUTTONBOX);

            newRow.setAlignment(Pos.CENTER_LEFT);
            newRow.setPrefWidth(300);
            newRow.setPadding(new Insets(40, 30, 5, 40));
            newRow.setSpacing(20);

            rows.add(newRow);

            Rectangle clip = new Rectangle(
                    bookImage.getRequestedWidth(), bookImage.getRequestedHeight()
            );
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            bookImageContent.setClip(clip);
        }

        return rows;
    }

}


