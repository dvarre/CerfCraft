package fr.cerfcraft.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Mob {
    Integer id; //ok
    String name; //ok
    String behaviour; //ok
    Integer health; //ok
    Integer damageEasy; //ok
    Integer damageNormal; //ok
    Integer damageHard; //ok
    Integer experience; //ok
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamageEasy() {
        return damageEasy;
    }

    public void setDamageEasy(Integer damageEasy) {
        this.damageEasy = damageEasy;
    }

    public Integer getDamageNormal() {
        return damageNormal;
    }

    public void setDamageNormal(Integer damageNormal) {
        this.damageNormal = damageNormal;
    }

    public Integer getDamageHard() {
        return damageHard;
    }

    public void setDamageHard(Integer damageHard) {
        this.damageHard = damageHard;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CollectionReference getLoots() {
        return loots;
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
}
