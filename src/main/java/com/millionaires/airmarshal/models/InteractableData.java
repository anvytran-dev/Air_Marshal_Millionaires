package com.millionaires.airmarshal.models;

import org.json.JSONObject;

public class InteractableData {

    private String name;
    private String imagePath;
    private double x;
    private double y;

    public InteractableData(String name, String imagePath, double x, double y) {
        this.name = name;
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
    }

    public InteractableData() {

    }

    public static InteractableData fromJson(JSONObject interactableData) {
        return new InteractableData(
                interactableData.getString("name"),
                interactableData.getString("image"),
                interactableData.getDouble("x"),
                interactableData.getDouble("y")
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        System.out.println(imagePath);
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


}
