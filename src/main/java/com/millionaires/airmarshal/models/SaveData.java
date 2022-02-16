package com.millionaires.airmarshal.models;

import org.json.JSONObject;

public class SaveData {

    private final JSONObject viewInterface, player, compartment;

    public SaveData(JSONObject viewInterface, JSONObject player, JSONObject compartment) {
        this.viewInterface = viewInterface;
        this.player = player;
        this.compartment = compartment;
    }

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

    public String getData() {
        JSONObject data = new JSONObject();
        data.put("viewInterface", viewInterface);
        data.put("player", player);
        data.put("compartment", compartment);
        return data.toString();
    }

    public JSONObject getCompartmentData() {
        return compartment;
    }

    public JSONObject getPlayerData() {
        return player;
    }

    public JSONObject getViewInterfaceData() {
        return viewInterface;
    }
}
