package com.example.ass1_mohammad_1191413;

import java.io.Serializable;

public class Tile implements Serializable {
    private String type;
    private String size;
    private String color;
    private String description;

    private String imagePath;


    public Tile(String type, String size, String color, String description,String imagePath) {
        this.type = type;
        this.size = size;
        this.color = color;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", img Path=" + imagePath +
                '}';
    }



}
