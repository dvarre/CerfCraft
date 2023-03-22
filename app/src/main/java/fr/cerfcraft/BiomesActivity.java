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
import fr.cerfcraft.model.Biome;

public class BiomesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CollectionReference biomeReference = FirebaseFirestore.getInstance().collection("biomes");
    BiomeAdapter biomeAdapter;
    List<Biome> biomeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biomes);

        recyclerView = findViewById(R.id.biome_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        biomeList = new ArrayList<>();
        biomeAdapter = new BiomeAdapter(this,biomeList);

        biomeReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                    biomeList.add(snapshot.toObject(Biome.class));
                }
                recyclerView.setAdapter(biomeAdapter);
            }

        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"On Failure : ", e);
                    }
                });


    }


}