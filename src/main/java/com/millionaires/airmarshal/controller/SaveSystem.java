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

public class SaveSystem {

    private static final String SAVE_DIRECTORY = "saves";

    public static List<String> getSaves() {
        List<String> fileNames = new ArrayList<>();
        File directory = new File(SAVE_DIRECTORY);

        if(!directory.exists())
            return fileNames;

        for (File f : Objects.requireNonNull(directory.listFiles()))
            fileNames.add(f.getName());

        return fileNames;
    }

    public static SaveData loadGame(String fileName) throws Exception {
        String saveDataRaw = Files.readString(Path.of(getFullPath(fileName)));
        return SaveData.fromRawSaveData(saveDataRaw);
    }

    public static void saveGame() throws Exception {
        SaveData saveData = ViewInterface.getInstance().getSaveData();
        String fileName = "air-marshall-save-" + System.currentTimeMillis() + ".json";
        saveToFile(saveData.getData(), fileName);
    }

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

    public static void deleteSave(String fileName) {
        File f = new File(fileName);
        f.delete();
    }
}
