package fr.cerfcraft.model;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    String name;
    String description;

    String image;

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mission(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Mission() {
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
