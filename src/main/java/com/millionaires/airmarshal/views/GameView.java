package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.views.components.DialogBox;
import com.millionaires.airmarshal.views.components.InstructionsDisplay;
import com.millionaires.airmarshal.views.components.SideMenu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * The main view of the game. Holds the CompartmentView, SideMenu, DialogBox, and InstructionsDisplay (help menu)
 */
public class GameView extends StackPane {

    DialogBox dialogBox;
    SideMenu sm = new SideMenu();
    InstructionsDisplay instructions;

    /**
     * Renders the provided compartment view with the SideMenu
     * @param compartment the CompartmentView to be rendered
     */
    public GameView(CompartmentView compartment) {
        super();

        // Init the DialogBox and set invisible
        dialogBox = new DialogBox("Default text");
        dialogBox.setVisible(false);

        // Init the InstructionsDisplay and set invisible
        instructions = new InstructionsDisplay(ViewInterface.getInstance().getInstructions(), dismissInstructions);
        instructions.setVisible(false);

        sm.setPrefWidth(300);

        // Display the provided CompartmentView in the center of a BorderPane.
        // Then set the SideMenu to render in the right side of the screen
        BorderPane pane = new BorderPane(compartment);
        pane.setRight(sm);

        // Add all renderers to the view and give
        // the ViewInterface a reference to itself for future use
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
