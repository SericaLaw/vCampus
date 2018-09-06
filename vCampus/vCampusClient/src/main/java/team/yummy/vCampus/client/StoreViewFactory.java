package team.yummy.vCampus.client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import team.yummy.vCampus.models.Goods;

import java.util.*;

import static java.lang.Math.min;
import static sun.swing.MenuItemLayoutHelper.max;

public class StoreViewFactory {
    private StackPane rootStackPane;
    private MainViewController mainViewController;
    StoreViewFactory(StackPane rootStackPane, MainViewController mainViewController) {
        this.rootStackPane = rootStackPane;
        this.mainViewController = mainViewController;
    }
    // TODO: 添加样式
    public List<HBox> createStoreRows(List<Goods> goodsList, int countPerRow) {
        List<HBox> rows = new ArrayList<>();
        List<VBox> items = new ArrayList<>();

        for(final Goods goods : goodsList) {
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
            goodsInfoContent.setAlignment(Pos.CENTER);
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
            Label cartGoodsPrice = new Label("￥"+goods.getPrice());
            Label cartGoodsName = new Label(goods.getName());
            cartGoodsPrice.setFont(Font.font(20));
            cartGoodsName.setFont(Font.font(28));
            cartGoodsName.setTextFill(Color.web("#212121"));
            cartGoodsInfo.setFont(Font.font(20));
            cartGoodsInfo.setTextFill(Color.web("#757575"));
            cartGoodsPrice.setTextFill(Color.web("#7C4DFF"));
            cartGoodsName.setPadding(new Insets(0,0,20,0));

            buttonGroup.setAlignment(Pos.CENTER_RIGHT);
            buttonGroup.setSpacing(20);


            JFXButton buttonAddToCart = new JFXButton("加入购物车");
            buttonAddToCart.setButtonType(JFXButton.ButtonType.RAISED);
            buttonAddToCart.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
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

            cartContentMain.getChildren().addAll( cartGoodsName,  cartGoodsInfo, buttonGroup);
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

    // TODO: 应使用购物车商品类；相关事件处理
    public List<HBox> createCartRows(List<Goods> goodsToBuy) {
        List<HBox> rows = new ArrayList<>();
        for(Goods goods : goodsToBuy) {

            HBox newRow = new HBox();
            Label goodsName = new Label(goods.getName());
            Label goodsPrice = new Label(goods.getPrice());
            Image goodsImage = new Image(goods.getImgUrl());
            ImageView goodsImageView = new ImageView(goodsImage);

            newRow.getChildren().addAll(goodsImageView, goodsName, goodsPrice);

            rows.add(newRow);
        }
        return rows;
    }
}
