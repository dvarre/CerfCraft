package fr.cerfcraft.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

public class Item {
    int id; // ok
    String name; //ok
    String image; //ok
    String type; // ok
    DocumentReference craft;
    Integer durability;// ok
    Integer damage; // ok
    //boolean hasCraft;
    //boolean hasDurability;
    //boolean canDamage;


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
