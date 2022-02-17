package com.millionaires.airmarshal.views.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InstructionsDisplay extends VBox {
    double WIDTH = 900;
    double HEIGHT = 450;

    public InstructionsDisplay(String instructions, EventHandler<ActionEvent> onDismiss) {
        super(5);

        // Set the text using the receive param
        Text text = new Text(instructions);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(15));

        // Create a backdrop to more easily see the text
        Rectangle blackBox = new Rectangle(WIDTH, HEIGHT, Color.BLACK);
        blackBox.setArcWidth(10.0);
        blackBox.setArcHeight(10.0);
        blackBox.setStrokeWidth(2);
        blackBox.setOpacity(.8);

        Text instLabel = new Text("Instructions");
        instLabel.setFont(Font.font(30));
        instLabel.setFill(Color.WHITE);

        // Stack the text on top of the box
        VBox labelAndText = new VBox(5, instLabel, text, new StandardButton("Ok", onDismiss));
        labelAndText.setPadding(new Insets(10));
        labelAndText.setAlignment(Pos.CENTER);
        StackPane stack = new StackPane(blackBox, labelAndText);

        // Add all the children and set alignment
        getChildren().addAll(stack);
        setAlignment(Pos.CENTER);
        setMaxHeight(HEIGHT);
        setMaxWidth(WIDTH);
    }
}
