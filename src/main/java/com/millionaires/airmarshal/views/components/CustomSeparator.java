package com.millionaires.airmarshal.views.components;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

public class CustomSeparator extends Separator {
    public CustomSeparator(){
        super(Orientation.HORIZONTAL);
        setStyle("-fx-base: #6495ED;");
        setPadding(new Insets(5));
    }
}
