package com.millionaires.airmarshal.controller;

import com.millionaires.airmarshal.models.SaveData;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SaveSystemTest {

    @Test
    public void saveFileTest() throws Exception {
        SaveSystem.saveGame();
    }

    @Test
    public void loadFileTest() throws Exception {
        SaveData data = SaveSystem.loadGame("air-marshall-save-1645034698871.json");
        System.out.println(data.getData());
    }

    @Test
    public void testGetAllSaves() {
        List<String> saveNames = SaveSystem.getSaves();
        System.out.println(saveNames);
    }
}
