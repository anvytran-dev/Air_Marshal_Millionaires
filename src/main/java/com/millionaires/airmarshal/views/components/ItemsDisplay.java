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

/**
 * Renders the provided InteractableData list using standard size, spacing, and positioning.
 */
public class ItemsDisplay extends StackPane {

    /**
     * Renders the provided InteractableData list using standard size, spacing, and positioning.
     * @param items a List of InteractableData to display
     */
    public ItemsDisplay(List<InteractableData> items) {
        super();

        // Creates a black, semitransparent box to help user see the text stacked on top
        Rectangle box = new Rectangle(999, 150, Color.BLACK);
        box.setArcWidth(10.0);
        box.setArcHeight(10.0);
        box.setStrokeWidth(2);
        box.setOpacity(.5);

        // Contains the Interactables to standardize
        HBox display = new HBox();
        display.setSpacing(50);
        display.setAlignment(Pos.CENTER);
        for (InteractableData itemsData : items) {
            Interactable item = new Interactable(itemsData);
            item.setFitHeight(100);
            item.setPreserveRatio(true);
            display.getChildren().add(item);
        }

        // A label to let the player know what the objects in the box are
        Label label = new Label("Items in " + ViewInterface.getInstance().getCompartmentName());
        label.setFont(Font.font("System Regular", FontWeight.NORMAL, 20));
        label.setTextFill(Color.WHITE);
        label.setPadding(new Insets(5));

        VBox contents = new VBox(label, display);
        getChildren().addAll(box, contents);
    }
}
