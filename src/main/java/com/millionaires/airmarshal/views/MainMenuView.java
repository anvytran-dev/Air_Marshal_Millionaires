package com.millionaires.airmarshal.views;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.views.components.InstructionsDisplay;
import com.millionaires.airmarshal.views.components.SaveLoader;
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

public class MainMenuView extends VBox {

    HBox dynamicArea = new HBox();

    public MainMenuView() {
        super();
        setStyle("-fx-font-family: 'sans-serif'");

        // The text logo
        ImageView logo = new ImageView(getPath("large_logo.png"));
        logo.setEffect(new DropShadow(60, Color.BLACK));

        // Get the plane image and set properties
        ImageView plane = new ImageView(getPath("plane.png"));
        plane.setFitWidth(600);
        plane.setPreserveRatio(true);

        // For some reason, instantiating these outside of constructor disables onAction.
        // So declaring here instead
        MenuButtons menuButtons = new MenuButtons();
        NameCollector nameCollector = new NameCollector(showMainMenuButtons);

        dynamicArea.getChildren().add(menuButtons);
        dynamicArea.setAlignment(Pos.CENTER);
        dynamicArea.setPadding(new Insets(100, 0, 0, 0));

        // Add all the children to the view and set properties
        getChildren().addAll(plane, logo, dynamicArea);
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

    class MenuButtons extends VBox {
        MenuButtons() {
            super();

            StandardButton playBtn = new StandardButton("Play", showNameCollector);
            playBtn.setPrefWidth(200);

            StandardButton loadBtn = new StandardButton("Load", showSaveLoader);
            loadBtn.setPrefWidth(200);
            if (!ViewInterface.getInstance().savesExist())
                loadBtn.setDisable(true);

            StandardButton instBtn = new StandardButton("Instructions", showInstructions);
            instBtn.setPrefWidth(200);

            StandardButton quitBtn = new StandardButton("Quit", quitGame);
            quitBtn.setPrefWidth(200);

            getChildren().addAll(playBtn, loadBtn, instBtn, quitBtn);
            setAlignment(Pos.CENTER);
            setSpacing(5);
        }
    }

    EventHandler<ActionEvent> showMainMenuButtons = actionEvent -> {
        dynamicArea.getChildren().clear();
        dynamicArea.getChildren().addAll(new MenuButtons());
    };

    EventHandler<ActionEvent> showNameCollector = actionEvent -> {
        dynamicArea.getChildren().clear();
        dynamicArea.getChildren().add(new NameCollector(showMainMenuButtons));
    };

    EventHandler<ActionEvent> showSaveLoader = actionEvent -> {
        dynamicArea.getChildren().clear();
        dynamicArea.getChildren().add(new SaveLoader(showMainMenuButtons));
    };

    EventHandler<ActionEvent> showInstructions = actionEvent -> {
        dynamicArea.getChildren().clear();
        String instructions = ViewInterface.getInstance().getInstructions();
        dynamicArea.getChildren().add(new InstructionsDisplay(instructions, showMainMenuButtons));
    };

    EventHandler<ActionEvent> quitGame = event -> ViewInterface.getInstance().quitGame();
}
