package fr.cerfcraft.model;

import com.google.firebase.firestore.DocumentReference;

public class Craft {

    int id;
    DocumentReference result;
    DocumentReference[] slots = new DocumentReference[9];
    String name;
    String image;

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
