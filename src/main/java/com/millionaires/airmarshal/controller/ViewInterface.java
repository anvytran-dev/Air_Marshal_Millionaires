package com.millionaires.airmarshal.controller;


import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.models.Player;
import com.millionaires.airmarshal.views.CompartmentView;
import com.millionaires.airmarshal.views.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.*;

public class ViewInterface {
    // Singleton

    private static final ViewInterface instance = new ViewInterface();

    public static ViewInterface getInstance() {
        return instance;
    }

    // Class methods below
    private Map<String, CompartmentData> compartmentData = loadCompartmentData();
    private GameView gameView;
    private Scene scene;
    private CompartmentData currentCompartment = getCompartmentData("commercial class");
    Duration duration = Duration.ofMinutes(5L);

    Timeline oneSecondCountdown = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            String secs = ViewInterface.getInstance().subtractTime();
            System.out.println(secs);
            gameView.updateTimer(secs);
        }
    }));

    private ViewInterface() {
    }

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
            CompartmentData cd = CompartmentData.fromJson(room, key);
            tempMap.put(key, cd);
        }

        return tempMap;
    }


    public Map<String, CompartmentData> getRoomData() {
        return compartmentData;
    }

    public void startGame() {
        setCompartment();
        oneSecondCountdown.setCycleCount(Timeline.INDEFINITE);
        oneSecondCountdown.play();
    }

    private void setCompartment() {
        scene.setRoot(new GameView(new CompartmentView(currentCompartment)));
    }

    public void loadGame() {

    }

    public void quitGame() {
        System.exit(0);
    }

    public String getInstructions() {
        try {
            List<String> insts = Files.readAllLines(Path.of("resources/data/game_instructions.txt"));
            return String.join("\n", insts);

        } catch (Exception e) {
            System.out.println("Caught error while trying to read instructions");
            return "Unable to read instructions";
        }

    }

    public void goDirection(String direction) {

        String nextCompartmentName = currentCompartment.getNextCompartmentName(direction);
        if(nextCompartmentName.equals("cockpit") && !Player.getInstance().canAccessCockpit()){
            showDialogBox("STEWARDESS: Only passengers with tour posters are allowed to enter");
            return;
        }

        if(nextCompartmentName.equals("galley") && !Player.getInstance().canAccessGalley()){
            showDialogBox("I shouldn't venture too far without knowing my way around");
            return;
        }

        if(nextCompartmentName.equals("cargo") && !Player.getInstance().canAccessCargo()){
            showDialogBox("The cargo room is locked. I wonder who would have a key...");
            return;
        }

        this.currentCompartment = compartmentData.get(nextCompartmentName);
        setCompartment();
    }

    public void takeItem(InteractableData item) {
        Player.getInstance().getInventory().remove(item);

    }

    public void toggleMusic() {

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private CompartmentData getCompartmentData(String compartmentName) {
        return compartmentData.get(compartmentName);
    }

    /**
     * Sets the player's name
     *
     * @param name - the name to set on the player
     * @return true if setting name was successful, false if it was unsuccessful
     */
    public boolean setPlayerName(String name) {
        if (name == null || name.isEmpty())
            return false;

        Player.getInstance().setName(name);
        return true;
    }

    public String getPlayerName() {
        return Player.getInstance().getName();
    }

    public String[] getAvailableCompartmentDirections() {
        return currentCompartment.getDirections().keySet().toArray(new String[0]);
    }

    public String getCompartmentName() {
        return currentCompartment.getName();
    }

    public void setGameView(GameView gv) {
        this.gameView = gv;
    }

    public void showDialogBox(String s) {
        gameView.setDialogText(s);
        gameView.setDialogVisible(true);
    }

    public void dismissDialog() {
        gameView.setDialogVisible(false);
    }


    public String subtractTime() {
        duration = duration.minusSeconds(1);
        return getRemainingTime();
    }

    public String getRemainingTime() {
        return duration.getSeconds() + "";
    }
    public void addItem(InteractableData item){
        currentCompartment.removeItem(item);
        Player.getInstance().addItemToInventory(item);
        setCompartment();
    }
    public List<InteractableData> getPlayerInventory(){
        return Player.getInstance().getInventory();
    }

}
