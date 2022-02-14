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

    private ViewInterface api = ViewInterface.getInstance();
    private Label currentCompartment;
    private Inventory inventory = new Inventory();
    private MenuOptions menuOptions = new MenuOptions();
    private boolean shouldShowInventory = true;

    public SideMenu() {
        System.out.println(this);
        Label timeRemaining = new Label("04:36 remaining");

        Button menuButton = new Button("=");
        menuButton.setOnMouseClicked(menuButtonFunction);

        currentCompartment = new Label(api.getCompartmentName());
        VBox topSection = new VBox(currentCompartment, timeRemaining, menuButton);

        topSection.setAlignment(Pos.CENTER);
        Directionals directionals = new Directionals();
        this.getChildren().addAll(topSection, inventory, directionals);

        setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle("-fx-font-family: 'sans-serif'");
        setEffect(new DropShadow(50, Color.BLACK));
    }



    EventHandler menuButtonFunction = event -> {
        getChildren().remove(1);
        if (shouldShowInventory) {
            getChildren().add(1, inventory);

        } else {
            getChildren().add(1, menuOptions);

        }
        shouldShowInventory = !shouldShowInventory;

    };


}
