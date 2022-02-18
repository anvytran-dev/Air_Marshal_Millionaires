package com.millionaires.airmarshal.views.components;

import com.millionaires.airmarshal.controller.ViewInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Save menu to view and select a save file
 */
public class SaveLoader extends StackPane {
    private final double WIDTH = 600;
    private final double HEIGHT = 250;
    private final VBox scrollContent = new VBox();

    /**
     * Renders save files and allows the player to select one to load or go to the previous screen
     *
     * @param onBackBtnClicked a callback to go back to the previous view
     */
    public SaveLoader(EventHandler<ActionEvent> onBackBtnClicked) {
        super();

        // Back button
        StandardButton backBtn = StandardButton.red("Back", onBackBtnClicked);

        // Gets the savegame file names and builds SaveFileRenderers to be shown
        List<String> saveNames = ViewInterface.getInstance().getSaveNames();
        List<SaveFileRenderer> saveFileRenderers = getSaveFileRenderers(saveNames);

        // Add the save file renderers to the scrollable. Set display preferences
        scrollContent.getChildren().addAll(saveFileRenderers);
        ScrollPane saveFileScroller = new ScrollPane(scrollContent);
        saveFileScroller.setPrefHeight(HEIGHT);
        saveFileScroller.setFitToWidth(true);

        // Label to let user know what they are seeing
        Label label = new Label(String.format("Saved Games (%d)", saveNames.size()));
        label.setFont(Font.font(20));
        label.setTextFill(Color.WHITE);

        // Add render object to a VBox. This VBox will be placed inside the scroller
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

        // Add the renderers to this object for display
        getChildren().addAll(box, contents);
        setMaxHeight(HEIGHT);
    }

    /**
     * Builds renderers to display the provided names
     *
     * @param names the list of names to build renderers for
     * @return a list of SaveFileRenderer, each containing a name from the provided list
     */
    private List<SaveFileRenderer> getSaveFileRenderers(List<String> names) {
        List<SaveFileRenderer> renderers = new ArrayList<>();
        for (String save : names)
            renderers.add(new SaveFileRenderer(save));

        return renderers;
    }

    /**
     * A renderer to display the save file in a user-friendly way
     */
    private class SaveFileRenderer extends HBox {

        /**
         * Displays a string as a button with the ability to load a save file into the game
         *
         * @param name the name of the save file
         */
        private SaveFileRenderer(String name) {
            super();
            StandardButton button = new StandardButton(getDateString(name), loadSave(name));
            button.setPrefWidth(WIDTH);
            getChildren().add(button);
        }

        /**
         * Utility to get a string representing the date of the save file
         *
         * @param name a valid save file name to be processed and converted to a ISO-standard date format
         * @return ISO-standard date string. If the provided file is unparseable, returns "Invalid save file"
         */
        private String getDateString(String name) {
            try {
                String[] splitString = name.split("-");
                String millis = splitString[3].split("\\.")[0];
                Date d = new Date(Long.parseLong(millis));
                return d.toString();
            }catch(Exception e){
                return "Invalid save file";
            }
        }

        /**
         * Handler to load the provided fileName as a save file. If unable to load the game,
         * prints error message to the console
         * @param fileName the name of the save file to print
         * @return an EventHandler which tries to load the game
         */
        private EventHandler<ActionEvent> loadSave(String fileName) {
            return e -> {
                String errorMsg = ViewInterface.getInstance().loadGame(fileName);
                if (errorMsg != null)
                    System.out.println(errorMsg);

            };
        }
    }
}