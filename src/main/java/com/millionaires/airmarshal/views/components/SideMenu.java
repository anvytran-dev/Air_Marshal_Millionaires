package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.Duration;


public class SideMenu extends VBox {

    private ViewInterface api = ViewInterface.getInstance();
    private Label currentCompartment;
    private Inventory inventory = new Inventory();
    private MenuOptions menuOptions = new MenuOptions();
    private boolean shouldShowInventory = true;
    Label timeRemaining = new Label();
    private static boolean isTimelineRunning = false;
    Timeline oneSecondCountdown = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            String secs = ViewInterface.getInstance().subtractTime();
            System.out.println(secs);

            timeRemaining.setText(secs);


        }}));

    public void runTimeline() {

            isTimelineRunning = true;
            oneSecondCountdown.setCycleCount(Timeline.INDEFINITE);
            oneSecondCountdown.play();

    }


    public SideMenu() {
        System.out.println(this);

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

        runTimeline();
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


    public void updateTimer(Duration duration) {
        timeRemaining.setText(duration.toSeconds() + "");

    }
}
