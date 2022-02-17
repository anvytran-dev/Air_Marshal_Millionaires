package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;


public class Inventory extends VBox {

    public Inventory() {
        List<Node> nodesToAdd = new ArrayList<>();

        Label inventoryLabel = new Label("Inventory");
        inventoryLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 30));
        inventoryLabel.setTextFill(Color.WHITE);
        inventoryLabel.setPadding(new Insets(0,0,10,0));

        nodesToAdd.add(inventoryLabel);

        for (InteractableData item : ViewInterface.getInstance().getPlayerInventory()) {
            Button button1 = new Button();
            button1.setEffect(new DropShadow(30, Color.PURPLE));
            button1.setText(item.getPrettyName());
            button1.setPrefWidth(300);
            button1.setStyle("-fx-base: #6495ED; -fx-font-size:" + 20);
            button1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            nodesToAdd.add(button1);
        }

        getChildren().addAll(nodesToAdd);
        setAlignment(Pos.CENTER);
    }


}


