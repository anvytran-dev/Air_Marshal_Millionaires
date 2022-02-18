package com.millionaires.airmarshal;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.views.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Runs the JavaFX application and provides the scene to the ViewInterface
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new MainMenuView());
        stage.setTitle("Air Marshall - Millionaires");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        ViewInterface.getInstance().setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}