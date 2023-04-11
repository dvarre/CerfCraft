package fr.cerfcraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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

import fr.cerfcraft.adapter.LinkBiomeAdapter;
import fr.cerfcraft.model.Craft;
import fr.cerfcraft.model.Item;
import fr.cerfcraft.model.Loot;
import fr.cerfcraft.model.Mob;

public class MobInfo extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("mobs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob_info);

        String id ="";
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }
        }
        TextView txtView = findViewById(R.id.titre);
        TextView descriptionView = findViewById(R.id.description);
        ImageView imageView = findViewById(R.id.image);
        Context ctx = this;
        RecyclerView recyclerView = findViewById(R.id.links_mob);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Mob mob = task.getResult().toObject(Mob.class);
                    mob.setLoots(task.getResult().getReference().collection("loots"));
                    txtView.setText(mob.getName());
                    descriptionView.setText(mob.getBehaviour());
                    String uri = "@drawable/" + mob.getImage();

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);

                    if(mob.getLoots() != null){
                        System.out.println("loots is here");
                        mob.getLoots().get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<Object> itemLoots = new ArrayList<>();
                                LinkBiomeAdapter adapter = new LinkBiomeAdapter(ctx,itemLoots);
                                for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
                                    System.out.println(queryDocumentSnapshot.getId());
                                    System.out.println(queryDocumentSnapshot.getData().get("quantity"));
                                    Loot loot = queryDocumentSnapshot.toObject(Loot.class);
                                    loot.getItem().get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            itemLoots.add(documentSnapshot.toObject(Item.class));
                                            recyclerView.setAdapter(adapter);
                                        }
                                    });
                                }
                            }
                        });
                    }

                }
                else{
                    txtView.setText("");
                    descriptionView.setText("");
                }
            }
        });
    }
}