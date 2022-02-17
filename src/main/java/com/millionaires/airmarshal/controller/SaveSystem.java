package com.millionaires.airmarshal.controller;

import com.millionaires.airmarshal.models.SaveData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contains static methods to execute game loading and saving.
 */
public class SaveSystem {

    private static final String SAVE_DIRECTORY = "saves";

    /**
     * Searches for, compiles, and returns the names of save files in the save directory.
     * @return ArrayList of the names of the files found in the save directory.
     * If the save directory does not exist, will return an empty ArrayList.
     */
    public static List<String> getSaves() {
        List<String> fileNames = new ArrayList<>();
        File directory = new File(SAVE_DIRECTORY);

        if(!directory.exists())
            return fileNames;

        for (File f : Objects.requireNonNull(directory.listFiles()))
            fileNames.add(f.getName());

        return fileNames;
    }

    /**
     * Searches for and loads the save file corresponding to the provided fileName parameter.
     * @param fileName the name of the save file to load
     * @return SaveData object containing the parsed save file data
     * @throws Exception if the file is not found or cannot be parsed
     */
    public static SaveData loadGame(String fileName) throws Exception {
        String saveDataRaw = Files.readString(Path.of(getFullPath(fileName)));
        return SaveData.fromRawSaveData(saveDataRaw);
    }

    /**
     * Saves the current state of the game as a new file using the current
     * system time to aid in filename uniqueness
     * @throws Exception if writing to the system fails
     */
    public static void saveGame() throws Exception {
        SaveData saveData = ViewInterface.getInstance().getSaveData();
        String fileName = "air-marshall-save-" + System.currentTimeMillis() + ".json";
        saveToFile(saveData.getData(), fileName);
    }

    /**
     * Saves the provided save data as a file with the provided file name
     * @param saveDataJsonString the data to write to the save file
     * @param fileName the desired name of the save
     * @throws Exception if an error occurs during the save
     */
    private static void saveToFile(String saveDataJsonString, String fileName) throws Exception {
        File directory = new File(SAVE_DIRECTORY);
        if (!directory.exists())
            directory.mkdir();

        File file = new File(getFullPath(fileName));
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(saveDataJsonString);
        bw.close();
    }

    private static String getFullPath(String fileName) {
        return SAVE_DIRECTORY + File.separator + fileName;
    }
}
