package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SideMenu extends VBox {

    ViewInterface api = ViewInterface.getInstance();

   Label currentCompartment = new Label("commercial class");

    private void updateCompartmentName(String compartmentName) {
        System.out.println(compartmentName);
        this.currentCompartment.setText(compartmentName);
        System.out.println(currentCompartment.getText());

    }

    EventHandler<ActionEvent> goForward = actionEvent ->

    {
        api.goDirection("forward");
        updateCompartmentName(api.getCompartmentName());
    };

    EventHandler<ActionEvent> goBack = actionEvent -> {
        api.goDirection("back");
        updateCompartmentName(api.getCompartmentName());
    };

    EventHandler<ActionEvent> goLeft = actionEvent -> {
        api.goDirection("left");
        updateCompartmentName(api.getCompartmentName());
    };

    EventHandler<ActionEvent> goRight = actionEvent -> {
        api.goDirection("right");
        updateCompartmentName(api.getCompartmentName());
    };

    Inventory inventory = new Inventory();
    MenuOptions menuOptions = new MenuOptions();
    Directionals directionals = new Directionals(goForward, goBack, goLeft, goRight);
    boolean shouldShowInventory = true;


    public SideMenu() {

        Label timeRemaining = new Label("Time Remaining : 04 : 36");

        Button menuButton = new Button("=");
        menuButton.setOnMouseClicked(menuButtonFunction);


        VBox topSection = new VBox(currentCompartment, timeRemaining, menuButton);

        //currentCompartment.setX(50);
        //currentCompartment.setY(50);
        topSection.setAlignment(Pos.CENTER);

        this.getChildren().addAll(topSection, inventory, directionals);


        setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle("-fx-font-family: 'sans-serif'");
        setEffect(new DropShadow(50, Color.BLACK));

    }


    EventHandler menuButtonFunction = new EventHandler() {
        @Override
        public void handle(Event event) {
            getChildren().remove(1);
            if (shouldShowInventory) {
                getChildren().add(1, inventory);

            } else {
                getChildren().add(1, menuOptions);

            }
            shouldShowInventory = !shouldShowInventory;

        }
    };


}
