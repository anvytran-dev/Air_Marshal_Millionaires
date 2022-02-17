package com.millionaires.airmarshal.views.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class StandardButton extends Button {

    private static int DEFAULT_FONT_SIZE = 20;

    public StandardButton(String label) {
        this(label, e -> {});
    }

    public StandardButton(String label, EventHandler<ActionEvent> onAction) {
        super(label);
        setStyle("-fx-base: #33B8FF; -fx-font-size:" + DEFAULT_FONT_SIZE);
        setTextFill(Color.WHITE);
        setOnAction(onAction);
    }

    public static StandardButton red(String label, EventHandler<ActionEvent> onAction) {
        StandardButton b = new StandardButton(label, onAction);
        b.setStyle("-fx-base: #FF2E2E; -fx-font-size:" + DEFAULT_FONT_SIZE);
        return b;
    }

    public static StandardButton red(String label) {
        return StandardButton.red(label, e -> {
        });
    }
}