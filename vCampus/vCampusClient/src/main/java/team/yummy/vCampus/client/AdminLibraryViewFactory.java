package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.deploy.panel.ExceptionListDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import team.yummy.vCampus.models.viewmodel.BookViewModel;

import java.util.*;

import static java.lang.Math.min;

public class AdminLibraryViewFactory {
    private StackPane rootStackPane;
    private AdminViewController adminViewController;

    AdminLibraryViewFactory(StackPane rootStackPane, AdminViewController adminViewController) {
        this.rootStackPane = rootStackPane;
        this.adminViewController = adminViewController;
    }

    public List<HBox> createFullBookRows(List<BookViewModel> bookList) {
        List<HBox> rows = new ArrayList<>();
        for(BookViewModel book:bookList){
            HBox newRow=new HBox();
            VBox InfoCard=new VBox();
            HBox newRow_1=new HBox();
            HBox newRow_2=new HBox();
            HBox newRow_3=new HBox();
            HBox newRow_4=new HBox();
            HBox newRow_5=new HBox();
            HBox newRow_6=new HBox();
            Label title_ID=new Label("ID: ");
            JFXTextField bookID = new JFXTextField(book.getBookId());
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
            JFXTextField bookTotalCount = new JFXTextField(Integer.toString(book.getTotalCount()));
            newRow_5.getChildren().addAll(title_TotalCount,bookTotalCount);
            Label title_AvailableCount=new Label("剩余量： ");
            JFXTextField bookAvailableCount=new JFXTextField(Integer.toString(book.getAvailableCount()));
            newRow_6.getChildren().addAll(title_AvailableCount,bookAvailableCount);


            Label errorText=new Label("");
            bookID.setDisable(true);
            bookName.setDisable(true);
            bookWriter.setDisable(true);
            bookPublisher.setDisable(true);
            bookTotalCount.setDisable(true);
            bookAvailableCount.setDisable(true);


            final JFXButton editBook = new JFXButton("编辑");
            Image bookImage = new Image("./images/Library.jpg", 240, 160, false, true, true);
            ImageView bookImageContent = new ImageView(bookImage);
            InfoCard.getChildren().addAll(newRow_1,newRow_2,newRow_3,newRow_4,newRow_5,newRow_6,errorText);


            InfoCard.setSpacing(10);
            InfoCard.setMinWidth(240);

            HBox BUTTONBOX=new HBox();
            editBook.setButtonType(JFXButton.ButtonType.RAISED);
            editBook.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
            editBook.setTextFill(Color.web("#FFF"));
            editBook.setFont(Font.font(18));
            editBook.setAlignment(Pos.BOTTOM_RIGHT);

            //final BookViewModel b = book;
            final JFXTextField book_ID=bookID;
            final JFXTextField book_Name=bookName;
            final JFXTextField book_Writer=bookWriter;
            final JFXTextField book_Publisher=bookPublisher;
            final JFXTextField book_TotalCount=bookTotalCount;
            final JFXTextField book_AvailableCount=bookAvailableCount;
            final Label error_Text=errorText;
            editBook.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(editBook.getText().equals("编辑")) {
                        editBook.setText("保存");
                        book_ID.setDisable(false);
                        book_Name.setDisable(false);
                        book_Writer.setDisable(false);
                        book_Publisher.setDisable(false);
                        book_TotalCount.setDisable(false);
                        book_AvailableCount.setDisable(false);
                    }
                    else {
                        String ID=book_ID.getText();
                        String Name=book_Name.getText();
                        String Writer=book_Writer.getText();
                        String Publisher=book_Publisher.getText();
                        String TotalCount=book_TotalCount.getText();
                        String AvailableCount=book_AvailableCount.getText();

                        if (ID.length() == 0 || Name.length() == 0 || Writer.length() == 0 || Publisher.length() == 0
                                || TotalCount.length()==0 || AvailableCount.length()==0)
                            error_Text.setText("有空选项!");

                        else {
//                            JSONObject infoToModify = new JSONObject();
//                            infoToModify.put("BookID", ID);
//                            infoToModify.put("BookName", Name);
//                            infoToModify.put("Writer", Writer);
//                            infoToModify.put("Publisher", Publisher);
//                            // TODO
//                            adminViewController.api.patch("/book" + adminViewController.currentAccount.getCampusCardId(), infoToModify.toJSONString());

                            error_Text.setText("");
                            book_ID.setDisable(true);
                            book_Name.setDisable(true);
                            book_Writer.setDisable(true);
                            book_Publisher.setDisable(true);
                            editBook.setText("编辑");
                        }
                    }
                }
            });


            BUTTONBOX.getChildren().add(editBook);
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

    public List<HBox> createEmptyBookRows() {
        List<HBox> rows = new ArrayList<>();
        HBox newRow = new HBox();
        VBox InfoCard = new VBox();
        HBox newRow_1 = new HBox();
        HBox newRow_2 = new HBox();
        HBox newRow_3 = new HBox();
        HBox newRow_4 = new HBox();
        HBox newRow_5 = new HBox();
        HBox newRow_6 = new HBox();
        Label title_ID = new Label("ID: ");
        JFXTextField bookID = new JFXTextField();
        newRow_1.getChildren().addAll(title_ID, bookID);
        Label title_Name1 = new Label("书名: 《");
        JFXTextField bookName = new JFXTextField();
        Label title_Name2 = new Label("》");
        newRow_2.getChildren().addAll(title_Name1, bookName, title_Name2);
        Label title_Writer = new Label("作者： ");
        JFXTextField bookWriter = new JFXTextField();
        newRow_3.getChildren().addAll(title_Writer, bookWriter);
        Label title_Publisher = new Label("出版社： ");
        JFXTextField bookPublisher = new JFXTextField();
        newRow_4.getChildren().addAll(title_Publisher, bookPublisher);
        Label title_TotalCount = new Label("图书总量： ");
        JFXTextField bookTotalCount = new JFXTextField();
        newRow_5.getChildren().addAll(title_TotalCount, bookTotalCount);
        Label title_AvailableCount = new Label("剩余量： ");
        JFXTextField bookAvailableCount = new JFXTextField();
        newRow_6.getChildren().addAll(title_AvailableCount, bookAvailableCount);


        Label errorText = new Label("");
        bookID.setDisable(false);
        bookName.setDisable(false);
        bookWriter.setDisable(false);
        bookPublisher.setDisable(false);
        bookTotalCount.setDisable(false);
        bookAvailableCount.setDisable(false);

        final JFXButton editBook = new JFXButton("保存");
        Image bookImage = new Image("./images/Library.jpg", 240, 160, false, true, true);
        ImageView bookImageContent = new ImageView(bookImage);
        InfoCard.getChildren().addAll(newRow_1, newRow_2, newRow_3, newRow_4, newRow_5, newRow_6,errorText);


        InfoCard.setSpacing(10);
        InfoCard.setMinWidth(240);

        HBox BUTTONBOX = new HBox();
        editBook.setButtonType(JFXButton.ButtonType.RAISED);
        editBook.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"), null, null)));
        editBook.setTextFill(Color.web("#FFF"));
        editBook.setFont(Font.font(18));
        editBook.setAlignment(Pos.BOTTOM_RIGHT);

        BUTTONBOX.getChildren().add(editBook);
        BUTTONBOX.setAlignment(Pos.BOTTOM_RIGHT);

        newRow.getChildren().addAll(bookImageContent, InfoCard, BUTTONBOX);

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

        final JFXTextField book_ID = bookID;
        final JFXTextField book_Name = bookName;
        final JFXTextField book_Writer = bookWriter;
        final JFXTextField book_Publisher = bookPublisher;
        final JFXTextField book_TotalCount = bookTotalCount;
        final JFXTextField book_AvailableCount = bookAvailableCount;
        final Label error_Text = errorText;
        editBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ID = book_ID.getText();
                String Name = book_Name.getText();
                String Writer = book_Writer.getText();
                String Publisher = book_Publisher.getText();
                String TotalCount=book_TotalCount.getText();
                String AvailableCount = book_AvailableCount.getText();

                if (ID.length() == 0 || Name.length() == 0 || Writer.length() == 0 || Publisher.length() == 0
                        || TotalCount.length() == 0 || AvailableCount.length() == 0 )
                    error_Text.setText("有空选项!");
                else {

                    BookViewModel newBook = new BookViewModel();
                    newBook.setBookId(ID);
                    newBook.setBookName(Name);
                    newBook.setWriter(Writer);
                    newBook.setPublisher(Publisher);
                    newBook.setTotalCount(Integer.valueOf(TotalCount));
                    newBook.setAvailableCount(Integer.valueOf(AvailableCount));

                    adminViewController.api.post("/book", JSON.toJSONString(newBook));

                    error_Text.setText("");
                    book_ID.setDisable(true);
                    book_Name.setDisable(true);
                    book_Writer.setDisable(true);
                    book_Publisher.setDisable(true);
                    book_TotalCount.setDisable(true);
                    book_AvailableCount.setDisable(true);
                    editBook.setText("编辑");
                }
            }
        });

        return rows;
    }
}


