package fr.cerfcraft;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.BiomeAdapter;
import fr.cerfcraft.model.Biome;

public class BiomesActivity extends AppCompatActivity {
    private Toolbar toolbar;

    RecyclerView recyclerView;
    CollectionReference biomeReference = FirebaseFirestore.getInstance().collection("biomes");
    BiomeAdapter biomeAdapter;
    List<Biome> biomeList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref = db.collection("biomes").document("data");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biomes);

        toolbar=findViewById((R.id.include_biomes));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    //Pour mettre à jour les données automatiquement
//    @Override
//    protected void onStart() {
//        super.onStart();
//        ref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                if(error!=null)
//                {
//                    Toast.makeText(BiomesActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (value.exists()){
//                    Object id =value.get("id");
//                    String name=value.getString("name");
//                    System.out.println("id = " + id);
//                    System.out.println("name = " + name);
//                    //Map<String, Object> map = documentSnapshot.getData();
//                }
//            }
//        });
//    }
}