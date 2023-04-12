package fr.cerfcraft.DAO;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Map;

import fr.cerfcraft.model.Mission;

public class BasicDAO {

/*    public Map<String, Object> getDocumentsFromBD(DocumentReference docRef){
        final Map<String, Object>[] map = new Map[]{null};
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        map[0] = document.getData();
                        for (Map.Entry<?,?> entry : map[0].entrySet())
                            System.out.println(entry.getValue());
                            MissionDAO missionDAO = new MissionDAO();
                            missionDAO.HashToMissionObject(map[0]);
                        System.out.println("MAP ICIIIIIIIII " + map[0].values());

                    } else {
                        Log.d(TAG, "This document does not exist: " );
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        System.out.println("Avant de sortir la map vaut : " + map[0]);
        return map[0];
    }*/
}
