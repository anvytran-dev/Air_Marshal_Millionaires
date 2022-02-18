package com.millionaires.airmarshal.views.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * A reuseable button with styling
 */
public class StandardButton extends Button {

    private static int DEFAULT_FONT_SIZE = 20;

    /**
     * A reusable button with styling. An EventHandler must be provided
     * after initialization if the button is required to have a function
     * @param label the button text to display
     */
    public StandardButton(String label) {
        this(label, e -> {});
    }

    /**
     * A reusable button with styling.
     * @param label the button text to display
     * @param onAction the function the button should execute when clicked
     */
    public StandardButton(String label, EventHandler<ActionEvent> onAction) {
        super(label);
        setStyle("-fx-base: #33B8FF; -fx-font-size:" + DEFAULT_FONT_SIZE);
        setTextFill(Color.WHITE);
        setOnAction(onAction);
    }

    /**
     * A reusable button with styling.
     * @param label the button text to display
     * @param onAction the function the button should execute when clicked
     * @return A red variant of the StandardButton
     */
    public static StandardButton red(String label, EventHandler<ActionEvent> onAction) {
        StandardButton b = new StandardButton(label, onAction);
        b.setStyle("-fx-base: #FF2E2E; -fx-font-size:" + DEFAULT_FONT_SIZE);
        return b;
    }

    /**
     * A reusable button with styling. An EventHandler must be provided
     * after initialization if the button is required to have a function.
     * @param label the button text to display
     * @return A red variant of the StandardButton with no functionality
     */
    public static StandardButton red(String label) {
        return StandardButton.red(label, e -> {
        });
    }
}