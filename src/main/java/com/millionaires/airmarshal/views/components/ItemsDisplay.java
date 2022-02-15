package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.models.InteractableData;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        getChildren().addAll(box, display);
    }
}
