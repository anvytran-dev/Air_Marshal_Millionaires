package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.models.Player;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Interactable extends ImageView {
    InteractableData data;

    /**
     * setting up how images will render
     *
     */

    public Interactable(InteractableData data) {
        setPickOnBounds(true); // allows click on transparent areas
        setOnMouseClicked(onClick(data));
        setCursor(Cursor.HAND);
        setLayoutX(data.getX());
        setLayoutY(data.getY());
        setImage(new Image(data.getImagePath()));
        this.data = data;
    }

    EventHandler<MouseEvent> onClick(InteractableData data) {
    /**
     * if player clicks on the stewardess while having items stored in
     * inventory, then player has won game
     * if component clicked is an item then add it to inventory
     */
        return mouseEvent -> {

            

            if(data.getName().equals("stewardess") && Player.getInstance().hasWinningItems()) {
                ViewInterface.getInstance().winGame();
            }

            if(data.isItem()) {
                ViewInterface.getInstance().addItem(data);
            }
            ViewInterface.getInstance().showDialogBox(data.getDialog());


        };
    }

}
