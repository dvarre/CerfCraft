package fr.cerfcraft;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.MissionCategoryAdapter;
import fr.cerfcraft.model.MissionCategory;

public class MissionCategoriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CollectionReference missionReference = FirebaseFirestore.getInstance().collection("missions");
    MissionCategoryAdapter missionAdapter;
    List<MissionCategory> missionList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_categories);

        recyclerView = findViewById(R.id.mission_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        missionList = new ArrayList<>();
        missionAdapter = new MissionCategoryAdapter(this, missionList);

        missionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                            missionList.add(snapshot.toObject(MissionCategory.class));
                        }
                        recyclerView.setAdapter(missionAdapter);
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"On Failure : ", e);
                    }
                });


    }
}