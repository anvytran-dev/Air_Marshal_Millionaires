package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class ItemsDisplay extends StackPane {
    public ItemsDisplay(List<InteractableData> items) {
        super();
        Rectangle box = new Rectangle(999, 150, Color.BLACK);
        box.setArcWidth(10.0);
        box.setArcHeight(10.0);
        box.setStrokeWidth(2);
        box.setOpacity(.5);

        HBox display = new HBox();
        display.setSpacing(50);
        display.setAlignment(Pos.CENTER);
        for (InteractableData itemsData : items) {
            Interactable item = new Interactable(itemsData);
            item.setFitHeight(100);
            item.setPreserveRatio(true);
            display.getChildren().add(item);
        }

        Label label = new Label("Items in " + ViewInterface.getInstance().getCompartmentName());
        label.setFont(Font.font("System Regular", FontWeight.NORMAL, 20));
        label.setTextFill(Color.WHITE);
        label.setPadding(new Insets(5));
        VBox contents = new VBox(label, display);

        getChildren().addAll(box, contents);
    }
}
