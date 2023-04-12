package fr.cerfcraft.model;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Dimension {
    List<DocumentReference> biomes = new ArrayList<>();
    String description;
    int id;
    String image;
    String name;

    public List<DocumentReference> getBiomes() {
        return biomes;
    }

    public void setBiomes(List<DocumentReference> biomes) {
        this.biomes = biomes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
