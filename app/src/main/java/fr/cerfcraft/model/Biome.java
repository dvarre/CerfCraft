package fr.cerfcraft.model;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Biome {

    int id;
    String name;
    String image;
    String type;
    String rarity;
    float temperature;
    String description;
    List<DocumentReference> links = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    public float getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public List<DocumentReference> getLinks() {
        return links;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLinks(List<DocumentReference> links) {
        this.links = links;
    }
}
