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
import fr.cerfcraft.adapter.ItemAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Item;

public class ItemsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CollectionReference itemReference = FirebaseFirestore.getInstance().collection("items");
    ItemAdapter itemAdapter;
    List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = findViewById(R.id.item_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ItemAdapter(this, itemList);

        itemReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                            itemList.add(snapshot.toObject(Item.class));
                        }
                        recyclerView.setAdapter(itemAdapter);
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"On Failure : ", e);
                    }
                });
    }
}