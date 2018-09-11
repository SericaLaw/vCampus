package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import team.yummy.vCampus.models.viewmodel.BookViewModel;
import team.yummy.vCampus.models.viewmodel.BorrowRecordViewModel;
import team.yummy.vCampus.web.WebResponse;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.min;

public class LibraryViewFactory {
    private StackPane rootStackPane;
    private MainViewController mainViewController;
    LibraryViewFactory(StackPane rootStackPane, MainViewController mainViewController) {
        this.rootStackPane = rootStackPane;
        this.mainViewController = mainViewController;
    }

    public List<HBox> createBookRows(List<BookViewModel> bookList, int countPerRow) {
        List<HBox> rows = new ArrayList<>();
        List<VBox> items = new ArrayList<>();

        for(final BookViewModel book:bookList){
            VBox bookCardWrapper = new VBox();

            Image bookImage = new Image("./images/Library.jpg", 240, 160, false, true, true);
            ImageView bookImageContent = new ImageView(bookImage);
            Rectangle clip = new Rectangle(
                    bookImage.getRequestedWidth(), bookImage.getRequestedHeight()
            );
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            bookImageContent.setClip(clip);

            HBox bookInfoContent = new HBox();
            Label bookIDInfo = new Label( book.getBookId());
            Label bookNameInfo = new Label(book.getBookName());



            bookInfoContent.setSpacing(10);
            bookInfoContent.setAlignment(Pos.CENTER);
            bookInfoContent.getChildren().addAll(bookIDInfo,bookNameInfo);

            bookCardWrapper.getChildren().addAll(bookImageContent, bookInfoContent);
            bookCardWrapper.setPadding(new Insets(40, 30, 5, 40));

            /**
             * Layout for book detail model
             */
            final JFXDialog dialog = new JFXDialog();

            HBox cartContent = new HBox();
            // 右边部分
            VBox cartContentMain = new VBox();
            HBox buttonGroup = new HBox();

            // 左边部分
            Image cartbookImage = new Image("./images/Library.jpg", 400, 320, true, true, true);
            ImageView cartImage = new ImageView(cartbookImage);

            Label cartbookID = new Label("ID:" +book.getBookId());
            Label cartbookName = new Label(book.getBookName());
            Label cartbookWriter=new Label(book.getWriter()+"著");
            Label cartPublisher=new Label(book.getPublisher());

            String availnum=Integer.toString(book.getAvailableCount());
            String totalnum=Integer.toString(book.getTotalCount());
            Label Num=new Label("图书总量："+totalnum+ "   "+"剩余数量："+availnum);
            //Label totalNum=new Label("图书总量："+totalnum);

            cartbookID.setFont(Font.font(20));
            cartbookID.setTextFill(Color.web("#212121"));
            cartbookID.setPadding(new Insets(0,0,20,0));

            cartbookName.setFont(Font.font(28));
            cartbookName.setTextFill(Color.web("#212121"));
            cartbookName.setPadding(new Insets(0,0,20,0));

            buttonGroup.setAlignment(Pos.CENTER_RIGHT);
            buttonGroup.setSpacing(20);


            final JFXButton buttonAddToList = new JFXButton("借阅");
            buttonAddToList.setButtonType(JFXButton.ButtonType.RAISED);
            buttonAddToList.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
            buttonAddToList.setTextFill(Color.web("#FFF"));
            buttonAddToList.setFont(Font.font(22));

            buttonGroup.getChildren().addAll(buttonAddToList);



            cartContentMain.getChildren().addAll( cartbookID,cartbookName,cartbookWriter,cartPublisher,Num,buttonGroup);
            cartContentMain.setPrefWidth(300);
            cartContentMain.setAlignment(Pos.CENTER_LEFT);
            cartContentMain.setPadding(new Insets(40, 30, 5, 40));
            cartContentMain.setSpacing(10);

            cartContent.getChildren().addAll(cartImage, cartContentMain);

            dialog.setContent(cartContent);
            bookCardWrapper.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dialog.show(rootStackPane);
                }
            });

            bookCardWrapper.setSpacing(20);
            items.add(bookCardWrapper);

            buttonAddToList.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    WebResponse res = mainViewController.api.post("/library/borrow", book.getBookId());

                    dialog.setVisible(false);

                    WebResponse resed = mainViewController.api.get("/library/borrow");
                    List<BorrowRecordViewModel> borrowedbookList = resed.dataList(BorrowRecordViewModel.class);
                    List<HBox> borrowedRows=createBorrowedbookRows(borrowedbookList);
                    mainViewController.library_borrowedBox.getChildren().clear();
                    mainViewController.library_borrowedBox.getChildren().addAll(borrowedRows);

                    WebResponse res2 = mainViewController.api.get("/library/book");
                    List<BookViewModel> bookList2 = res2.dataList(BookViewModel.class);
                    List<HBox> row3 = createBookRows(bookList2, 3);

                    mainViewController.library_inquireBox.getChildren().clear();
                    mainViewController.library_inquireBox.getChildren().addAll(row3);
                }

            });
        }

        int size = items.size();
        for(int i = 0; i < size / countPerRow; i++) {
            // TODO: 有些可以无视的bug
            List<VBox> rowItems = items.subList(i * countPerRow, i * countPerRow + countPerRow);
            HBox newRow = new HBox();
            newRow.getChildren().addAll(rowItems);
            newRow.getStyleClass().add("row");
            rows.add(newRow);
        }

        return rows;
    }

    public List<HBox> createBorrowedbookRows(List<BorrowRecordViewModel> borrowedbookList){
        List<HBox> rows = new ArrayList<>();
        for(BorrowRecordViewModel borrowedbook : borrowedbookList) {
            HBox newRow=new HBox();
            VBox InfoCard=new VBox();
            Label borrowedbookID = new Label("ID:" +borrowedbook.getBookId());
            Label borrowedbookName = new Label("《 "+borrowedbook.getBookName()+" 》");
            Label borrowedbookWriter=new Label(borrowedbook.getWriter()+"著");
            Label borrowedbookPublisher=new Label(borrowedbook.getPublisher());

            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            String borrowdate=formatter.format(borrowedbook.getBorrowDate());
            SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
            String returndate=formatter2.format(borrowedbook.getExpiryDate());
            Label borrowedbookDate=new Label("借阅时间："+borrowdate);
            Label returnDate=new Label("到期时间："+returndate);
            JFXButton buttonreturn = new JFXButton("归还");
            Image borrowedbookImage = new Image("./images/Library.jpg", 240, 160, false, true, true);
            ImageView borrowedbookImageContent = new ImageView(borrowedbookImage);
            InfoCard.getChildren().addAll(borrowedbookID,borrowedbookName,borrowedbookWriter,borrowedbookPublisher,borrowedbookDate,returnDate);


            InfoCard.setSpacing(10);
            InfoCard.setMinWidth(240);

            HBox BUTTONBOX=new HBox();
            buttonreturn.setButtonType(JFXButton.ButtonType.RAISED);
            buttonreturn.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
            buttonreturn.setTextFill(Color.web("#FFF"));
            buttonreturn.setFont(Font.font(18));
            buttonreturn.setAlignment(Pos.BOTTOM_RIGHT);

            final BorrowRecordViewModel b = borrowedbook;
            buttonreturn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    WebResponse res = mainViewController.api.delete("/library/borrow/"+ b.getId());

                    mainViewController.library_borrowedBox.getChildren().clear();
                    WebResponse resed = mainViewController.api.get("/library/borrow");
                    List<BorrowRecordViewModel> borrowedbookList = resed.dataList(BorrowRecordViewModel.class);
                    List<HBox> borrowedRows=createBorrowedbookRows(borrowedbookList);
                    mainViewController.library_borrowedBox.getChildren().addAll(borrowedRows);

                    mainViewController.library_inquireBox.getChildren().clear();
                    WebResponse resedd = mainViewController.api.get("library/book");
                    List<BookViewModel> bookList = resedd.dataList(BookViewModel.class);
                    List<HBox> Rows=createBookRows(bookList,3);
                    mainViewController.library_inquireBox.getChildren().addAll(Rows);

                }
            });

            BUTTONBOX.getChildren().add(buttonreturn);
            BUTTONBOX.setAlignment(Pos.BOTTOM_RIGHT);

            newRow.getChildren().addAll(borrowedbookImageContent,InfoCard,BUTTONBOX);

            newRow.setAlignment(Pos.CENTER_LEFT);
            newRow.setPrefWidth(300);
            newRow.setPadding(new Insets(40, 30, 5, 40));
            newRow.setSpacing(20);

            rows.add(newRow);

            Rectangle clip = new Rectangle(
                    borrowedbookImage.getRequestedWidth(), borrowedbookImage.getRequestedHeight()
            );
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            borrowedbookImageContent.setClip(clip);
        }

        return rows;
    }


}

