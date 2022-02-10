package com.millionaires.airmarshal.controller;


import com.millionaires.airmarshal.models.CompartmentData;
import com.millionaires.airmarshal.models.InteractableData;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.text.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.*;

public class ViewInterface {
    private static final ViewInterface instance = new ViewInterface();

    private Map<String, CompartmentData> compartmentData;

    private ViewInterface() {

            compartmentData = loadCompartmentData();

    }

    private Map<String,CompartmentData> loadCompartmentData()  {
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

        for(String key : roomData.keySet()) {
            JSONObject room = roomData.getJSONObject(key);

            //convert list of characters to List<InteractableData>
            List<InteractableData> chars = new ArrayList<>();
            JSONArray listOfCharacters = room.getJSONArray("characters");

            for(Object character : listOfCharacters) {
                JSONObject charJSON = (JSONObject) character;
                InteractableData person = new InteractableData(charJSON.getString("name"), charJSON.getString("image"), charJSON.getDouble("x"), charJSON.getDouble("y"));

                chars.add(person);
            }

            //convert items
            List<InteractableData> items = new ArrayList<>();
            JSONArray listOfItems = room.getJSONArray("items");

            for(Object item : listOfItems) {
                JSONObject itemJSON = (JSONObject) item;
                InteractableData itemInRoom = new InteractableData(itemJSON.getString("name"), itemJSON.getString("image"), itemJSON.getDouble("x"), itemJSON.getDouble("y"));
                System.out.println(itemJSON.get("image"));
                items.add(itemInRoom);
            }


            Map<String, String> directions = new HashMap<>();
            JSONObject possibleDirections = room.getJSONObject("directions");

            for(String keyDirection: possibleDirections.keySet()) {
                directions.put(keyDirection, possibleDirections.getString(keyDirection));
            }

            CompartmentData cd = new CompartmentData(room.getString("backgroundUrl"), chars, items, directions);

            tempMap.put(key, cd);
        }

        return tempMap;
    }

    public static ViewInterface getInstance() {
        return instance;
    }

    public Map<String, CompartmentData> getRoomData() {
        return compartmentData;
    }
}
