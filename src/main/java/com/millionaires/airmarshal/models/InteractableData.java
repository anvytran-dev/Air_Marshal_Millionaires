package com.millionaires.airmarshal.models;

import com.millionaires.airmarshal.controller.ViewInterface;
import org.json.JSONObject;

/**
 * InteractableData objects are the objects that the player can interact with(click on) like the characters or items in the game.
 * Each object has a name, image path, and dialog
 */

public class InteractableData {

    private String name;
    private String imagePath;
    private double x;
    private double y;
    private String dialog;
    boolean isItem = false;

    public InteractableData(String name, String imagePath, double x, double y, String dialog) {
        this.name = name;
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.dialog = dialog;
    }

    public static InteractableData fromJson(JSONObject interactableData) {
        return new InteractableData(
                interactableData.getString("name"),
                interactableData.getString("image"),
                interactableData.getDouble("x"),
                interactableData.getDouble("y"),
                interactableData.getString("dialog")

        );
    }

    public String getPrettyName(){
        return ViewInterface.getInstance().getProperCase(getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return "file:" + imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public String getDialog() {
        return this.dialog;
    }

    public void setItem(boolean item) {
        isItem = item;
    }

    public boolean isItem() {
        return isItem;
    }
}
