package fr.cerfcraft.model;

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
    //Collection<Loot> loots;

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

    //    public Collection<Loot> getLoots() {
//        return loots;
//    }
}
