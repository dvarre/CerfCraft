package fr.cerfcraft;

import com.google.firebase.database.Transaction;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.model.Item;

public class FixBugRecyclerView {
    public static List<Object> aucuneIdeeList = new ArrayList<>();

    public static List<Object> getAucuneIdeeList() {
        return aucuneIdeeList;
    }

    public static void setAucuneIdeeList(List<Object> aucuneIdeeList) {
        FixBugRecyclerView.aucuneIdeeList = aucuneIdeeList;
    }

    public void ReturnObectFromBDD(Item doc){
        System.out.println("doc = " + doc);
        Item obj = doc;
        System.out.println("obj = " + obj.getId());
        aucuneIdeeList.add(obj);
    }
}
