package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.models.InteractableData;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.List;

public class CharacterDisplay extends StackPane {
    public CharacterDisplay(List<InteractableData> characters) {
        super();

        HBox display = new HBox();
        display.setSpacing(50);
        display.setAlignment(Pos.CENTER);

        for (InteractableData charData : characters) {
            Interactable character = new Interactable(charData);
            character.setFitHeight(500);
            character.setPreserveRatio(true);
            display.getChildren().add(character);
        }
        getChildren().addAll(display);
    }
}
