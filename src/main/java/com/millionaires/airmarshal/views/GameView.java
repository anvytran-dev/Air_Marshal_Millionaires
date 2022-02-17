package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.views.components.DialogBox;
import com.millionaires.airmarshal.views.components.InstructionsDisplay;
import com.millionaires.airmarshal.views.components.SideMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameView extends StackPane {

    DialogBox dialogBox;
    SideMenu sm = new SideMenu();
    InstructionsDisplay instructions;

    public GameView(CompartmentView compartment) {
        super();
        dialogBox = new DialogBox("Default text");
        dialogBox.setVisible(false);

        instructions = new InstructionsDisplay(ViewInterface.getInstance().getInstructions(), dismissInstructions);
        instructions.setVisible(false);

        sm.setPrefWidth(300);

        BorderPane pane = new BorderPane(compartment);
        pane.setRight(sm);

        getChildren().addAll(pane, dialogBox, instructions);
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

    public void showHelpMenu() {
        instructions.setVisible(true);
    }

    private EventHandler<ActionEvent> dismissInstructions = e -> instructions.setVisible(false);


}
