package com.millionaires.airmarshal.views.components;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class Inventory extends VBox {
    public Inventory(){
        Button button1 = new Button();
        button1.setText("Inventory");

        Button button2 = new Button();
        button2.setText("Item 1");

        Button button3 = new Button();
        button3.setText("Item 2");

        Button button4 = new Button();
        button4.setText("Item 3");

        getChildren().addAll(button1,button2,button3,button4);
    }
}

