package com.millionaires.airmarshal.models;

import org.json.JSONObject;

/**
 * JSON-serialized game states for the ViewInterface, Player, and CompartmentData.
 * Used to hold the read/write data for the SaveSystem
 */
public class SaveData {

    private final JSONObject viewInterface, player, compartment;

    public SaveData(JSONObject viewInterface, JSONObject player, JSONObject compartment) {
        this.viewInterface = viewInterface;
        this.player = player;
        this.compartment = compartment;
    }

    /**
     * Converts raw data string into a new SaveData object.
     * Parses the provided parameter as JSON and stores internally
     * @param saveDataRaw the data to be parsed into JSON. Should come from a valid save file
     * @return a new SaveData object holding the serialized game state
     * @throws Exception if unable to parse the parameter
     */
    public static SaveData fromRawSaveData(String saveDataRaw) throws Exception {
        try {
            JSONObject j = new JSONObject(saveDataRaw);
            JSONObject viewInterface = j.getJSONObject("viewInterface");
            JSONObject player = j.getJSONObject("player");
            JSONObject compartment = j.getJSONObject("compartment");
            return new SaveData(viewInterface, player, compartment);
        }catch(Exception e){
            String message = "Unable to ingest save data. The save file is probably not in valid JSON format.";
            throw new Exception(message);
        }
    }

    /**
     * Converts the data held in this class to a single string.
     * The output would typically be written to a file or sent to a JSONObject constructor
     * @return a string representation of the data held in this class formatted as JSON
     */
    public String getData() {
        JSONObject data = new JSONObject();
        data.put("viewInterface", viewInterface);
        data.put("player", player);
        data.put("compartment", compartment);
        return data.toString();
    }

    public JSONObject getPlayerData() {
        return player;
    }

    public JSONObject getViewInterfaceData() {
        return viewInterface;
    }
}
