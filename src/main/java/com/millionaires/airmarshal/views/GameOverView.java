package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.views.components.DialogBox;
import com.millionaires.airmarshal.views.components.NameCollector;
import com.millionaires.airmarshal.views.components.StandardButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * The GameOverView object creates the Game Over screen that the user sees when they win or lose the game. Depending on if they win or lose, the message that they will see will be different. The Game Over screen also allows the player to Play Again or go back to the Main Menu.
 */

public class GameOverView extends VBox {

    HBox dynamicArea = new HBox();

    public GameOverView (boolean b) {
        super();
        setStyle("-fx-font-family: 'sans-serif'");

        // The text logo
        ImageView logo = new ImageView(getPath("large_logo.png"));
        logo.setEffect(new DropShadow(60, Color.BLACK));
        logo.setFitWidth(500);
        logo.setPreserveRatio(true);

        // Get the plane image and set properties
        ImageView plane = new ImageView(getPath("plane.png"));
        plane.setFitWidth(400);
        plane.setPreserveRatio(true);

        // Game Over Display

        DialogBox gameOver = displayGameOverText(b);

        // For some reason, instantiating these outside of constructor disables onAction.
        // So declaring here instead
        MenuButtons menuButtons = new MenuButtons();
        NameCollector nameCollector = new NameCollector(showMainMenuButtons);

        dynamicArea.getChildren().add(menuButtons);
        dynamicArea.setAlignment(Pos.CENTER);
        dynamicArea.setPadding(new Insets(100,0,0,0));

        // Add all the children to the view and set properties
        getChildren().addAll(plane, logo, gameOver, dynamicArea);
        setAlignment(Pos.BASELINE_CENTER);
        setBackground(getBackgroundImage(getPath("bg.png")));
        setSpacing(5);
        setPadding(new Insets(50, 0, 0, 0));
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

    private DialogBox displayGameOverText(Boolean didWin) {
        DialogBox gameOverTextBox;
        if(didWin){
            gameOverTextBox = new DialogBox("The stewardess takes the boarding pass you found. \nKnowing this pass belongs to the identity of the killer, you shake in anticipation of their identity. \n The stewardess takes the boarding pass and quickly points to the seat you woke up from... \n To your shock you are the killer. Why would you do such thing?\n", 1400, 250);

        } else {

            gameOverTextBox = new DialogBox("\nGAME OVER: THE PASSENGER HAS BEEN MURDERED! \nTime has run out and you were not able to figure out who the murderer is.\nIn order to win the game you need to collect specific items and talk to the right people once you have collected the items.\n", 1700, 250);
        }

        return gameOverTextBox;
    }

    class MenuButtons extends VBox {
        MenuButtons() {
            super();

            StandardButton playBtn = new StandardButton("Play Again", restartGame);
            playBtn.setPrefWidth(200);

            StandardButton quitBtn = new StandardButton("Quit", quitGame);
            quitBtn.setPrefWidth(200);

            StandardButton mainMenuBtn = new StandardButton("Main Menu", restartGame);
            mainMenuBtn.setPrefWidth(200);

            getChildren().addAll(playBtn, quitBtn, mainMenuBtn);
            setAlignment(Pos.CENTER);
            setSpacing(5);
        }
    }
    EventHandler<ActionEvent> showMainMenuButtons = actionEvent -> {
        dynamicArea.getChildren().clear();
        dynamicArea.getChildren().addAll(new MenuButtons());
    };

    EventHandler<ActionEvent> restartGame = actionEvent -> ViewInterface.getInstance().restartGame();

    EventHandler<ActionEvent> quitGame = event -> ViewInterface.getInstance().quitGame();

}

