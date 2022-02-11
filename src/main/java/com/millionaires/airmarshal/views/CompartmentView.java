package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.views.components.Interactable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class CompartmentView extends Pane {

    private final double CHARACTER_HEIGHT = 300;
    private final double ITEM_HEIGHT = 100;

    public CompartmentView(CompartmentData data) {

        List<Interactable> interactables = new ArrayList<>();
        for(InteractableData iData : data.getCharacters()) {
            Interactable character = new Interactable(iData);
            character.setFitHeight(CHARACTER_HEIGHT);
            character.setPreserveRatio(true);
            interactables.add(character);
        }
        for(InteractableData itemsData : data.getItems()) {
            Interactable item = new Interactable(itemsData);
            item.setFitHeight(ITEM_HEIGHT);
            item.setPreserveRatio(true);
            interactables.add(item);
        }
        getChildren().addAll(interactables);

        setBackground(getBackgroundImage(data.getBackgroundUrl()));
    }

    private Background getBackgroundImage(String path) {
        Image image = new Image("file:" + path, true);
        BackgroundSize size = new BackgroundSize(1, 1, true, true, true, true);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        return new Background(bgImage);
    }

    private void showDialog(String text){

    }
}
