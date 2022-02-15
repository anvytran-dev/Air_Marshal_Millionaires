package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.views.components.DialogBox;
import com.millionaires.airmarshal.views.components.SideMenu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameView extends StackPane {

    DialogBox dialogBox;
    SideMenu sm = new SideMenu();

    public GameView(CompartmentView compartment) {
        super();
        dialogBox = new DialogBox("Default text");
        dialogBox.setVisible(false);
        BorderPane pane = new BorderPane(compartment);

        sm.setPrefWidth(300);
        pane.setRight(sm);

        getChildren().addAll(pane, dialogBox);
        ViewInterface.getInstance().setGameView(this);
    }



    public void setDialogVisible(boolean b) {
        dialogBox.setVisible(b);
    };

    public void setDialogText(String s){
        dialogBox.setText(s);
    }

    public void updateTimer(String secs) {
        sm.updateTimer(secs);
    }
}
