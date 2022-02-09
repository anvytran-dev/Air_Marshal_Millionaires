package com.millionaires.airmarshal;

import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.views.CompartmentView;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(new CompartmentView(new CompartmentData()), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}