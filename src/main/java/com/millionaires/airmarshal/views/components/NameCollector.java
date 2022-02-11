package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NameCollector extends VBox {

    TextField input = new TextField();
    Text errorMessage = new Text("");

    public NameCollector(EventHandler<ActionEvent> onDismiss) {
        super(5);
        Text prompt = new Text("Enter your name");
        prompt.setFill(Color.WHITE);
        prompt.setFont(Font.font(20));

        StandardButton backBtn = StandardButton.red("Back", onDismiss);
        StandardButton playBtn = new StandardButton("Play", onSubmit);

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


    private EventHandler<ActionEvent> onSubmit = actionEvent -> {
        errorMessage.setText(""); // Remove any error message that exists

        String desiredName = input.getText();
        ViewInterface api = ViewInterface.getInstance();
        if (api.setPlayerName(desiredName)) {
            api.startGame();
        }else{
            errorMessage.setText("Name is required");
        }

    };
}