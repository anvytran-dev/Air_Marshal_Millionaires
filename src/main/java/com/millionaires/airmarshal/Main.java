package com.millionaires.airmarshal;

import com.millionaires.airmarshal.controller.ViewInterface;
import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.views.CompartmentView;
import com.millionaires.airmarshal.views.MainMenuView;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        SideMenu menu = new SideMenu();
//        menu.setPrefWidth(650);
//        menu.setSpacing(200);
//        menu.setBorder(Border.EMPTY);
//        menu.setAlignment(Pos.TOP_CENTER);
//        CompartmentData cd = ViewInterface.getInstance().getRoomData().get("commercial class");
//        HBox row = new HBox(new CompartmentView(cd),menu);

//        Scene scene = new Scene(new CompartmentView(cd), 320, 240);
//        Scene scene = new Scene(row, 420, 340);
//        CompartmentData cd = ViewInterface.getInstance().getRoomData().get("commercial class");
//
//        System.out.println(ViewInterface.getInstance().getRoomData().get("commercial class").getItems().size());

        Scene scene = new Scene(new MainMenuView());

        System.out.println(ViewInterface.getInstance().getRoomData());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}