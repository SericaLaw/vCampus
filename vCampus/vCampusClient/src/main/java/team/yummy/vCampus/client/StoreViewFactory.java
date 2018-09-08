package team.yummy.vCampus.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import team.yummy.vCampus.models.Goods;

import java.util.*;

import static java.lang.Math.min;
import static javafx.geometry.Pos.CENTER;
import static sun.swing.MenuItemLayoutHelper.max;


public class StoreViewFactory {
    private StackPane rootStackPane;
    private MainViewController mainViewController;
    private List<Goods> goodsSelected = new ArrayList<>();

    StoreViewFactory(StackPane rootStackPane, MainViewController mainViewController) {
        this.rootStackPane = rootStackPane;
        this.mainViewController = mainViewController;
    }

    // TODO: 添加样式
    public List<HBox> createStoreRows(List<Goods> goodsList, int countPerRow) {
        List<HBox> rows = new ArrayList<>();
        List<VBox> items = new ArrayList<>();

        for (final Goods goods : goodsList) {
            VBox goodsCardWrapper = new VBox();

            Image goodsImage = new Image(goods.getImgUrl(), 240, 160, false, true, true);
            ImageView goodsImageContent = new ImageView(goodsImage);
            goodsImageContent.getStyleClass().add("img");
            Rectangle clip = new Rectangle(
                    goodsImage.getRequestedWidth(), goodsImage.getRequestedHeight()
            );
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            goodsImageContent.setClip(clip);

            HBox goodsInfoContent = new HBox();
            Label goodsNameInfo = new Label(goods.getName());
            Label goodsPriceInfo = new Label("￥" + goods.getPrice());

            goodsInfoContent.setSpacing(10);
            goodsInfoContent.setAlignment(CENTER);
            goodsInfoContent.getChildren().addAll(goodsNameInfo, goodsPriceInfo);

            goodsCardWrapper.getChildren().addAll(goodsImageContent, goodsInfoContent);

            /**
             * Layout for goods detail model
             */
            final JFXDialog dialog = new JFXDialog();

            HBox cartContent = new HBox();
            // 右边部分
            VBox cartContentMain = new VBox();
            HBox buttonGroup = new HBox();

            // 左边部分
            Image cartGoodsImage = new Image(goods.getImgUrl(), 400, 320, true, true, true);
            ImageView cartImage = new ImageView(cartGoodsImage);

            Label cartGoodsInfo = new Label(goods.getInfo());
            Label cartGoodsPrice = new Label("￥" + goods.getPrice());
            Label cartGoodsName = new Label(goods.getName());
            cartGoodsPrice.setFont(Font.font(20));
            cartGoodsName.setFont(Font.font(28));
            cartGoodsName.setTextFill(Color.web("#212121"));
            cartGoodsInfo.setFont(Font.font(20));
            cartGoodsInfo.setTextFill(Color.web("#757575"));
            cartGoodsPrice.setTextFill(Color.web("#7C4DFF"));
            cartGoodsName.setPadding(new Insets(0, 0, 20, 0));

            buttonGroup.setAlignment(Pos.CENTER_RIGHT);
            buttonGroup.setSpacing(20);


            JFXButton buttonAddToCart = new JFXButton("加入购物车");
            buttonAddToCart.setButtonType(JFXButton.ButtonType.RAISED);
            buttonAddToCart.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"), null, null)));
            buttonAddToCart.setTextFill(Color.web("#FFF"));
            buttonAddToCart.setFont(Font.font(22));

            buttonGroup.getChildren().addAll(cartGoodsPrice, buttonAddToCart);

            buttonAddToCart.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // TODO: 这里处理和购物车有关的逻辑
                    mainViewController.goodsToBuy.add(goods);
                }
            });

            cartContentMain.getChildren().addAll(cartGoodsName, cartGoodsInfo, buttonGroup);
            cartContentMain.setPrefWidth(300);
            cartContentMain.setAlignment(Pos.CENTER_LEFT);
            cartContentMain.setPadding(new Insets(40, 30, 5, 40));
            cartContentMain.setSpacing(10);

            cartContent.getChildren().addAll(cartImage, cartContentMain);

            dialog.setContent(cartContent);
            goodsCardWrapper.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dialog.show(rootStackPane);
                }
            });


            items.add(goodsCardWrapper);
        }

        int size = items.size();
        for (int i = 0; i < size / countPerRow; i++) {
            // TODO: 有些可以无视的bug
            List<VBox> rowItems = items.subList(i * countPerRow, i * countPerRow + countPerRow);
            HBox newRow = new HBox();
            newRow.getChildren().addAll(rowItems);
            newRow.getStyleClass().add("row");
            rows.add(newRow);
        }
        return rows;
    }

    // TODO: 应使用购物车商品类；相关事件处理
    public List<HBox> createCartRows(List<Goods> goodsToBuy) {
        List<HBox> rows = new ArrayList<>();
        for (final Goods goods : goodsToBuy) {

            final HBox newRow = new HBox();
            newRow.setSpacing(50);
            newRow.setPadding(new Insets(20, 50, 20, 50));

            final CheckBox goodsSelector =new CheckBox();
            newRow.getChildren().add(goodsSelector);

            Image goodsImage = new Image(goods.getImgUrl());
            ImageView goodsImageView = new ImageView(goodsImage);
            goodsImageView.setFitWidth(150);
            goodsImageView.setFitHeight(100);
            newRow.getChildren().add(goodsImageView);

            Label goodsName = new Label(goods.getName());
            Label goodsInfo=new Label(goods.getInfo());
            Label goodsPrice = new Label("￥" + goods.getPrice());
            goodsPrice.setStyle("-fx-text-fill: #7547E8;");
            VBox newCol_1 = new VBox();
            newCol_1.setSpacing(10);
            newCol_1.setAlignment(CENTER);
            newCol_1.getChildren().add(goodsName);
            newCol_1.getChildren().add(goodsInfo);
            newCol_1.getChildren().add(goodsPrice);
            newRow.getChildren().add(newCol_1);

            Button goodsDec = new Button("一");
            Button goodsInc = new Button("+");
            goodsDec.setStyle("-fx-background-color: transparent; -fx-text-size:17px;");
            goodsInc.setStyle("-fx-background-color: transparent; -fx-text-size:25px;");
            final TextField goodsAmount = new TextField("1");
            goodsAmount.setPrefWidth(50);
            HBox newRow_1 = new HBox();
            newRow_1.setSpacing(10);
            newRow_1.getChildren().add(goodsDec);
            newRow_1.getChildren().add(goodsAmount);
            newRow_1.getChildren().add(goodsInc);

            Label goodsTotalPriceTitle = new Label();
            final Label goodsTotalPrice = new Label();
            final double Price = Double.valueOf(goods.getPrice()).doubleValue();
            int Amount = Integer.valueOf(goodsAmount.getText());
            double TotalPrice = Price * Amount;
            goodsTotalPriceTitle.setText("Total：￥  ");
            goodsTotalPrice.setText(Double.toString(TotalPrice));
            goodsTotalPriceTitle.setStyle("-fx-text-fill: #7547E8;");
            goodsTotalPrice.setStyle("-fx-text-fill: #7547E8;");
            HBox newRow_2=new HBox();
            newRow_2.setAlignment(CENTER);
            newRow_2.getChildren().add(goodsTotalPriceTitle);
            newRow_2.getChildren().add(goodsTotalPrice);
            VBox newCol_2 = new VBox();
            newCol_2.setSpacing(30);
            newCol_2.setAlignment(CENTER);
            newCol_2.getChildren().add(newRow_1);
            newCol_2.getChildren().add(newRow_2);
            newRow.getChildren().add(newCol_2);

            Button goodsRemove = new Button("删除");
            goodsRemove.setStyle("-fx-background-color:#7547E8; -fx-text-fill: #fff;");
            VBox newCol_3 = new VBox();
            newCol_3.setAlignment(CENTER);
            newCol_3.getChildren().add(goodsRemove);
            newRow.getChildren().add(goodsRemove);

            rows.add(newRow);

            goodsDec.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    int tempAmount = Integer.valueOf(goodsAmount.getText()) - 1;
                    if(tempAmount<1) goodsAmount.setText("1");
                    else {
                        goodsAmount.setText(Integer.toString(tempAmount));
                        goodsTotalPrice.setText(Double.toString(Price * tempAmount));
                        if(goodsSelector.isSelected())
                        {
                            Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                            Double TPtemp=Double.valueOf(goods.getPrice()).doubleValue();
                            GTPtemp=GTPtemp-TPtemp;
                            mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));
                        }
                    }
                }
            });
            goodsInc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    int tempAmount = Integer.valueOf(goodsAmount.getText()) + 1;
                    goodsAmount.setText(Integer.toString(tempAmount));
                    goodsTotalPrice.setText(Double.toString(Price * tempAmount));
                    if(goodsSelector.isSelected())
                    {
                        Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                        Double TPtemp=Double.valueOf(goods.getPrice()).doubleValue();
                        GTPtemp=GTPtemp+TPtemp;
                        mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));
                    }
                }
            });

            goodsAmount.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                    // TODO Auto-generated method stub
                    if (newValue == false) {
                        int tempAmount = Integer.valueOf(goodsAmount.getText());
                        goodsAmount.setText(Integer.toString(tempAmount));
                        goodsTotalPrice.setText(Double.toString(Price * tempAmount));
                    }
                }
            });

            final Goods goodsToDelete = goods;
            goodsRemove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                    Double TPtemp=Double.valueOf(goodsTotalPrice.getText()).doubleValue();
                    GTPtemp=GTPtemp-TPtemp;
                    mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));

                    mainViewController.goodsToBuy.remove(goodsToDelete);
                    mainViewController.store_CartBox.getChildren().clear();
                    List<HBox> cartRows = createCartRows( mainViewController.goodsToBuy);   // 数据刷新
                    mainViewController.store_CartBox.getChildren().addAll(cartRows);        // 界面刷新
                }
            });

            final Goods goodsToSelect = goods;
            goodsSelector.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    boolean newValue=goodsSelector.isSelected();
                    if (newValue)
                    {
                        goodsSelected.add(goodsToSelect);
                        Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                        Double TPtemp=Double.valueOf(goodsTotalPrice.getText()).doubleValue();
                        GTPtemp=GTPtemp+TPtemp;
                        mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));
                    }
                    else {
                        goodsSelected.remove(goodsToSelect);
                        Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                        Double TPtemp=Double.valueOf(goodsTotalPrice.getText()).doubleValue();
                        GTPtemp=GTPtemp-TPtemp;
                        mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));
                    }
                }
            });
        }

        return rows;
    }
}