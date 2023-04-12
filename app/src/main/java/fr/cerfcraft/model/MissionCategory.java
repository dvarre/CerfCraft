package fr.cerfcraft.model;

import com.google.firebase.firestore.CollectionReference;

import java.util.Collection;


public class MissionCategory {

    int id;
    String name;
    String image;
    CollectionReference missions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CollectionReference getMissions() {
        return missions;
    }

    public void setMissions(CollectionReference missions) {
        this.missions = missions;
    }
}
