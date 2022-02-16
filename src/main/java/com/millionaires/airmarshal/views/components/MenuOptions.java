package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.music.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;

public class MenuOptions extends VBox {
    public MenuOptions() {
        Button button1 = new Button();
        button1.setText("Sound");
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MusicPlayer.init();
            }
        });


        Button button2 = new Button();
        button2.setText("Save");
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        Button button3 = new Button();
        button3.setText("Load");
        button3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        setStyle("-fx-base: #3f00ff; -fx-font-size:" + 20);
        button1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        button2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        button3.setFont(Font.font("Verdana", FontWeight.BOLD, 20));


        setSpacing(5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(button1, button2, button3);

    }


}
