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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.LinkBiomeAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Craft;
import fr.cerfcraft.model.Item;

public class CraftItem extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("crafts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft_item);

        String id ="";
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }
//            TextView txtView = findViewById(R.id.craft_name);

        }
        TextView txtView = findViewById(R.id.titre);
        ImageView imageView = findViewById(R.id.image);
        TextView descriptionView = findViewById(R.id.description);
        Context ctx = this;
        ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Craft craft = task.getResult().toObject(Craft.class);
                    txtView.setText(craft.getName());
                    descriptionView.setText(craft.getDescription());
                    String uri = "@drawable/" + craft.getImage();

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                    RecyclerView recyclerView = findViewById(R.id.links_craft);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                    String itemResultId = craft.getResult().getId();
                    db.collection("items").document(itemResultId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            List<Object> listResult = new ArrayList<>();
                            listResult.add(documentSnapshot.toObject(Item.class));
                            LinkBiomeAdapter adapter = new LinkBiomeAdapter(ctx, listResult);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                }
                else{
                    txtView.setText("");
                    descriptionView.setText("");
                }
            }
        });
    }
}