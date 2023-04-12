package fr.cerfcraft.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Mob {
    int id; //ok
    String name; //ok
    String behaviour; //ok
    int health; //ok
    int damageEasy; //ok
    int damageNormal; //ok
    int damageHard; //ok
    int experience; //ok
    String image; //ok
    //String description;
    //Collection<Loot> loots;
    CollectionReference loots;
    List<String> linksBiomes = new ArrayList<>();
    List<String> linksCrafts = new ArrayList<>();
    List<String> linksDimensions = new ArrayList<>();
    List<String> linksItems = new ArrayList<>();
    List<String> linksMissions = new ArrayList<>();
    List<String> linksMobs = new ArrayList<>();
    List<String> linksStructures = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamageEasy(int damageEasy) {
        this.damageEasy = damageEasy;
    }

    public void setDamageNormal(int damageNormal) {
        this.damageNormal = damageNormal;
    }

    public void setDamageHard(int damageHard) {
        this.damageHard = damageHard;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLoots(CollectionReference loots) {
        this.loots = loots;
    }

    public List<String> getLinksBiomes() {
        return linksBiomes;
    }

    public void setLinksBiomes(List<String> linksBiomes) {
        this.linksBiomes = linksBiomes;
    }

    public List<String> getLinksCrafts() {
        return linksCrafts;
    }

    public void setLinksCrafts(List<String> linksCrafts) {
        this.linksCrafts = linksCrafts;
    }

    public List<String> getLinksDimensions() {
        return linksDimensions;
    }

    public void setLinksDimensions(List<String> linksDimensions) {
        this.linksDimensions = linksDimensions;
    }

    public List<String> getLinksItems() {
        return linksItems;
    }

    public void setLinksItems(List<String> linksItems) {
        this.linksItems = linksItems;
    }

    public List<String> getLinksMissions() {
        return linksMissions;
    }

    public void setLinksMissions(List<String> linksMissions) {
        this.linksMissions = linksMissions;
    }

    public List<String> getLinksMobs() {
        return linksMobs;
    }

    public void setLinksMobs(List<String> linksMobs) {
        this.linksMobs = linksMobs;
    }

    public List<String> getLinksStructures() {
        return linksStructures;
    }

    public void setLinksStructures(List<String> linksStructures) {
        this.linksStructures = linksStructures;
    }

    //    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public int getHealth() {
        return health;
    }

    public int getDamageEasy() {
        return damageEasy;
    }

    public int getDamageNormal() {
        return damageNormal;
    }

    public int getDamageHard() {
        return damageHard;
    }

    public int getExperience() {
        return experience;
    }

    public String getImage() {
        return image;
    }

    public CollectionReference getLoots() {
        return loots;
    }

//    public Collection<Loot> getLoots() {
//        return loots;
//    }
}
