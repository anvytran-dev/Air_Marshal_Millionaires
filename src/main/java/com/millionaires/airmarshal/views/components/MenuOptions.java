package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuOptions extends VBox {
    public MenuOptions() {

        ViewInterface api = ViewInterface.getInstance();
        /**
         * turn music on and off
         */

        Button soundBtn = getMenuButton("Sound");
        soundBtn.setOnMouseClicked(toggleSound);
        /**
         * saves game
         */

        Button saveBtn = getMenuButton("Save");
        saveBtn.setOnMouseClicked(mouseEvent -> api.saveGame());
        /**
         * displays instructions that are from the main screen
         */

        Button helpBtn = getMenuButton("Help");
        helpBtn.setOnMouseClicked(showHelpMenu);

        Label inventoryLabel = new Label("Options");
        inventoryLabel.setFont(Font.font("System Regular", FontWeight.BOLD, 30));
        inventoryLabel.setTextFill(Color.WHITE);
        inventoryLabel.setPadding(new Insets(0,0,10,0));

        setStyle("-fx-base: #3f00ff; -fx-font-size:" + 20);
        setSpacing(5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(inventoryLabel, soundBtn, saveBtn, helpBtn);
    }

    private EventHandler<MouseEvent> toggleSound = mouseEvent -> {
        String errorMsg = ViewInterface.getInstance().toggleMusic();
        if (errorMsg != null)
            System.out.println("Error occurred while trying to play audio: " + errorMsg);
    };

    /**
     * created button to use for all the menu options
     * @param label to name the button
     * @return button
     */

    private Button getMenuButton(String label) {
        Button b = new Button(label);
        b.setPrefWidth(100);
        b.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        return b;
    }

    /**
     * an EventHandler which shows the help menu
     */
    private EventHandler<MouseEvent> showHelpMenu = e -> ViewInterface.getInstance().showHelpMenu();
}
