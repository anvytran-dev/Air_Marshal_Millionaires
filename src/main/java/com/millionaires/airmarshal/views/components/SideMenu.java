package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class SideMenu extends BorderPane {

    private ViewInterface api = ViewInterface.getInstance();
    private Label currentCompartment;
    private Inventory inventory = new Inventory();
    private MenuOptions menuOptions = new MenuOptions();
    private boolean shouldShowInventory = true;
    private Label timeRemaining = new Label("Time remaining: " + api.getRemainingTime());
    VBox middleSection;
    VBox menuButtonHolder;

    /**
     * The side menu shows the player which compartment they are in, which direction they can go to and items,
     * that can be clicked on in order to move to inventory.
     * The time remaining is displayed and shown using seconds
     *
     */

    public SideMenu() {

        currentCompartment = new Label(api.getCompartmentName());

        Directionals directionals = new Directionals();
        directionals.setStyle("-fx-base: #3f00ff; -fx-font-size:" + 35);
        directionals.setEffect(new DropShadow(45, Color.BLACK));

        currentCompartment.setFont(Font.font("System regular", FontWeight.NORMAL, 30));
        currentCompartment.setTextFill(Color.WHITE);

        timeRemaining.setFont(Font.font("System regular", FontWeight.BOLD, 20));
        timeRemaining.setTextFill(Color.WHITE);


        menuButtonHolder = new VBox(getMenuButton());
        menuButtonHolder.setAlignment(Pos.CENTER);

        middleSection = new VBox(inventory);
        VBox bottomSection = new VBox();

        bottomSection.getChildren().addAll(new CustomSeparator(), directionals);

        VBox topSection = new VBox(menuButtonHolder, currentCompartment, timeRemaining, new CustomSeparator());
        topSection.setAlignment(Pos.CENTER);
        topSection.setSpacing(15);
        topSection.setPadding(new Insets(10,0,0,0));
        setTop(topSection);

        middleSection.setEffect(new DropShadow(70, Color.PURPLE));
        setCenter(middleSection);

        setBackground(getBackgroundImage(getPath("bg.png")));
        setStyle("-fx-font-family: 'sans-serif'");
        setEffect(new DropShadow(50, Color.BLACK));

        setBottom(bottomSection);
    }

    private String getPath(String fileName) {
        return "file:resources/images/MainMenuView/" + fileName;
    }

    /**
     * background for menu matches main menu .
     * @param path used to locate file
     * @return
     */
    private Background getBackgroundImage(String path) {
        Image image = new Image(path, true);
        BackgroundSize size = new BackgroundSize(1, 1, true, true, true, true);
        BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size);
        return new Background(bgImage);
    }

    EventHandler<MouseEvent> toggleMenuOptionsInventory = event -> {
        shouldShowInventory = !shouldShowInventory;
        Node node = shouldShowInventory ? new Inventory() : new MenuOptions();
        middleSection.getChildren().clear();
        middleSection.getChildren().add(node);
        menuButtonHolder.getChildren().clear();
        menuButtonHolder.getChildren().add(getMenuButton());


    };

    /**
     * this is updated every time player moves through scenes
     * @param secs is used to show time
     */

    public void updateTimer(String secs) {
        timeRemaining.setText("Time remaining: " + secs);
    }

    /**
     * when clicking on side menu, it shows if you are on the invdentory or menu options screen
     * @return
     */
    private Button getMenuButton() {
        String icon = shouldShowInventory ? "=" : "x";
        Button button = shouldShowInventory ? new StandardButton(icon) : StandardButton.red(icon);
        button.setOnMouseClicked(toggleMenuOptionsInventory);
        return button;
    }
}
