package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuOptions extends VBox {
    public MenuOptions() {

        ViewInterface api = ViewInterface.getInstance();

        Button soundBtn = getMenuButton("Sound");
        soundBtn.setOnMouseClicked(toggleSound);

        Button saveBtn = getMenuButton("Save");
        saveBtn.setOnMouseClicked(mouseEvent -> api.saveGame());

        Button helpBtn = getMenuButton("Help");
        helpBtn.setOnMouseClicked(showHelpMenu);

        setStyle("-fx-base: #3f00ff; -fx-font-size:" + 20);
        setSpacing(5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(soundBtn, saveBtn, helpBtn);
    }

    private EventHandler<MouseEvent> toggleSound = mouseEvent -> {
        String errorMsg = ViewInterface.getInstance().toggleMusic();
        if (errorMsg != null)
            System.out.println("Error occurred while trying to play audio: " + errorMsg);
    };

    private Button getMenuButton(String label) {
        Button b = new Button(label);
        b.setPrefWidth(100);
        b.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        return b;
    }

    private EventHandler<MouseEvent> showHelpMenu = e -> ViewInterface.getInstance().showHelpMenu();
}
