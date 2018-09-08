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
import static javafx.geometry.Pos.BASELINE_CENTER;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;
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
                    List<HBox> cartRows = createCartRows(mainViewController.goodsToBuy);
                    mainViewController.store_CartBox.getChildren().addAll(cartRows);
                    dialog.setVisible(false);
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
            newRow.setSpacing(70);
            newRow.setPadding(new Insets(20, 50, 20, 50));

            final CheckBox goodsSelector =new CheckBox();
            VBox newCol_0 = new VBox();
            newCol_0.setAlignment(CENTER);
            newCol_0.getChildren().add(goodsSelector);
            newRow.getChildren().add(newCol_0);

            Image goodsImage = new Image(goods.getImgUrl());
            ImageView goodsImageView = new ImageView(goodsImage);
            goodsImageView.setFitWidth(150);
            goodsImageView.setFitHeight(100);
            newRow.getChildren().add(goodsImageView);

            Label goodsName = new Label(goods.getName());
            goodsName.setStyle("-fx-font-weight:bold;-fx-font-size:17px;");
            Label goodsInfo=new Label(goods.getInfo());
            Label goodsPrice = new Label("￥" + goods.getPrice());
            goodsPrice.setFont(Font.font(16.5));
            goodsPrice.setStyle("-fx-text-fill: #7547E8;");
            VBox newCol_1 = new VBox();
            newCol_1.setSpacing(10);
            newCol_1.setAlignment(CENTER_LEFT);
            newCol_1.getChildren().add(goodsName);
            newCol_1.getChildren().add(goodsInfo);
            newCol_1.getChildren().add(goodsPrice);
            newRow.getChildren().add(newCol_1);

            Image goodsdec=new Image("./images/goodsDec.png",22,22,true,true);
            Image goodsinc=new Image("./images/goodsInc.png",22,22,true,true);
            ImageView image1= new ImageView(goodsdec);
            ImageView image2= new ImageView(goodsinc);
            Button goodsDec = new Button("",image1);
            Button goodsInc = new Button("",image2);
            goodsDec.setPrefSize(10,10);
            goodsInc.setPrefSize(10,10);
            goodsDec.setStyle("-fx-background-color:transparent");
            goodsInc.setStyle("-fx-background-color:transparent");
            final TextField goodsAmount = new TextField("1");
            goodsAmount.setPrefWidth(50);
            HBox newRow_1 = new HBox();
            newRow_1.setSpacing(10);
            newRow_1.setAlignment(BASELINE_CENTER);
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
            goodsTotalPrice.setFont(Font.font(18));
            goodsTotalPriceTitle.setFont(Font.font(18));
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
            goodsRemove.setStyle("-fx-background-color:#7547E8; -fx-text-fill: #fff;-fx-font-size:16.5px");
            goodsRemove.setPrefSize(75,20);
            VBox newCol_3 = new VBox();
            newCol_3.setAlignment(CENTER);
            newCol_3.getChildren().add(goodsRemove);
            newRow.getChildren().add(newCol_3);
            //newRow.setBackground(new Background(new BackgroundFill(Color.web("#707070"),null,null)));

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
                        if(tempAmount>=1)
                        {
                            goodsAmount.setText(Integer.toString(tempAmount));
                            goodsTotalPrice.setText(Double.toString(Price * tempAmount));
                        }
                        else
                        {
                            goodsAmount.setText("1");
                            goodsTotalPrice.setText(goods.getPrice());
                        }
                    }
                }
            });

            final Goods goodsToRemove = goods;
            goodsRemove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {

                    if(goodsSelector.isSelected())
                    {
                        goodsSelected.remove(newRow);
                        Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                        Double TPtemp=Double.valueOf(goodsTotalPrice.getText()).doubleValue();
                        GTPtemp=GTPtemp-TPtemp;
                        mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));
                    }
                    mainViewController.goodsToBuy.remove(goodsToRemove);
                    mainViewController.store_CartBox.getChildren().remove(newRow);
                    //List<HBox> cartRows = createCartRows(mainViewController.goodsToBuy);   // 数据刷新
                    //mainViewController.store_CartBox.getChildren().addAll(cartRows);        // 界面刷新

                }
            });

            //final Goods goodsToSelect = goods;
            goodsSelector.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    boolean newValue=goodsSelector.isSelected();
                    if (newValue) {
                        goodsSelected.add(goods);
                        Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                        Double TPtemp=Double.valueOf(goodsTotalPrice.getText()).doubleValue();
                        GTPtemp=GTPtemp+TPtemp;
                        mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));
                    }
                    else {
                        goodsSelected.remove(goods);
                        Double GTPtemp=Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue();
                        Double TPtemp=Double.valueOf(goodsTotalPrice.getText()).doubleValue();
                        GTPtemp=GTPtemp-TPtemp;
                        mainViewController.content__Store__Cart__GrandTotalPrice.setText(Double.toString(GTPtemp));
                    }
                }
            });

            mainViewController.content__Store__Cart__BatchRemove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {

                    mainViewController.goodsToBuy.removeAll(goodsSelected);
                    mainViewController.store_CartBox.getChildren().clear();
                    List<HBox> cartRows = createCartRows(mainViewController.goodsToBuy);
                    mainViewController.store_CartBox.getChildren().addAll(cartRows);

                    goodsSelected.clear();
                    mainViewController.content__Store__Cart__GrandTotalPrice.setText("0");
                }
            });

            mainViewController.content__Store__Cart__Pay.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {

                    if(Double.valueOf(mainViewController.content__Store__Cart__GrandTotalPrice.getText()).doubleValue()!=0)
                    {
                        mainViewController.content__Store__Cart__GrandTotalPrice.setDisable(false);
                        final JFXDialog dialog = new JFXDialog();
                        HBox hb1 = new HBox();
                        HBox hb2 = new HBox();
                        VBox vb = new VBox();
                        Label text =new Label("确定要支付 ￥ "+mainViewController.content__Store__Cart__GrandTotalPrice.getText()+" 吗？");
                        text.setFont(Font.font(20));
                        text.setTextFill(Color.web("black"));
                        text.setPadding(new Insets(10,0,0,130));
                        text.setPrefSize(400,200);
                        hb1.setPadding(new Insets(0,10,30,280));
                        JFXButton ok=new JFXButton("确定");
                        JFXButton cancel=new JFXButton("取消");
                        ok.setFont(Font.font(17));
                        ok.setPrefSize(80,35);
                        ok.setBackground(new Background(new BackgroundFill(Color.web("#FF4500"),null,null)));
                        ok.setTextFill(Color.web("#fff"));
                        cancel.setFont(Font.font(17));
                        cancel.setPrefSize(80,35);
                        cancel.setBackground(new Background(new BackgroundFill(Color.web("#707070"),null,null)));
                        cancel.setTextFill(Color.web("#fff"));
                        hb1.setSpacing(35);
                        hb1.getChildren().addAll(ok,cancel);
                        vb.setPrefSize(500,250);
                        hb2.getChildren().addAll(text);
                        vb.getChildren().addAll(hb2,hb1);
                        dialog.setContent(vb);
                        dialog.show(rootStackPane);

                        ok.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mainViewController.goodsToBuy.removeAll(goodsSelected);
                                mainViewController.store_CartBox.getChildren().clear();
                                List<HBox> cartRows = createCartRows( mainViewController.goodsToBuy);   // 数据刷新
                                mainViewController.store_CartBox.getChildren().addAll(cartRows);        // 界面刷新
                                goodsSelected.clear();
                                mainViewController.content__Store__Cart__GrandTotalPrice.setText("0");
                                dialog.setVisible(false);
                            }
                        });
                        cancel.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dialog.setVisible(false);            }
                        });
                    }
                }
            });
        }

        return rows;
    }
}