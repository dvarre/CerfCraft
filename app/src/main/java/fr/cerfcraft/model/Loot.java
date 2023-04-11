package fr.cerfcraft.model;

import com.google.firebase.firestore.DocumentReference;

public class Loot {
    DocumentReference item;
    String quantity;
    float probability;

    public DocumentReference getItem() {
        return item;
    }

    public void setItem(DocumentReference item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }
}
