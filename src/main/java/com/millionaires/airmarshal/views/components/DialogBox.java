package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DialogBox extends StackPane {
    private final Text dialogText;

    public DialogBox(String text) {
        this(text, 900, 200);
    }

    public DialogBox(String text, double width, double height) {
        super();

        // The visible box
        Rectangle box = new Rectangle(width, height, Color.WHITESMOKE);
        box.setArcWidth(10.0);
        box.setArcHeight(10.0);
        box.setStrokeWidth(2);
        box.setStroke(Color.BLACK);
        box.setEffect(new DropShadow(30, Color.GREY));

        // The actual dialog to display
        dialogText = new Text(text);
        dialogText.setFont(Font.font("Verdana", 25));

        // The dismiss button with default function in case it was null
        StandardButton dismissButton = new StandardButton("Ok", onDismiss);
        dismissButton.setPrefWidth(80);

        // Column contains dialog, a text spacer for aesthetics, and the dismiss button
        VBox col = new VBox(10,dialogText, new Text(""), dismissButton);
        col.setPadding(new Insets(5));
        col.setAlignment(Pos.CENTER);

        // Add nodes and set StackPane attributes
        getChildren().addAll(box, col);
        setMaxWidth(width);
        setMaxHeight(height);
        setLayoutY(500);

    }

    EventHandler<ActionEvent> onDismiss = e -> ViewInterface.getInstance().dismissDialog();

    public void setText(String text){
        dialogText.setText(text == null ? "" : text);
    }
}
