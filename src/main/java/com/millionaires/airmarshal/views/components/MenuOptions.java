package com.millionaires.airmarshal.views.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class MenuOptions extends VBox {
    public MenuOptions(){
        Button button1 = new Button();
        button1.setText("Sound");

        Button button2 = new Button();
        button2.setText("Save");

        Button button3 = new Button();
        button3.setText("Load");

        Button button4 = new Button();
        button4.setText("Help");

        setSpacing(10);
        setAlignment(Pos.CENTER);
        getChildren().addAll(button1,button2,button3,button4);

    }
}
