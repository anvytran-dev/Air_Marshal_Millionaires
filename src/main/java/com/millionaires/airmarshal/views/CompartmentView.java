package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.views.components.CharacterDisplay;
import com.millionaires.airmarshal.views.components.ItemsDisplay;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class CompartmentView extends AnchorPane {
    private final double CHARACTER_HEIGHT = 500;
    private final double ITEM_HEIGHT = 100;

    public CompartmentView(CompartmentData data) {
        super();

        CharacterDisplay characters =  new CharacterDisplay(data.getCharacters());
        getChildren().add(characters);
        setBottomAnchor(characters, 0.0);

        ItemsDisplay items = new ItemsDisplay(data.getItems());
        getChildren().add(items);
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
