package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The NameCollector object asks the player to enter their name and sets their name.
 */

public class NameCollector extends VBox {

    TextField input = new TextField();
    Text errorMessage = new Text("");

    public NameCollector(EventHandler<ActionEvent> onDismiss) {
        super(5);

        input.setOnKeyPressed(startGameFromEnterKeyPress());

        Text prompt = new Text("Enter your name");
        prompt.setFill(Color.WHITE);
        prompt.setFont(Font.font(20));

        StandardButton backBtn = StandardButton.red("Back", onDismiss);
        StandardButton playBtn = new StandardButton("Play", startGameFromButtonClick());

        HBox bottomBtns = new HBox(20, backBtn, playBtn);
        bottomBtns.setAlignment(Pos.BOTTOM_CENTER);
        bottomBtns.setPadding(new Insets(0, 0, 40, 0));

        input.setFont(Font.font(20));
        input.setAlignment(Pos.CENTER);


        errorMessage.setFill(Color.RED);
        errorMessage.setFont(Font.font(20));

        getChildren().addAll(prompt, input, bottomBtns, errorMessage);
        setAlignment(Pos.CENTER);
        setMaxWidth(300);
    }

    private EventHandler<KeyEvent> startGameFromEnterKeyPress() {
        return e -> {
            if (e.getCode() == KeyCode.ENTER)
                startGame();
        };
    }

    private EventHandler<ActionEvent> startGameFromButtonClick() {
        return e -> startGame();
    }

    private void startGame(){
        errorMessage.setText(""); // Remove any error message that exists

        String desiredName = input.getText();
        ViewInterface api = ViewInterface.getInstance();
        if (api.setPlayerName(desiredName)) {
            api.startGame();
        } else {
            errorMessage.setText("Name is required");
        }
    }
}