package team.yummy.vCampus.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
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
import team.yummy.vCampus.models.viewmodel.GoodsViewModel;

import team.yummy.vCampus.web.WebResponse;

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
    public List<HBox> createFullGoodsRows(List<GoodsViewModel> goodsList) {
        List<HBox> rows = new ArrayList<>();

        for (final GoodsViewModel goods : goodsList) {

            HBox newRow = new HBox();
            VBox InfoCard1 = new VBox();
            VBox InfoCard2 = new VBox();
            HBox newRow_1 = new HBox();
            HBox newRow_2 = new HBox();
            HBox newRow_3 = new HBox();
            HBox newRow_4 = new HBox();
            HBox newRow_5 = new HBox();

            newRow_1.setSpacing(30);
            newRow_2.setSpacing(30);
            newRow_3.setSpacing(30);
            newRow_4.setSpacing(30);
            newRow_5.setSpacing(30);
            newRow.setSpacing(30);


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
            title_ID.setMinSize(100,35);
            JFXTextField goodsID=new JFXTextField(goods.getGoodsId());
            goodsID.setFont(Font.font(17));
            goodsID.setMinSize(100,18);
            newRow_1.getChildren().addAll(title_ID,goodsID);
            Label title_Name=new Label("品名： ");
            title_Name.setFont(Font.font(17));
            title_Name.setMinSize(100,35);
            JFXTextField goodsName = new JFXTextField(goods.getGoodsName());
            goodsName.setFont(Font.font(17));
            goodsName.setMinSize(100,18);
            newRow_2.getChildren().addAll(title_Name,goodsName);
            Label title_Info=new Label("型号： ");
            title_Info.setFont(Font.font(17));
            title_Info.setMinSize(100,35);
            JFXTextField goodsInfo=new JFXTextField(goods.getInfo());
            goodsInfo.setFont(Font.font(17));
            goodsInfo.setMinSize(100,18);
            newRow_3.getChildren().addAll(title_Info,goodsInfo);
            Label title_Price=new Label("价格：￥ ");
            title_Price.setFont(Font.font(17));
            title_Price.setMinSize(100,35);
            JFXTextField goodsPrice = new JFXTextField(Double.toString(goods.getPrice()));
            goodsPrice.setFont(Font.font(17));
            goodsPrice.setMinSize(100,18);
            goodsPrice.setStyle("-fx-text-fill:#673AB7");
            newRow_4.getChildren().addAll(title_Price,goodsPrice);
            Label title_Tag=new Label("类别： ");
            title_Tag.setFont(Font.font(17));
            JFXComboBox<String> goodsTag=new JFXComboBox<>();
            goodsTag.setItems(FXCollections.observableArrayList(
                    new String("1"),
                    new String("2"),
                    new String("3")));
            goodsTag.setValue(Integer.toString(goods.getTag()));
            newRow_5.getChildren().addAll(title_Tag,goodsTag);
            InfoCard1.getChildren().addAll(newRow_1,newRow_2,newRow_3,newRow_4,newRow_5);
            Label errorText=new Label("");
            errorText.setFont(Font.font(16));
            errorText.setMinSize(100,20);
            errorText.setStyle("-fx-text-fill:#673AB7");     //换成那个红色


            JFXButton editgoods = new JFXButton("编辑");
            editgoods.setButtonType(JFXButton.ButtonType.RAISED);
            editgoods.setButtonType(JFXButton.ButtonType.RAISED);
            editgoods.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
            editgoods.setTextFill(Color.web("#FFF"));
            editgoods.setFont(Font.font(18));
            editgoods.setMinSize(60,10);
            InfoCard2.setSpacing(20);
            InfoCard2.setAlignment(Pos.BOTTOM_RIGHT);
            errorText.setAlignment(Pos.CENTER_RIGHT);
            InfoCard2.getChildren().addAll(errorText,editgoods);
            newRow.getChildren().addAll(goodsImageContent,InfoCard1,InfoCard2);
            newRow.setAlignment(CENTER);
            newRow.setAlignment(Pos.CENTER_LEFT);
            //newRow.setPrefWidth(300);
            newRow.setPadding(new Insets(40, 30, 5, 40));
            //newRow.setSpacing(20);

            goodsID.setDisable(true);
            goodsName.setDisable(true);
            goodsInfo.setDisable(true);
            goodsPrice.setDisable(true);
            goodsTag.setDisable(true);
            rows.add(newRow);


            //final GoodsViewModel b = goods;
            final JFXTextField goods_Name=goodsName;
            final JFXTextField goods_Info=goodsInfo;
            final JFXTextField goods_Price=goodsPrice;
            final JFXComboBox goods_Tag= goodsTag;
            final Label error_Text=errorText;
            editgoods.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(editgoods.getText().equals("编辑")) {
                        editgoods.setText("保存");
                        goods_Name.setDisable(false);
                        goods_Info.setDisable(false);
                        goods_Price.setDisable(false);
                        goods_Tag.setDisable(false);
                    }
                    else {

                        String Name=goods_Name.getText();
                        String Info=goods_Info.getText();
                        String Price=goods_Price.getText();
                        String Tag=goods_Tag.getValue().toString();

                        if (Name.length() == 0 || Info.length() == 0 || Price.length() == 0 || Tag.length() ==0)
                            error_Text.setText("有空项!");

                        else {

                            GoodsViewModel newGoods = new GoodsViewModel();
                            newGoods.setGoodsName(Name);
                            newGoods.setImgUrl("./images/item.png");
                            newGoods.setPrice(Double.valueOf(Price));
                            newGoods.setTag(Integer.valueOf(Tag));

                            WebResponse res = controller.api.post("/goods", JSON.toJSONString(newGoods));

                            error_Text.setText("");
                            goods_Name.setDisable(true);
                            goods_Info.setDisable(true);
                            goods_Price.setDisable(true);
                            goods_Tag.setDisable(true);
                            editgoods.setText("编辑");
                        }
                    }
                }
            });
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
        HBox newRow_5=new HBox();

        newRow_1.setSpacing(30);
        newRow_2.setSpacing(30);
        newRow_3.setSpacing(30);
        newRow_4.setSpacing(30);
        newRow_5.setSpacing(30);
        newRow.setSpacing(30);

        Image goodsImage = new Image("./images/item.png", 240, 160, false, true, true);
        ImageView goodsImageContent = new ImageView(goodsImage);
        Label title_Name=new Label("  品名： ");
        title_Name.setFont(Font.font(17));
        title_Name.setMinSize(110,35);
        JFXTextField goodsName = new JFXTextField();
        goodsName.setFont(Font.font(17));
        goodsName.setMinSize(100,15);
        newRow_2.getChildren().addAll(title_Name,goodsName);
        Label title_Info=new Label("  型号： ");
        title_Info.setFont(Font.font(17));
        title_Info.setMinSize(110,35);
        JFXTextField goodsInfo=new JFXTextField();
        goodsInfo.setFont(Font.font(17));
        goodsInfo.setMinSize(100,15);
        newRow_3.getChildren().addAll(title_Info,goodsInfo);
        Label title_Price=new Label("  价格：￥ ");
        title_Price.setFont(Font.font(17));
        title_Price.setMinSize(110,35);
        JFXTextField goodsPrice = new JFXTextField();
        goodsPrice.setFont(Font.font(17));
        goodsPrice.setMinSize(100,15);
        newRow_4.getChildren().addAll(title_Price,goodsPrice);
        Label title_Tag=new Label("  类别： ");
        title_Tag.setFont(Font.font(17));
        title_Tag.setMinSize(110,35);
        JFXComboBox goodsTag=new JFXComboBox();
        goodsTag.setMinHeight(17);
        goodsTag.getItems().addAll("1","2","3");
        newRow_5.getChildren().addAll(title_Tag,goodsTag);
        Label errorText=new Label("");
        errorText.setFont(Font.font(16));
        errorText.setMinSize(100,20);
        InfoCard1.getChildren().addAll(newRow_1,newRow_2,newRow_3,newRow_4,newRow_5,errorText);


        JFXButton editgoods = new JFXButton("保存");
        editgoods.setButtonType(JFXButton.ButtonType.RAISED);
        editgoods.setButtonType(JFXButton.ButtonType.RAISED);
        editgoods.setBackground(new Background(new BackgroundFill(Color.web("#7C4DFF"),null,null)));
        editgoods.setTextFill(Color.web("#FFF"));
        editgoods.setFont(Font.font(18));
        editgoods.setMinSize(60,10);
        InfoCard2.setSpacing(20);
        InfoCard2.setAlignment(Pos.BOTTOM_RIGHT);
        errorText.setAlignment(Pos.CENTER_RIGHT);
        InfoCard2.getChildren().addAll(errorText,editgoods);

        newRow.getChildren().addAll(goodsImageContent,InfoCard1,InfoCard2);
        newRow.setAlignment(CENTER);
        newRow.setAlignment(Pos.CENTER_LEFT);
        newRow.setPrefWidth(300);
        newRow.setPadding(new Insets(40, 30, 5, 40));
        newRow.setSpacing(20);

        goodsName.setDisable(false);
        goodsInfo.setDisable(false);
        goodsPrice.setDisable(false);
        goodsTag.setDisable(false);

        //final Goods b = goods;
        final JFXTextField goods_Name=goodsName;
        final JFXTextField goods_Info=goodsInfo;
        final JFXTextField goods_Price=goodsPrice;
        final ComboBox goods_Tag=goodsTag;
        final Label error_Text=errorText;
        editgoods.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Name=goods_Name.getText();
                String Info=goods_Info.getText();
                String Price=goods_Price.getText();
                String Tag=goods_Tag.getValue().toString();

                if (Name.length() == 0 || Info.length() == 0 || Price.length() == 0 || Tag.length()==0)
                    error_Text.setText("有空项!");
                else {
                    System.out.println("here");
                    GoodsViewModel newGoods = new GoodsViewModel();
                    newGoods.setGoodsName(Name);
                    newGoods.setImgUrl("./images/item.png");
                    newGoods.setPrice(Double.valueOf(Price));
                    newGoods.setTag(Integer.valueOf(Tag));

                    WebResponse res = controller.api.post("/goods", JSON.toJSONString(newGoods));

                    error_Text.setText("");
                    goods_Name.setDisable(true);
                    goods_Info.setDisable(true);
                    goods_Price.setDisable(true);
                    goods_Tag.setDisable(true);
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

