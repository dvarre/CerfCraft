package fr.cerfcraft.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Item {
    int id; // ok
    String name; //ok
    String image; //ok
    String type; // ok
    DocumentReference craft;
    Integer durability;// ok
    Integer damage; // ok
    String description;
    List<String> linksBiomes = new ArrayList<>();
    List<String> linksCrafts = new ArrayList<>();
    List<String> linksDimensions = new ArrayList<>();
    List<String> linksItems = new ArrayList<>();
    List<String> linksMissions = new ArrayList<>();
    List<String> linksMobs = new ArrayList<>();
    List<String> linksStructures = new ArrayList<>();
    //boolean hasCraft;
    //boolean hasDurability;
    //boolean canDamage;


    public Item(){

    }
    public Item(int id, String name, String image, String type, DocumentReference craft, Integer durability, Integer damage, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.craft = craft;
        this.durability = durability;
        this.damage = damage;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DocumentReference getCraft() {
        return craft;
    }

    public void setCraft(DocumentReference craft) {
        this.craft = craft;
    }

    public Integer getDurability() {
        return durability;
    }

    public void setDurability(Integer durability) {
        this.durability = durability;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
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

    //    public boolean isHasCraft() {
//        return hasCraft;
//    }
//
//    public void setHasCraft(boolean hasCraft) {
//        this.hasCraft = hasCraft;
//    }
//
//    public boolean isHasDurability() {
//        return hasDurability;
//    }
//
//    public void setHasDurability(boolean hasDurability) {
//        this.hasDurability = hasDurability;
//    }
//
//    public boolean isCanDamage() {
//        return canDamage;
//    }
//
//    public void setCanDamage(boolean canDamage) {
//        this.canDamage = canDamage;
//    }


}
