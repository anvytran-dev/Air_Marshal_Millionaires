package com.millionaires.airmarshal.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompartmentData {

    private String backgroundUrl;
    private List<InteractableData> characters;
    private List<InteractableData> items;
    private Map<String, String> directions;

    public CompartmentData(){
        backgroundUrl = "https://theawesomedaily.com/wp-content/uploads/2018/07/cool-backgrounds-feat-1.jpg";
        characters = List.of(
              new InteractableData()
        );
        items = List.of(
               new InteractableData()
        );
        directions = Map.of("forward", "first class");
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
}
