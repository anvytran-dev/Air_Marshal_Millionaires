package com.millionaires.airmarshal.views.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InstructionsDisplay extends VBox {
    double WIDTH = 800;
    double HEIGHT = 300;

    public InstructionsDisplay(String instructions, EventHandler<ActionEvent> onDismiss) {
        super(5);

        // Set the text using the receive param
        Text text = new Text(instructions);
        text.setFill(Color.WHITE);

        // Create a backdrop to more easily see the text
        Rectangle box = new Rectangle(WIDTH, HEIGHT, Color.BLACK);
        box.setArcWidth(10.0);
        box.setArcHeight(10.0);
        box.setStrokeWidth(2);
        box.setOpacity(.5);

        // Stack the text on top of the box
        StackPane stack = new StackPane(box, text);

        StandardButton dismissBtn = new StandardButton("Ok", onDismiss);

        // Let the user know what we are looking at
        Text instLabel = new Text("Instructions");
        instLabel.setFont(Font.font(30));
        instLabel.setFill(Color.WHITE);

        // Add all the children and set alignment
        getChildren().addAll(instLabel, stack, dismissBtn);
        setAlignment(Pos.CENTER);
        setMaxHeight(HEIGHT);
        setMaxWidth(WIDTH);
    }
}
