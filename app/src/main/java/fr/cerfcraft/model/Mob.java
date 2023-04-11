package fr.cerfcraft.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.Collection;

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
