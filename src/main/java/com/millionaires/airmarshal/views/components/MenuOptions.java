package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuOptions extends VBox {
    public MenuOptions() {

        ViewInterface api = ViewInterface.getInstance();

        Button button1 = new Button();
        button1.setText("Sound");
        button1.setOnMouseClicked(mouseEvent -> {
            String errorMsg = api.toggleMusic();
            if (errorMsg != null)
                System.out.println("Error occurred while trying to play audio: " + errorMsg);
        });

        Button button2 = new Button();
        button2.setText("Save");
        button2.setOnMouseClicked(mouseEvent -> api.saveGame());

        setStyle("-fx-base: #3f00ff; -fx-font-size:" + 20);
        button1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        button2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        setSpacing(5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(button1, button2);
    }
}
