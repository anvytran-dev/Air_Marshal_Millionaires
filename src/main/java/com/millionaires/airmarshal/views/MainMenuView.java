package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.views.components.Interactable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends VBox {


    public MainMenuView() {
        super();
        setStyle("-fx-font-family: 'sans-serif'");

        ImageView logo = new ImageView(getPath("large_logo.png"));

        // Get the plane image and set properties
        ImageView plane = new ImageView(getPath("plane.png"));
        plane.setFitWidth(600);
        plane.setPreserveRatio(true);

        // Add all the children to the view and set properties
        getChildren().addAll(plane, logo, new MenuButtons());
        setAlignment(Pos.BASELINE_CENTER);
        setBackground(getBackgroundImage(getPath("bg.png")));
        setSpacing(5);


    }

    private String getPath(String fileName) {
        return "file:resources/images/MainMenuView/" + fileName;
    }

    private Background getBackgroundImage(String path) {
        Image image = new Image(path, true);
        BackgroundSize size = new BackgroundSize(1, 1, true, true, true, true);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        return new Background(bgImage);
    }

    private class MainMenuButton extends Button {
        MainMenuButton(String text, EventHandler<ActionEvent> function) {
            super();
            setText(text);
            setPrefWidth(300);
            setFont(Font.font(20));
            setOnAction(function);
            setTextFill(Color.WHITE);




        }
    }

    private class MenuButtons extends VBox {
        MenuButtons() {
            super();
//            setStyle("-fx-font-family: 'sans-serif'");
            MainMenuButton play = new MainMenuButton("Play", playGame);
            MainMenuButton load = new MainMenuButton("Load", playGame);
            MainMenuButton instructions = new MainMenuButton("Instructions", playGame);
            MainMenuButton quit = new MainMenuButton("Quit", quitGame);
            getChildren().addAll(play, load, instructions, quit);
            setAlignment(Pos.BASELINE_CENTER);
            setSpacing(5);


        }
    }

    EventHandler<ActionEvent> playGame = event -> System.out.println("Clicked play");

    EventHandler<ActionEvent> quitGame = event -> ViewInterface.getInstance().quitGame();

}
