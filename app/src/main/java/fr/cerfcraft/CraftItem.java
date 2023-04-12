package fr.cerfcraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
import fr.cerfcraft.adapter.LinkCraftAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Craft;
import fr.cerfcraft.model.Dimension;
import fr.cerfcraft.model.Item;
import fr.cerfcraft.model.Mission;
import fr.cerfcraft.model.Mob;
import fr.cerfcraft.model.Structure;

public class CraftItem extends AppCompatActivity {
    private Toolbar toolbar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("crafts");
    LinkCraftAdapter linkCraftAdapter;
    public List<Object> linkObjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft_item);

        toolbar=findViewById((R.id.include_craft_item));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String id ="";
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }
        }

        TextView nameTxtView = findViewById(R.id.titre);
        ImageView imageView = findViewById(R.id.image);
        TextView descriptionView = findViewById(R.id.description);
        TextView idTxtView = findViewById(R.id.idNbTxtView);

        ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Craft craft = task.getResult().toObject(Craft.class);
                    nameTxtView.setText(craft.getName());
                    descriptionView.setText(craft.getDescription());
                    String uri = "@drawable/" + craft.getImage();

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                    if(craft.getId() != null){
                        idTxtView.setText(craft.getId().toString());
                    } else {
                        idTxtView.setText("?");
                    }

                    List<String> linksBiomes = craft.getLinksBiomes();
                    List<String> linksCrafts = craft.getLinksCrafts();
                    List<String> linksDimensions = craft.getLinksDimensions();
                    List<String> linksItems = craft.getLinksItems();
                    List<String> linksMissions = craft.getLinksMissions();
                    List<String> linksMobs = craft.getLinksMobs();
                    List<String> linksStructures = craft.getLinksStructures();
                    addObjectsToListToDisplay(linksBiomes, "biomes", Biome.class);
                    addObjectsToListToDisplay(linksCrafts, "crafts", Craft.class);
                    addObjectsToListToDisplay(linksDimensions, "dimensions", Dimension.class);
                    addObjectsToListToDisplay(linksItems, "items", Item.class);
                    addObjectsToListToDisplay(linksMissions, "missions", Mission.class);
                    addObjectsToListToDisplay(linksMobs, "mobs", Mob.class);
                    addObjectsToListToDisplay(linksStructures, "structures", Structure.class);


                }
                else{
                    nameTxtView.setText("");
                    descriptionView.setText("");
                    imageView.setImageDrawable(null);
                    nameTxtView.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.links);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkCraftAdapter = new LinkCraftAdapter(getApplicationContext(), linkObjectList);
        recyclerView.setAdapter(linkCraftAdapter);
    }


    private void addObjectsToListToDisplay(List<String> referencelist, String collectionBD, Class testObj){
        for (int i=0; i<referencelist.size(); i++){
            if(referencelist.get(i) != null && referencelist.get(i)!="")
            {
                db.collection(collectionBD).document(referencelist.get(i)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Object obj = documentSnapshot.toObject(testObj);
                        linkObjectList.add(obj);
                        linkCraftAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }
}