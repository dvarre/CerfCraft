package fr.cerfcraft.model;

import java.util.ArrayList;
import java.util.List;

public class Mission {
    static public List<Mission> missionList = new ArrayList<>();
    String image;
    String name;
    String description;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mission(String image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public Mission() {
    }

    public static List<Mission> getMissionList() {
        return missionList;
    }

    public static void setMissionList(List<Mission> missionList) {
        Mission.missionList = missionList;
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
