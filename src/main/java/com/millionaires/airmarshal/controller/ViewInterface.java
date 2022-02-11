package com.millionaires.airmarshal.controller;


import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.models.Player;
import com.millionaires.airmarshal.views.CompartmentView;
import com.millionaires.airmarshal.views.GameView;
import javafx.scene.Scene;
import org.json.JSONObject;
import java.io.File;
import java.util.*;

public class ViewInterface {
    // Singleton

    private static final ViewInterface instance = new ViewInterface();
    private Scene scene;

    public static ViewInterface getInstance() {
        return instance;
    }

    // Class methods below
    private Map<String, CompartmentData> compartmentData = loadCompartmentData();
    private GameView gameView;

    private ViewInterface() {}

    private Map<String, CompartmentData> loadCompartmentData() {
        //[{}, {}]
        File file = new File("resources/room_data.json");

        StringBuilder jsonContent = new StringBuilder();

        // try with resources
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine())
                jsonContent.append(reader.nextLine());

        } catch (Exception e) {
            System.out.println("Error occurred while loading scenes: " + e);
        }

        JSONObject jsonData = new JSONObject(jsonContent.toString());

        JSONObject roomData = jsonData.getJSONObject("rooms");

        Map<String, CompartmentData> tempMap = new HashMap<>();

        for (String key : roomData.keySet()) {
            JSONObject room = roomData.getJSONObject(key);
            CompartmentData cd = CompartmentData.fromJson(room);
            tempMap.put(key, cd);
        }

        return tempMap;
    }


    public Map<String, CompartmentData> getRoomData() {
        return compartmentData;
    }

    public void startGame() {
        scene.setRoot(new GameView(new CompartmentView(getCompartmentData("commercial class"))));
    }

    public void loadGame() {

    }

    public void quitGame() {
        System.exit(0);
    }

    public String getInstructions() {
        return "These are instructions";
    }

    public CompartmentData goDirection(CompartmentData currentCompartment, String direction) {
        String nextCompartmentName = currentCompartment.getNextCompartmentName(direction);
        return compartmentData.get(nextCompartmentName);
    }

    public String talkTo(InteractableData character) {
        return "Hello. We have not implemented talking to characters yet.";
    }

    public void takeItem(InteractableData item) {

    }

    public void toggleMusic() {

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private CompartmentData getCompartmentData(String compartmentName){
        return compartmentData.get(compartmentName);
    }

    /**
     * Sets the player's name
     * @param name - the name to set on the player
     * @return true if setting name was successful, false if it was unsuccessful
     */
    public boolean setPlayerName(String name) {
        if(name == null || name.isEmpty())
            return false;

        Player.getInstance().setName(name);
        return true;
    }

    public String getPlayerName() {
        return Player.getInstance().getName();
    }
}
