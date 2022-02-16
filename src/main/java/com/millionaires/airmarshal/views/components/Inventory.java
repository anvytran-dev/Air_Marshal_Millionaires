package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.awt.*;


public class Inventory extends VBox {

    public Inventory() {


        for (InteractableData item : ViewInterface.getInstance().getPlayerInventory())
        {   Button button1 = new Button();
            button1.setEffect(new DropShadow(30, Color.PURPLE));
            button1.setText(item.getName());
            button1.setPrefWidth(300);
            button1.setStyle("-fx-base: #6495ED; -fx-font-size:" + 20);
            button1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            getChildren().addAll(button1);
        }

    }


}


