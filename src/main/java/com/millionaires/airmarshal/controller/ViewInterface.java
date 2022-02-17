package com.millionaires.airmarshal.controller;


import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import com.millionaires.airmarshal.models.Player;
import com.millionaires.airmarshal.models.SaveData;
import com.millionaires.airmarshal.views.CompartmentView;
import com.millionaires.airmarshal.views.GameOverView;
import com.millionaires.airmarshal.views.GameView;
import com.millionaires.airmarshal.views.MainMenuView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    //        Duration duration = Duration.ofSeconds(5L);
    Duration duration = Duration.ofMinutes(5L);

    Timeline oneSecondCountdown = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            String secs = ViewInterface.getInstance().subtractTime();
            gameView.updateTimer(secs);

            if (secs.equals("0")) {
                scene.setRoot(new GameOverView(false));
                oneSecondCountdown.stop();
            }
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
        toggleMusic();
    }

    private void setCompartment() {
        scene.setRoot(new GameView(new CompartmentView(currentCompartment)));
    }

    public String loadGame(String fileName) {
        try {
            SaveData saveData = SaveSystem.loadGame(fileName);

            JSONObject playerSave = saveData.getPlayerData();
            Player.getInstance().setNameAndTransferItemsFromCompartmentsToPlayer(playerSave);

            JSONObject viewInterfaceSave = saveData.getViewInterfaceData();
            duration = Duration.ofSeconds(viewInterfaceSave.getLong("timeRemaining"));
            currentCompartment = getCompartmentData(viewInterfaceSave.getString("currentCompartment"));

            startGame();

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
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
        if (nextCompartmentName.equals("cockpit") && !Player.getInstance().canAccessCockpit()) {
            showDialogBox("STEWARDESS: Only passengers with tour posters are allowed to enter");
            return;
        }

        if (nextCompartmentName.equals("galley") && !Player.getInstance().canAccessGalley()) {
            showDialogBox("I shouldn't venture too far without knowing my way around");
            return;
        }

        if (nextCompartmentName.equals("cargo") && !Player.getInstance().canAccessCargo()) {
            showDialogBox("The cargo room is locked. I wonder who would have a key...");
            return;
        }

        this.currentCompartment = compartmentData.get(nextCompartmentName);
        setCompartment();
    }

    public void takeItem(InteractableData item) {
        Player.getInstance().getInventory().remove(item);

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
        String n = currentCompartment.getName();
        return getProperCase(n);
    }

    public String getProperCase(String toProcess) {
        String[] words = toProcess.split(" ");
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < words.length; i++) {
            if (i > 0)
                output.append(" ");

            String word = words[i];
            char c = word.toCharArray()[0];
            String s = Character.toString(c).toUpperCase();
            String wordWithoutFirstLetter = word.substring(1);
            s += wordWithoutFirstLetter;
            output.append(s);
        }

        return output.toString();
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
        while (duration.isNegative()) {
            return "";
        }
        duration = duration.minusSeconds(1);
        return getRemainingTime();
    }

    public String getRemainingTime() {
        return duration.getSeconds() + "";
    }

    public void addItemWithoutUpdatingView(InteractableData item) {
        currentCompartment.removeItem(item);
        Player.getInstance().addItemToInventory(item);
    }

    public void addItem(InteractableData item) {
        addItemWithoutUpdatingView(item);
        setCompartment();
    }

    public List<InteractableData> getPlayerInventory() {
        return Player.getInstance().getInventory();
    }

    public void winGame() {

        scene.setRoot(new GameOverView(true));
    }

    public void getMainMenu() {
        scene.setRoot(new MainMenuView());
    }

    public SaveData getSaveData() {
        JSONObject viewInterfaceData = new JSONObject();
        viewInterfaceData.put("currentCompartment", currentCompartment.getName());
        viewInterfaceData.put("timeRemaining", duration.getSeconds());
        viewInterfaceData.put("dateTimeSaved", System.currentTimeMillis());

        JSONObject compartmentData = new JSONObject();
        for (String compartmentName : this.compartmentData.keySet())
            compartmentData.put(compartmentName, this.compartmentData.get(compartmentName).serialize());

        JSONObject playerData = Player.getInstance().serialize();

        return new SaveData(viewInterfaceData, playerData, compartmentData);
    }

    private static final String os = System.getProperty("os.name").toLowerCase();

    public void restartGame() {
        try {
            if (os.contains("windows")) {
                Runtime.getRuntime().exec("run.bat");
            } else {
                Runtime.getRuntime().exec("run.sh");
            }
            quitGame();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public boolean savesExist() {
        return !SaveSystem.getSaves().isEmpty();
    }

    public List<String> getSaveNames() {
        return SaveSystem.getSaves();
    }

    public void saveGame() {
        try {
            SaveSystem.saveGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toggleMusic() {
        try {
            Music.toggle();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to play audio";
        }
    }

    public void showHelpMenu() {
        gameView.showHelpMenu();
    }
}
