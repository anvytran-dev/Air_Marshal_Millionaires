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

    public CompartmentView(CompartmentData data) {

        List<Interactable> interactables = new ArrayList<>();
        for(InteractableData iData : data.getCharacters())
            interactables.add(new Interactable(iData));

        getChildren().addAll(interactables);

        setBackground(getBackgroundImage(data.getBackgroundUrl()));
    }

    private Background getBackgroundImage(String path) {
        Image image = new Image("file:" + path, true);
        BackgroundSize size = new BackgroundSize(1, 1, true, true, true, true);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        return new Background(bgImage);
    }
}
