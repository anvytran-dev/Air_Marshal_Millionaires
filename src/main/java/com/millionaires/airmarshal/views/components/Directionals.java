package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Renders arrows that using standard size, spacing, and positioning.
 */

public class Directionals extends HBox {

    ViewInterface api = ViewInterface.getInstance();

    public Directionals() {
        super();
        Button forwardButton = getButton("↑", goDir("forward"));
        Button backButton = getButton("↓", goDir("back"));
        Button leftButton = getButton("←", goDir("left"));
        Button rightButton = getButton("→", goDir("right"));

        /**
         * Only renders buttons that can be used in a specific scene
         */

        for (String directionAvailable : api.getAvailableCompartmentDirections()) {
            switch (directionAvailable) {
                case "forward":
                    forwardButton.setDisable(false);
                    break;
                case "back":
                    backButton.setDisable(false);
                    break;
                case "left":
                    leftButton.setDisable(false);
                    break;
                case "right":
                    rightButton.setDisable(false);
                    break;
                default:
                    System.out.println("You forgot to change a direction in the room_data.json");
            }
        }
        /**
         * setting arrows up
         */
        VBox middleButtons = new VBox(forwardButton, backButton);
        setAlignment(Pos.BOTTOM_CENTER);

        getChildren().addAll(leftButton, middleButtons, rightButton);
        setPadding(new Insets(0,0,10,0));
    }


    private Button getButton(String dir, EventHandler<ActionEvent> func) {
        Button dirButton = new Button(dir);
        dirButton.setOnAction(func);
        dirButton.setDisable(true);
        return dirButton;
    }

    /**
     * Moves the player from one scene to the next and updates the view
     * Prevents the player from moving to certain scenes if the player
     */
    private EventHandler<ActionEvent> goDir(String dir) {
        return event -> api.goDirection(dir);
    }


}