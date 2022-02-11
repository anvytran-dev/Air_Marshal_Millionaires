package com.millionaires.airmarshal;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.views.CompartmentView;
import com.millionaires.airmarshal.views.GameView;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CompartmentData cd = ViewInterface.getInstance().getRoomData().get("cockpit");
        Scene scene = new Scene(new GameView(new CompartmentView(cd)));
//        Scene scene = new Scene(new MainMenuView());
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