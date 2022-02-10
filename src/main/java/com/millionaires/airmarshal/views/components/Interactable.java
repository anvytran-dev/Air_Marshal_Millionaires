package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.models.InteractableData;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Interactable extends ImageView {

    public Interactable(InteractableData data){
        setPickOnBounds(true); // allows click on transparent areas
        setOnMouseClicked((e) -> {
            System.out.println("Clicked!"); // change functionality
        });
        setCursor(Cursor.HAND);
        setLayoutX(data.getX());
        setLayoutY(data.getY());
        setImage(new Image(data.getImagePath()));
    }

}
