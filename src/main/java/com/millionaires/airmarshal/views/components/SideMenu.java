package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.views.CompartmentView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class SideMenu extends  BorderPane{

    private ViewInterface api = ViewInterface.getInstance();
    private Label currentCompartment;
    private Inventory inventory = new Inventory();
    private MenuOptions menuOptions = new MenuOptions();
    private boolean shouldShowInventory = true;
    Label timeRemaining = new Label(api.getRemainingTime());

    public SideMenu() {
        System.out.println(this);

        Button menuButton = new Button("=");
        menuButton.setPrefWidth(60);
        menuButton.setStyle("-fx-base: #6495ED; -fx-font-size:" + 20);
        menuButton.setOnMouseClicked(menuButtonFunction);

        currentCompartment = new Label(api.getCompartmentName());
        Directionals directionals = new Directionals();

        currentCompartment.setStyle("-fx-base: #6495ED; -fx-font-size:" + 30);


        timeRemaining.setStyle("-fx-base: #6495ED; -fx-font-size:" + 20);


        Separator separator = new Separator(Orientation.HORIZONTAL);

        VBox topSection = new VBox(menuButton, currentCompartment, timeRemaining);
        VBox middleSection = new VBox(inventory);
        VBox bottomSection = new VBox(directionals);


        topSection.setAlignment(Pos.CENTER);
        topSection.setSpacing(15);
        setTop(topSection);


        middleSection.setTranslateY(100);
        middleSection.setEffect(new DropShadow(70, Color.PURPLE));
        setCenter(middleSection);






        setBackground(new Background(new BackgroundFill(Color.DARKVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle("-fx-font-family: 'sans-serif'");
        setEffect(new DropShadow(50, Color.BLACK));

        bottomSection.setStyle("-fx-base: #3f00ff; -fx-font-size:" + 35);
        bottomSection.setEffect(new DropShadow(45, Color.BLACK));
//        bottomSection.setTranslateY(200);

//        this.getChildren().add(bottomSection);
        setBottom(bottomSection);
        bottomSection.setAlignment(Pos.CENTER);

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

    public void updateTimer(String secs) {
        timeRemaining.setText(secs);
    }


}
