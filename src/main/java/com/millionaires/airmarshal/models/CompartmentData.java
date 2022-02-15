package com.millionaires.airmarshal.models;

        import com.millionaires.airmarshal.controller.ViewInterface;
        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class CompartmentData {

    private String name;
    private String backgroundUrl;
    private List<InteractableData> characters;
    private List<InteractableData> items;
    private Map<String, String> directions;


    public static CompartmentData fromJson(JSONObject room, String roomName){

        //convert list of characters to List<InteractableData>
        List<InteractableData> chars = new ArrayList<>();
        JSONArray listOfCharacters = room.getJSONArray("characters");

        for (Object character : listOfCharacters) {
            chars.add(InteractableData.fromJson((JSONObject) character));
        }

        //convert items
        List<InteractableData> items = new ArrayList<>();
        JSONArray listOfItems = room.getJSONArray("items");

        for (Object item : listOfItems) {
            InteractableData dataItem = InteractableData.fromJson((JSONObject) item);
            dataItem.setItem(true);
            items.add(dataItem);
        }

        Map<String, String> directions = new HashMap<>();
        JSONObject possibleDirections = room.getJSONObject("directions");

        for (String keyDirection : possibleDirections.keySet()) {
            directions.put(keyDirection, possibleDirections.getString(keyDirection));
        }

        return new CompartmentData(roomName,room.getString("backgroundUrl"), chars, items, directions);
    }


    public CompartmentData(String name, String backgroundUrl, List<InteractableData> characters, List<InteractableData> items, Map<String, String> directions){
        this.name = name;
        this.backgroundUrl = backgroundUrl;
        this.characters = characters;
        this.items = items;
        this.directions = directions;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public List<InteractableData> getCharacters() {
        return characters;
    }

    public void setCharacters(List<InteractableData> characters) {
        this.characters = characters;
    }

    public List<InteractableData> getItems() {
        return items;
    }

    public void setItems(List<InteractableData> items) {
        this.items = items;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public void setDirections(Map<String, String> directions) {
        this.directions = directions;
    }

    public String getNextCompartmentName(String direction) {
        return directions.get(direction);
    }

    public String getName() {
        return name;
    }

    public void removeItem(InteractableData itemToRemove) {
        this.items.removeIf(item -> itemToRemove.getName().equals(item.getName()));
    }
}

