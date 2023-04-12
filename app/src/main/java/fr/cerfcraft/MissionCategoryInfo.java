package fr.cerfcraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.MissionAdapter;
import fr.cerfcraft.model.Mission;
import fr.cerfcraft.model.MissionCategory;

public class MissionCategoryInfo extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("missions");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_category_info);

        String id ="";
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }
        }

        TextView txtView = findViewById(R.id.mission_category_title);
        Context ctx = this;

        ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    MissionCategory missionCategory = task.getResult().toObject(MissionCategory.class);
                    String missionsPath = "";
                    if(missionCategory.getId() == 1){
                        missionsPath = "architect";
                    }else if(missionCategory.getId() == 2){
                        missionsPath = "adventure";
                    }
                    missionCategory.setMissions(task.getResult().getReference().collection(missionsPath));
                    txtView.setText(missionCategory.getName());
                    RecyclerView recyclerView = findViewById(R.id.mission_list);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                    missionCategory.getMissions().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Mission> missionList = new ArrayList<>();
                            for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                                missionList.add(snapshot.toObject(Mission.class));
                            }
                            MissionAdapter missionAdapter = new MissionAdapter(ctx,missionList);
                            recyclerView.setAdapter(missionAdapter);
                        }
                    });
                }
                else{
                    txtView.setText("");
                }
            }
        });
    }
}