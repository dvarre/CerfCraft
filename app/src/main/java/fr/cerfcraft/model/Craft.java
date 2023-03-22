package fr.cerfcraft.model;

public class Craft {

    Item result;
    Item[] slots = new Item[9];

    public Item getResult() {
        return result;
    }

    //numSlot between 0 and 8
    public Item getSlot(int numSlot){
        if((numSlot>=0) && (numSlot<9)){
            return slots[numSlot];
        }
        return null;
    }
}
