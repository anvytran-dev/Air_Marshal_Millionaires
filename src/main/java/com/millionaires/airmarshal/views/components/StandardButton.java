package com.millionaires.airmarshal.views.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class StandardButton extends Button {

    public StandardButton(String label, EventHandler<ActionEvent> eventHandler) {
        super(label);
        setStyle("-fx-base: #33B8FF");
        setTextFill(Color.WHITE);
        setOnAction(eventHandler);
    }
}