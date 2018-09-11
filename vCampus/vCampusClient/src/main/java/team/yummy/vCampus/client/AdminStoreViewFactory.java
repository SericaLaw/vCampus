package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
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

import static java.lang.Math.min;
import static javafx.animation.Interpolator.EASE_BOTH;
import static javafx.geometry.Pos.BASELINE_CENTER;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;
import static sun.swing.MenuItemLayoutHelper.max;


public class AdminStoreViewFactory {
    private StackPane rootStackPane;
    private AdminViewController controller;

    AdminStoreViewFactory(StackPane rootStackPane, AdminViewController adminViewController) {
        this.rootStackPane = rootStackPane;
        this.controller = adminViewController;
    }

    // TODO: 添加样式
    public List<HBox> createFullGoodsRows(List<Goods> goodsList) {
        List<HBox> rows = new ArrayList<>();

        for (final Goods goods : goodsList) {

            HBox newRow = new HBox();
            VBox InfoCard1=new VBox();
            VBox InfoCard2=new VBox();
            HBox newRow_1=new HBox();
            HBox newRow_2=new HBox();
            HBox newRow_3=new HBox();
            HBox newRow_4=new HBox();

            Image goodsImage = new Image("./images/item.png", 240, 160, false, true, true);
            ImageView goodsImageContent = new ImageView(goodsImage);
            Rectangle clip = new Rectangle(
                    goodsImage.getRequestedWidth(), goodsImage.getRequestedHeight()
            );
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            goodsImageContent.setClip(clip);
            Label title_ID=new Label("ID: ");
            title_ID.setFont(Font.font(17));
            JFXTextField goodsID=new JFXTextField(goods.getGoodsID());
            goodsID.setFont(Font.font(17));
            newRow_1.getChildren().addAll(title_ID,goodsID);
            Label title_Name=new Label("品名： ");
            title_Name.setFont(Font.font(20));
            JFXTextField goodsName = new JFXTextField(goods.getName());
            goodsName.setFont(Font.font(20));
            newRow_2.getChildren().addAll(title_Name,goodsName);
            Label title_Info=new Label("型号： ");
            title_Info.setFont(Font.font(17));
            JFXTextField goodsInfo=new JFXTextField(goods.getInfo());
            goodsInfo.setFont(Font.font(17));
            newRow_3.getChildren().addAll(title_Info,goodsInfo);
            InfoCard1.getChildren().addAll(newRow_1,newRow_2,newRow_3);
            Label title_Price=new Label("价格：￥ ");
            title_Price.setFont(Font.font(21));
            JFXTextField goodsPrice = new JFXTextField(goods.getPrice());
            goodsPrice.setFont(Font.font(21));
            goodsPrice.setStyle("-fx-text-fill:#673AB7");
            newRow_4.getChildren().addAll(title_Price,goodsPrice);
            Label errorText=new Label("");
            errorText.setFont(Font.font(16));
            errorText.setStyle("-fx-text-fill:#673AB7");     //换成那个红色
            InfoCard2.getChildren().addAll(newRow_4,errorText);

            JFXButton editgoods = new JFXButton("编辑");
            editgoods.setButtonType(JFXButton.ButtonType.RAISED);
            editgoods.setButtonType(JFXButton.ButtonType.RAISED);
            editgoods.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
            editgoods.setTextFill(Color.web("#FFF"));
            editgoods.setFont(Font.font(18));
            editgoods.setAlignment(Pos.BOTTOM_RIGHT);

            newRow.getChildren().addAll(goodsImageContent,InfoCard1,InfoCard2,editgoods);
            newRow.setAlignment(CENTER);
            newRow.setAlignment(Pos.CENTER_LEFT);
            //newRow.setPrefWidth(300);
            newRow.setPadding(new Insets(40, 30, 5, 40));
            //newRow.setSpacing(20);

            goodsID.setDisable(true);
            goodsName.setDisable(true);
            goodsInfo.setDisable(true);
            goodsPrice.setDisable(true);


            final Goods b = goods;
            final JFXTextField goods_ID=goodsID;
            final JFXTextField goods_Name=goodsName;
            final JFXTextField goods_Info=goodsInfo;
            final JFXTextField goods_Price=goodsPrice;
            final Label error_Text=errorText;
            editgoods.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(editgoods.getText().equals("编辑")) {
                        editgoods.setText("保存");
                        goods_ID.setDisable(false);
                        goods_Name.setDisable(false);
                        goods_Info.setDisable(false);
                        goods_Price.setDisable(false);
                    }
                    else {
                        String ID=goods_ID.getText();
                        String Name=goods_Name.getText();
                        String Info=goods_Info.getText();
                        String Price=goods_Price.getText();

                        if (ID.length() == 0 || Name.length() == 0 || Info.length() == 0 || Price.length() == 0)
                            error_Text.setText("有空项!");

                        else {
                            JSONObject infoToModify = new JSONObject();
                            infoToModify.put("goodsID", ID);
                            infoToModify.put("goodsName", Name);
                            infoToModify.put("Info", Info);
                            infoToModify.put("Price", Price);
                            controller.api.patch("/goods" + controller.currentAccount.getCampusCardId(), infoToModify.toJSONString());

                            error_Text.setText("");
                            goods_ID.setDisable(true);
                            goods_Name.setDisable(true);
                            goods_Info.setDisable(true);
                            goods_Price.setDisable(true);
                            editgoods.setText("编辑");
                        }
                    }
                }
            });

            rows.add(newRow);
        }
        return rows;
    }

    public List<HBox> createEmptyGoodsRows() {
        List<HBox> rows = new ArrayList<>();
        HBox newRow = new HBox();
        VBox InfoCard1=new VBox();
        VBox InfoCard2=new VBox();
        HBox newRow_1=new HBox();
        HBox newRow_2=new HBox();
        HBox newRow_3=new HBox();
        HBox newRow_4=new HBox();

        Image goodsImage = new Image("./images/item.png", 240, 160, false, true, true);
        ImageView goodsImageContent = new ImageView(goodsImage);
        Label title_ID=new Label("ID: ");
        JFXTextField goodsID=new JFXTextField();
        newRow_1.getChildren().addAll(title_ID,goodsID);
        Label title_Name=new Label("品名： ");
        JFXTextField goodsName = new JFXTextField();
        newRow_2.getChildren().addAll(title_Name,goodsName);
        Label title_Info=new Label("型号： ");
        JFXTextField goodsInfo=new JFXTextField();
        newRow_3.getChildren().addAll(title_Info,goodsInfo);
        InfoCard1.getChildren().addAll(newRow_1,newRow_2,newRow_3);
        Label title_Price=new Label("价格：￥ ");
        JFXTextField goodsPrice = new JFXTextField();
        newRow_4.getChildren().addAll(title_Price,goodsPrice);
        Label errorText=new Label("");
        InfoCard2.getChildren().addAll(newRow_4,errorText);

        JFXButton editgoods = new JFXButton("保存");
        editgoods.setButtonType(JFXButton.ButtonType.RAISED);
        editgoods.setButtonType(JFXButton.ButtonType.RAISED);
        editgoods.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
        editgoods.setTextFill(Color.web("#FFF"));
        editgoods.setFont(Font.font(18));
        editgoods.setAlignment(Pos.BOTTOM_RIGHT);

        newRow.getChildren().addAll(goodsImageContent,InfoCard1,InfoCard2,editgoods);
        newRow.setAlignment(CENTER);
        newRow.setAlignment(Pos.CENTER_LEFT);
        newRow.setPrefWidth(300);
        newRow.setPadding(new Insets(40, 30, 5, 40));
        newRow.setSpacing(20);

        goodsID.setDisable(false);
        goodsName.setDisable(false);
        goodsInfo.setDisable(false);
        goodsPrice.setDisable(false);


        //final Goods b = goods;
        final JFXTextField goods_ID=goodsID;
        final JFXTextField goods_Name=goodsName;
        final JFXTextField goods_Info=goodsInfo;
        final JFXTextField goods_Price=goodsPrice;
        final Label error_Text=errorText;
        editgoods.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ID=goods_ID.getText();
                String Name=goods_Name.getText();
                String Info=goods_Info.getText();
                String Price=goods_Price.getText();

                if (ID.length() == 0 || Name.length() == 0 || Info.length() == 0 || Price.length() == 0)
                    error_Text.setText("有空项!");

                else {
                    JSONObject infoToModify = new JSONObject();
                    infoToModify.put("goodsID", ID);
                    infoToModify.put("goodsName", Name);
                    infoToModify.put("Info", Info);
                    infoToModify.put("Price", Price);
                    controller.api.patch("/goods" + controller.currentAccount.getCampusCardId(), infoToModify.toJSONString());

                    error_Text.setText("");
                    goods_ID.setDisable(true);
                    goods_Name.setDisable(true);
                    goods_Info.setDisable(true);
                    goods_Price.setDisable(true);
                    editgoods.setText("编辑");
                }
            }
        });

        rows.add(newRow);
        Rectangle clip = new Rectangle(goodsImage.getRequestedWidth(), goodsImage.getRequestedHeight());
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        goodsImageContent.setClip(clip);

        return rows;
    }
}

