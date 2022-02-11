package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.views.components.DialogBox;
import com.millionaires.airmarshal.views.components.SideMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameView extends StackPane {

    DialogBox dialogBox;

    public GameView(CompartmentView compartment) {
        super();
        dialogBox = new DialogBox("Default text", hideDialogBox);
        dialogBox.setVisible(false);
        BorderPane pane = new BorderPane(compartment);

        SideMenu sm = new SideMenu();
        sm.setPrefWidth(300);
        pane.setRight(sm);

        getChildren().addAll(pane, dialogBox);
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
