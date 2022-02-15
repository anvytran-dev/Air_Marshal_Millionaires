package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.models.InteractableData;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class Inventory extends VBox {
    Button button1, button2, button3, button4;
    Integer count = 1;
    public Inventory(){
        button1 = new Button();
        button1.setText("Inventory");

        button2 = new Button();
        button2.setText("Item 1");

        button3 = new Button();
        button3.setText("Item 2");

        button4 = new Button();
        button4.setText("Item 3");

        getChildren().addAll(button1,button2,button3,button4);
    }
    public void AddItem (InteractableData item){
        Button b = new Button();
        b.setText(item.getName());
//        Image image = new Image(getClass().getResourceAsStream("not.png"));
//        b.setGraphic(new ImageView());


        if(count == 1){
            button1 = b;
            System.out.println("Button 1 working");
        }
        if(count == 2){
            button2 = b;
        }
        if(count == 3){
            button3 = b;
        }
        if(count == 4){
            button4 = b;
        }
        count ++;
        getChildren().addAll(button1,button2,button3,button4);


    }
}


