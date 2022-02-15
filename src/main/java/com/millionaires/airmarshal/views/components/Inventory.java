package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class Inventory extends VBox {

    public Inventory() {

        for (InteractableData item : ViewInterface.getInstance().getPlayerInventory())
        {   Button button1 = new Button();
            button1.setText(item.getName());
            getChildren().addAll(button1);
        }

    }


}


