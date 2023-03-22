package fr.cerfcraft.model;

import java.util.Collection;

public class Mob {
    int id;
    String name;
    String behaviour;
    int health;
    int damageEasy;
    int damageNormal;
    int damageHard;
    int experience;
    Collection<Loot> loots;

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

    public Collection<Loot> getLoots() {
        return loots;
    }
}
