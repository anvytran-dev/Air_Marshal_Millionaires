package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.views.components.Interactable;
import com.millionaires.airmarshal.views.components.ItemsDisplay;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CompartmentView extends AnchorPane {
    private final double CHARACTER_HEIGHT = 500;
    private final double ITEM_HEIGHT = 100;

    public CompartmentView(CompartmentData data) {
        super();




        HBox characters = new HBox();
        for (InteractableData iData : data.getCharacters()) {
            Interactable character = new Interactable(iData);
            character.setFitHeight(CHARACTER_HEIGHT);
            character.setPreserveRatio(true);
            characters.getChildren().add(character);

        }
        HBox items = new HBox();
        items.setSpacing(100);
        items.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        items.setAlignment(Pos.CENTER);
        for (InteractableData itemsData : data.getItems()) {
            Interactable item = new Interactable(itemsData);
            item.setFitHeight(ITEM_HEIGHT);
            item.setPreserveRatio(true);
            items.getChildren().add(item);
        }

        getChildren().add(characters);
        setBottomAnchor(characters, 0.0);

        getChildren().add(new ItemsDisplay(data.getItems()));
        setTopAnchor(items, 0.0);


        setBackground(getBackgroundImage(data.getBackgroundUrl()));
    }

    private Background getBackgroundImage(String path) {
        Image image = new Image("file:" + path, true);
        BackgroundSize size = new BackgroundSize(1, 1, true, true, true, true);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        return new Background(bgImage);
    }
}
