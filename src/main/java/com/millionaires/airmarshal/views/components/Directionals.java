package com.millionaires.airmarshal.views.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class Directionals extends HBox {
    public Directionals(){
        Button forwardButton = new Button();
        forwardButton.setText("↑");

        Button backButton = new Button();
        backButton.setText("↓");

        Button leftButton = new Button();
        leftButton.setText("←");

        Button rightButton = new Button();
        rightButton.setText("→");

        VBox middleButtons = new VBox(forwardButton, backButton);

        getChildren().addAll(leftButton,middleButtons,rightButton);

        setAlignment(Pos.BOTTOM_CENTER);
        setAlignment(Pos.BOTTOM_CENTER);



    }
}