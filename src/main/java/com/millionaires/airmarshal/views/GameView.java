package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.views.components.DialogBox;
import com.millionaires.airmarshal.views.components.SideMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class GameView extends StackPane {

    DialogBox dialogBox;

    public GameView(CompartmentView compartment) {
        super();
        dialogBox = new DialogBox("Default text", hideDialogBox);

        Button showDialogButton = new Button("Show dialog");
        showDialogButton.setOnAction(showDialogBox);

        Button setTextButton = new Button("Set text");
        setTextButton.setOnAction(setDialog);

        HBox compartmentAndMenu = new HBox(compartment, new SideMenu(), showDialogButton, setTextButton);
        getChildren().addAll(compartmentAndMenu, dialogBox);
    }

    private EventHandler<ActionEvent> showDialogBox = actionEvent -> {
        dialogBox.setVisible(true);
    };

    private EventHandler<ActionEvent> hideDialogBox = actionEvent -> {
        dialogBox.setVisible(false);
    };

    private EventHandler<ActionEvent> setDialog = actionEvent -> {
        dialogBox.setText("Hello world!");
    };


}
