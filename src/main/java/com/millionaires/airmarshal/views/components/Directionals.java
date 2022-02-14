package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class Directionals extends HBox {

    ViewInterface api = ViewInterface.getInstance();

    public Directionals(){
        Button forwardButton = getButton("↑", goDir("forward"));
        Button backButton = getButton("↓", goDir("back"));
        Button leftButton = getButton("←", goDir("left"));
        Button rightButton = getButton("→", goDir("right"));

        for(String directionAvailable : api.getAvailableCompartmentDirections()){
            switch(directionAvailable){
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

        VBox middleButtons = new VBox(forwardButton, backButton);

        getChildren().addAll(leftButton,middleButtons,rightButton);

        setAlignment(Pos.BOTTOM_CENTER);
        setAlignment(Pos.BOTTOM_CENTER);
    }

    private Button getButton(String dir, EventHandler<ActionEvent> func){
        Button dirButton = new Button(dir);
        dirButton.setOnAction(func);
        dirButton.setDisable(true);
        return dirButton;
    }

    private EventHandler<ActionEvent> goDir(String dir) {
        return event -> api.goDirection(dir);
    }



}