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

import fr.cerfcraft.adapter.BiomeAdapter;
import fr.cerfcraft.adapter.MobAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Mob;

public class MobsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CollectionReference mobReference = FirebaseFirestore.getInstance().collection("mobs");
    MobAdapter mobAdapter;
    List<Mob> mobList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobs);

        recyclerView = findViewById(R.id.mob_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mobAdapter = new MobAdapter(this, mobList);

        mobReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                            mobList.add(snapshot.toObject(Mob.class));
                        }
                        recyclerView.setAdapter(mobAdapter);
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"On Failure : ", e);
                    }
                });
    }
}
