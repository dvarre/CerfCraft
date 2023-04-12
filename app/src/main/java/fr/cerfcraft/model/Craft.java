package fr.cerfcraft.model;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Craft {

    int id;
    DocumentReference result;
    List<DocumentReference> slots = new ArrayList<>();
    String name;
    String image;
    String description;
    List<String> linksBiomes = new ArrayList<>();
    List<String> linksCrafts = new ArrayList<>();
    List<String> linksDimensions = new ArrayList<>();
    List<String> linksItems = new ArrayList<>();
    List<String> linksMissions = new ArrayList<>();
    List<String> linksMobs = new ArrayList<>();
    List<String> linksStructures = new ArrayList<>();

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

    public void setResult(DocumentReference result) {
        this.result = result;
    }

    public List<DocumentReference> getSlots() {
        return slots;
    }

    public void setSlots(List<DocumentReference> slots) {
        this.slots = slots;
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

    public DocumentReference getResult() {
        return result;
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

    //numSlot between 0 and 8
//    public Item getSlot(int numSlot){
//        if((numSlot>=0) && (numSlot<9)){
//            return slots[numSlot];
//        }
//        return null;
//    }
}
