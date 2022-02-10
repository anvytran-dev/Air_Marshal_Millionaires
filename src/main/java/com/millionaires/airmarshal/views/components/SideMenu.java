package com.millionaires.airmarshal.views.components;

import javafx.animation.Transition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SideMenu extends VBox {

    Inventory inventory = new Inventory();
    MenuOptions menuOptions = new MenuOptions();
    Directionals directionals= new Directionals();
    boolean shouldShowInventory = true;




    public SideMenu(){

        Text currentCompartment = new Text("Commercial");
        Label timeRemaining = new Label("Time Remaining : 04 : 36");

        Button menuButton = new Button("=");
        menuButton.setOnMouseClicked(menuButtonFunction);


        VBox topSection = new VBox(currentCompartment, timeRemaining, menuButton);

        currentCompartment.setX(50);
        currentCompartment.setY(50);
        topSection.setAlignment(Pos.CENTER);

        this.getChildren().addAll(topSection,inventory, directionals);




        setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(5),Insets.EMPTY)));
        setStyle("-fx-font-family: 'sans-serif'");


    }


   EventHandler menuButtonFunction = new EventHandler() {
       @Override
       public void handle(Event event) {
           getChildren().remove(1);
           if (shouldShowInventory){
               getChildren().add(1,inventory);

           }else{
               getChildren().add(1,menuOptions);

           }
           shouldShowInventory = !shouldShowInventory;

       }
   };






}
