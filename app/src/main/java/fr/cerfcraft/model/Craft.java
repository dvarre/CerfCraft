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

//numSlot between 0 and 8
//    public Item getSlot(int numSlot){
//        if((numSlot>=0) && (numSlot<9)){
//            return slots[numSlot];
//        }
//        return null;
//    }
}
