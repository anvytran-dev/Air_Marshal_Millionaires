package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Interactable extends ImageView {

    public Interactable(InteractableData data) {
        setPickOnBounds(true); // allows click on transparent areas
        setOnMouseClicked(onClick(data));
        setCursor(Cursor.HAND);
        setLayoutX(data.getX());
        setLayoutY(data.getY());
        setImage(new Image(data.getImagePath()));
    }

    EventHandler<MouseEvent> onClick(InteractableData data) {
        return mouseEvent -> {
            ViewInterface.getInstance().showDialogBox(data.getDialog());
        };
    }

}
