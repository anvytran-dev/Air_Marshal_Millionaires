package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveLoader extends StackPane {
    private final double WIDTH = 600;
    private final double HEIGHT = 250;
    private final VBox scrollContent = new VBox();

    public SaveLoader(EventHandler<ActionEvent> showMainMenuButtons) {
        super();

        StandardButton backBtn = StandardButton.red("Back", showMainMenuButtons);
        List<String> saveNames = ViewInterface.getInstance().getSaveNames();
        List<SaveFileRenderer> saveFileRenderers = getSaveFileRenderers(saveNames);

        scrollContent.getChildren().addAll(saveFileRenderers);
        ScrollPane saveFileScroller = new ScrollPane(scrollContent);
        saveFileScroller.setPrefHeight(HEIGHT);
        saveFileScroller.setFitToWidth(true);

        Label label = new Label(String.format("Saved Games (%d)", saveNames.size()));
        label.setFont(Font.font(20));
        label.setTextFill(Color.WHITE);

        VBox contents = new VBox(10);
        contents.getChildren().addAll(label, saveFileScroller, backBtn);
        contents.setMaxWidth(WIDTH);
        contents.setAlignment(Pos.CENTER);

        // Create a backdrop to more easily see the content
        Rectangle box = new Rectangle(WIDTH + 150, HEIGHT + 150, Color.BLACK);
        box.setArcWidth(10.0);
        box.setArcHeight(10.0);
        box.setStrokeWidth(2);
        box.setOpacity(.5);

        getChildren().addAll(box, contents);
        setMaxHeight(HEIGHT);
    }

    private List<SaveFileRenderer> getSaveFileRenderers(List<String> names) {
        List<SaveFileRenderer> renderers = new ArrayList<>();
        for (String save : names)
            renderers.add(new SaveFileRenderer(save));

        return renderers;
    }

    private class SaveFileRenderer extends HBox {
        private SaveFileRenderer(String name) {
            super();
            StandardButton button = new StandardButton(getDateString(name), loadSave(name));
            button.setPrefWidth(WIDTH);
            getChildren().add(button);
        }

        private String getDateString(String name) {
            String[] splitString = name.split("-");
            String millis = splitString[3].split("\\.")[0];
            Date d = new Date(Long.parseLong(millis));
            return d.toString();
        }

        EventHandler<ActionEvent> loadSave(String fileName) {
            return e -> {
                String errorMsg = ViewInterface.getInstance().loadGame(fileName);
                if (errorMsg != null)
                    System.out.println(errorMsg);

            };
        }
    }

}
