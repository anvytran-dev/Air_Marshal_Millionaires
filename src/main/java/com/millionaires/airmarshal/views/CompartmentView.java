package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.views.components.Interactable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class CompartmentView extends Pane {

    public CompartmentView(CompartmentData data) {
        setStyle("" +
                "-fx-background-image: url('https://theawesomedaily.com/wp-content/uploads/2018/07/cool-backgrounds-feat-1.jpg'); " +
                "-fx-background-size: cover;"
        );

        List<Interactable> interactables = new ArrayList<>();
        for(InteractableData iData : data.getCharacters())
            interactables.add(new Interactable(iData));

        getChildren().addAll(interactables);
    }
}
