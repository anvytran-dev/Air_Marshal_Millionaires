package com.millionaires.airmarshal.models;

public class InteractableData {

    private String name;
    private String imagePath;
    private double x;
    private double y;

    public InteractableData() {
        name = "teacher";
        imagePath = "https://www.bethesdaheadshots.com/wp-content/uploads/2020/02/Jonathan-Business-Headshot.jpg";
        x = 0;
        y = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
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
