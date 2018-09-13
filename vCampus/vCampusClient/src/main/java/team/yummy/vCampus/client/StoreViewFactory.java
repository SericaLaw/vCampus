package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXSnackbar;
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
import team.yummy.vCampus.models.Goods;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static javafx.animation.Interpolator.EASE_BOTH;
import static javafx.geometry.Pos.BASELINE_CENTER;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;
import static sun.swing.MenuItemLayoutHelper.max;

import team.yummy.vCampus.models.viewmodel.CartRecordViewModel;
import team.yummy.vCampus.models.viewmodel.GoodsViewModel;
import team.yummy.vCampus.web.WebResponse;


public class StoreViewFactory {
    private StackPane rootStackPane;
    private MainViewController controller;
    private List<CartRecordViewModel> goodsSelected = new ArrayList<>();

    StoreViewFactory(StackPane rootStackPane, MainViewController mainViewController) {
        this.rootStackPane = rootStackPane;
        this.controller = mainViewController;
    }

    // TODO: 添加样式
    public List<HBox> createStoreRows(List<GoodsViewModel> goodsList, int countPerRow) {
        List<HBox> rows = new ArrayList<>();
        List<StackPane> items = new ArrayList<>();

        int i = 0;
        for (final GoodsViewModel goods : goodsList) {
            final StackPane child = new StackPane();
            double width = 280;
            child.setMinWidth(width);
            double height = 200;
            child.setMinHeight(height);
            JFXDepthManager.setDepth(child, 1);
            items.add(child);

            // create content
            StackPane header = new StackPane();
            header.setStyle(String.format("-fx-background-radius: 5 5 0 0; -fx-background-image: url(%s); -fx-background-position: center center;", goods.getImgUrl()));
            VBox.setVgrow(header, Priority.ALWAYS);
            StackPane body = new StackPane();
            body.setPrefHeight(50);
            HBox goodsInfoContent = new HBox();
            Label goodsNameInfo = new Label(goods.getGoodsName());
            Label goodsPriceInfo = new Label("￥" + goods.getPrice());

            goodsInfoContent.setSpacing(10);
            goodsInfoContent.setAlignment(CENTER);
            goodsInfoContent.getChildren().addAll(goodsNameInfo, goodsPriceInfo);
            body.getChildren().add(goodsInfoContent);

            VBox content = new VBox();
            content.getChildren().addAll(header, body);
            body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255);");

            // create button
            JFXButton button = new JFXButton("");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
            button.setPrefSize(40, 40);

            button.setScaleX(0);
            button.setScaleY(0);
            SVGGlyph glyph = new SVGGlyph(1,
                    "test",
                    "M804.571 530.286v-109.714q0-22.857-16-38.857t-38.857-16h-237.714v-237.714q0-22.857-16-38.857t-38.857-16h-109.714q-22.857 0-38.857 16t-16 38.857v237.714h-237.714q-22.857 0-38.857 16t-16 38.857v109.714q0 22.857 16 38.857t38.857 16h237.714v237.714q0 22.857 16 38.857t38.857 16h109.714q22.857 0 38.857-16t16-38.857v-237.714h237.714q22.857 0 38.857-16t16-38.857z",
                    Color.WHITE);
            glyph.setSize(20, 20);
            button.setGraphic(glyph);
            button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
                return header.getBoundsInParent().getHeight() - button.getHeight() / 2;
            }, header.boundsInParentProperty(), button.heightProperty()));
            StackPane.setMargin(button, new Insets(0, 12, 0, 0));
            StackPane.setAlignment(button, Pos.TOP_RIGHT);

            Timeline animation = new Timeline(new KeyFrame(Duration.millis(240),
                    new KeyValue(button.scaleXProperty(),
                            1,
                            EASE_BOTH),
                    new KeyValue(button.scaleYProperty(),
                            1,
                            EASE_BOTH)));
            animation.setDelay(Duration.millis(300 + 300 * i));
            animation.play();
            i++;
            child.getChildren().addAll(content, button);
            child.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            JFXDepthManager.setDepth(child, 3);
                        }
                    }
            );

            child.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            JFXDepthManager.setDepth(child, 1);
                        }
                    }
            );

            /**
             //             * Layout for goods detail model
             //             */
            final JFXDialog dialog = new JFXDialog();
            dialog.setMinWidth(800);
            dialog.setMinHeight(600);

            // create content
            StackPane left = new StackPane();
            left.setMinWidth(400);
            left.setMinHeight(300);
            left.setStyle(String.format("-fx-background-radius: 5 5 5 5; -fx-background-image: url(%s); -fx-background-position: center center;-fx-background-size: stretch;-fx-background-repeat: stretch;", goods.getImgUrl()));
            VBox.setVgrow(header, Priority.ALWAYS);
            StackPane right = new StackPane();
            right.setMinWidth(300);
            right.setMinHeight(300);

            HBox cartContent = new HBox();
            cartContent.getChildren().addAll(left, right);
            right.setStyle("-fx-background-radius: 5 5 5 5; -fx-background-color: rgb(255,255,255,0.87);");

            // 右边部分
            VBox cartContentMain = new VBox();
            HBox buttonGroup = new HBox();

            Label cartGoodsInfo = new Label(goods.getInfo());
            Label cartGoodsPrice = new Label("￥" + goods.getPrice());
            Label cartGoodsName = new Label(goods.getGoodsName());
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
                    WebResponse res = controller.api.post("/store/cart", goods.getGoodsId());
                    controller.refreshCartView();
                    dialog.setVisible(false);
                }
            });

            cartContentMain.getChildren().addAll(cartGoodsName, cartGoodsInfo, buttonGroup);
            cartContentMain.setAlignment(Pos.CENTER_LEFT);
            cartContentMain.setPadding(new Insets(40, 30, 5, 40));
            cartContentMain.setSpacing(10);
            right.getChildren().addAll(cartContentMain);

            dialog.setContent(cartContent);
            button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dialog.show(rootStackPane);
                }
            });
        }

        int size = items.size();
        for (i = 0; i < size / countPerRow; i++) {
            List<StackPane> rowItems = items.subList(i * countPerRow, i * countPerRow + countPerRow);
            HBox newRow = new HBox();
            newRow.getChildren().addAll(rowItems);
            newRow.getStyleClass().add("row");
            rows.add(newRow);
        }
        if(i * countPerRow < items.size()) {
            List<StackPane> rowItems = items.subList(i * countPerRow, items.size());
            HBox newRow = new HBox();
            newRow.getChildren().addAll(rowItems);
            newRow.getStyleClass().add("row");
            rows.add(newRow);
        }
        return rows;
    }


    public List<HBox> createCartRows(List<CartRecordViewModel> goodsToBuy) {
        // 排序
        Collections.sort(goodsToBuy, new Comparator<CartRecordViewModel>() {
            @Override
            public int compare(CartRecordViewModel o1, CartRecordViewModel o2) {
                if(o1.getCartRecordID().compareTo(o2.getCartRecordID()) > 0)
                    return 1;
                else
                    return -1;
            }
        });
        List<HBox> rows = new ArrayList<>();
        Double totalPrice = 0.;
        goodsSelected.clear();
        for(CartRecordViewModel goods: goodsToBuy) {
            totalPrice += goods.getPrice() * goods.getGoodsCount() * (goods.getIsSelected() ? 1 : 0);
            if(goods.getIsSelected())
                goodsSelected.add(goods);
        }
        controller.content__Store__Cart__GrandTotalPrice.setText(String.valueOf(totalPrice));
        for (final CartRecordViewModel goods : goodsToBuy) {

            final JFXCheckBox goodsSelector =new JFXCheckBox();
            goodsSelector.setSelected(goods.getIsSelected());

            final HBox newRow = new HBox();
            JFXDepthManager.setDepth(newRow, 1);


            newRow.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    newRow.setStyle("-fx-background-color: #fff; -fx-background-radius: 10;  -fx-spacing: 30; -fx-padding: 20 50 20 50;-fx-cursor: hand;");
                    JFXDepthManager.setDepth(newRow, 3);
                }
            });
            // TODO

            newRow.addEventFilter(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {
                String patch = String.format("{\"IsSel\":%s}", String.valueOf(!goods.getIsSelected()));
                controller.api.patch("/cartRecord/cartRecordID/" + goods.getCartRecordID(), patch);
                controller.refreshCartView();
            });
            newRow.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    JFXDepthManager.setDepth(newRow, 1);
                }
            });
            newRow.setStyle("-fx-background-color: #fff; -fx-background-radius: 10; -fx-spacing: 30; -fx-padding: 20 50 20 50;");

            VBox newCol_0 = new VBox();
            newCol_0.setAlignment(CENTER);
            newCol_0.getChildren().add(goodsSelector);
            newRow.getChildren().add(newCol_0);
            newCol_0.setMaxWidth(30);
            newCol_0.setPrefWidth(30);
            newCol_0.setMinWidth(30);

            Image goodsImage = new Image(goods.getImgUrl());
            ImageView goodsImageView = new ImageView(goodsImage);
            goodsImageView.setFitWidth(150);
            goodsImageView.setFitHeight(100);
            newRow.getChildren().add(goodsImageView);

            Label goodsName = new Label(goods.getGoodsName());
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
            newCol_1.setMaxWidth(250);
            newCol_1.setPrefWidth(250);
            newCol_1.setMinWidth(250);
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
            final TextField goodsAmount = new TextField(String.valueOf(goods.getGoodsCount()));
            goodsAmount.setMinWidth(50);
            goodsAmount.setPrefWidth(50);
            goodsAmount.setMaxWidth(50);
            HBox newRow_1 = new HBox();
            newRow_1.setSpacing(10);
            newRow_1.setAlignment(BASELINE_CENTER);
            newRow_1.getChildren().add(goodsDec);
            newRow_1.getChildren().add(goodsAmount);
            newRow_1.getChildren().add(goodsInc);
//            newRow_1.setMaxWidth(100);

//            Label goodsTotalPriceTitle = new Label();
//            final Label goodsTotalPrice = new Label();
//            final double Price = Double.valueOf(goods.getPrice()).doubleValue();
//            int Amount = Integer.valueOf(goodsAmount.getText());
//            double TotalPrice = Price * Amount;
//            goodsTotalPriceTitle.setText("￥  ");
//            goodsTotalPrice.setText(Double.toString(TotalPrice));
//            goodsTotalPriceTitle.setStyle("-fx-text-fill: #7547E8;");
//            goodsTotalPrice.setStyle("-fx-text-fill: #7547E8;");
//            goodsTotalPrice.setFont(Font.font(18));
//            goodsTotalPriceTitle.setFont(Font.font(18));
//            goodsTotalPriceTitle.setMinWidth(100);

//            HBox newRow_2=new HBox();
//            newRow_2.setAlignment(CENTER);
//            newRow_2.getChildren().add(goodsTotalPriceTitle);
//            newRow_2.getChildren().add(goodsTotalPrice);
//            newRow_2.setMinWidth(100);
            VBox newCol_2 = new VBox();
            newCol_2.setSpacing(30);
            newCol_2.setAlignment(CENTER);
            newCol_2.getChildren().add(newRow_1);
            newCol_2.setPrefWidth(120);
//            newCol_2.getChildren().add(newRow_2);
            newRow.getChildren().add(newCol_2);

            Button goodsRemove = new Button("删除");
            goodsRemove.setStyle("-fx-background-color:#7547E8; -fx-text-fill: #fff;-fx-font-size:16.5px");
            goodsRemove.setPrefSize(75,20);
            VBox newCol_3 = new VBox();
            newCol_3.setAlignment(CENTER);
            newCol_3.getChildren().add(goodsRemove);
            newRow.getChildren().add(newCol_3);
            newCol_3.setMinWidth(80);
            //newRow.setBackground(new Background(new BackgroundFill(Color.web("#707070"),null,null)));

            rows.add(newRow);

            goodsDec.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {

                    if(Integer.valueOf(goodsAmount.getText()) > 1) {
                        String patch = String.format("{\"GoodsCnt\":\"%s\"}", String.valueOf(goods.getGoodsCount() - 1));
                        WebResponse res = controller.api.patch("/cartRecord/cartRecordId/" + goods.getCartRecordID(), patch);
                        controller.refreshCartView();
                    }
                }
            });
            goodsInc.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    String patch = String.format("{\"GoodsCnt\":\"%s\"}", String.valueOf(goods.getGoodsCount()+1));
                    WebResponse res = controller.api.patch("/cartRecord/cartRecordId/" + goods.getCartRecordID(), patch);
                    controller.refreshCartView();
                }
            });

            goodsAmount.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                    // TODO Auto-generated method stub
                    if (newValue == false) {
                        int tempAmount = Integer.valueOf(goodsAmount.getText());
                        if(tempAmount >= 1) {
                            String patch = String.format("{\"GoodsCnt\":%d}", tempAmount);
                            WebResponse res = controller.api.patch("/cartRecord/cartRecordID/" + goods.getCartRecordID(), patch);
                            controller.refreshCartView();
//                            goodsAmount.setText(Integer.toString(tempAmount));
                        }

                    }
                }
            });

            final CartRecordViewModel goodsToRemove = goods;
            goodsRemove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    WebResponse res = controller.api.delete("/cartRecord/cartRecordID/" + goods.getCartRecordID());
                    controller.refreshCartView();

                }
            });

            //final Goods goodsToSelect = goods;
            goodsSelector.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    String patch = String.format("{\"IsSel\":%s}", String.valueOf(!goods.getIsSelected()));
                    controller.api.patch("/cartRecord/cartRecordID/" + goods.getCartRecordID(), patch);
                    controller.refreshCartView();
                }
            });

            controller.content__Store__Cart__BatchRemove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    List<String> goodsToDelete = goodsSelected.stream()
                            .map(r -> r.getCartRecordID())
                            .collect(Collectors.toList());
                    WebResponse res = controller.api.post("/store/clear", JSON.toJSONString(goodsToDelete));
                    controller.refreshCartView();
                }
            });

            controller.content__Store__Cart__Pay.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {

                    if(Double.valueOf(controller.content__Store__Cart__GrandTotalPrice.getText()).doubleValue()!=0)
                    {
                        controller.content__Store__Cart__GrandTotalPrice.setDisable(false);
                        final JFXDialog dialog = new JFXDialog();
                        HBox hb1 = new HBox();
                        HBox hb2 = new HBox();
                        VBox vb = new VBox();
                        Label text =new Label("确定要支付 ￥ "+controller.content__Store__Cart__GrandTotalPrice.getText()+" 吗？");
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
                                List<String> goodsToPurchase = goodsSelected.stream()
                                        .map(r -> r.getCartRecordID())
                                        .collect(Collectors.toList());
                                WebResponse res = controller.api.post("/store/purchase", JSON.toJSONString(goodsToPurchase));
                                dialog.setVisible(false);
                                controller.refreshCartView();

                                JFXSnackbar bar = new JFXSnackbar(controller.cartBottomView);
                                bar.setStyle("-fx-font-size: 18px;");

                                if(res.getStatusCode().equals("200")) {
                                    bar.enqueue(new JFXSnackbar.SnackbarEvent("支付成功"));
                                }
                                else {
                                    bar.enqueue(new JFXSnackbar.SnackbarEvent("支付失败"));
                                }


                            }
                        });
                        cancel.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dialog.setVisible(false);
                            }
                        });
                    }
                }
            });
        }

        return rows;
    }

    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0:
                color = "#8F3F7E";
                break;
            case 1:
                color = "#B5305F";
                break;
            case 2:
                color = "#CE584A";
                break;
            case 3:
                color = "#DB8D5C";
                break;
            case 4:
                color = "#DA854E";
                break;
            case 5:
                color = "#E9AB44";
                break;
            case 6:
                color = "#FEE435";
                break;
            case 7:
                color = "#99C286";
                break;
            case 8:
                color = "#01A05E";
                break;
            case 9:
                color = "#4A8895";
                break;
            case 10:
                color = "#16669B";
                break;
            case 11:
                color = "#2F65A5";
                break;
            case 12:
                color = "#4E6A9C";
                break;
            default:
                break;
        }
        return color;
    }
}