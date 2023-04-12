package fr.cerfcraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.LinkBiomeAdapter;
import fr.cerfcraft.adapter.LinkItemAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Craft;
import fr.cerfcraft.model.Dimension;
import fr.cerfcraft.model.Item;
import fr.cerfcraft.model.Mission;
import fr.cerfcraft.model.Mob;
import fr.cerfcraft.model.Structure;

public class ItemInfo extends AppCompatActivity {
    private Toolbar toolbar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("items");
    LinkItemAdapter linkItemAdapter;
    public List<Object> linkObjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        toolbar=findViewById((R.id.include_item_info));
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
        TextView nameView = findViewById(R.id.titre);
        ImageView imageView = findViewById(R.id.image);
        TextView descriptionView = findViewById(R.id.description);
        TextView idView = findViewById(R.id.idNbTxtView);
        TextView typeView = findViewById(R.id.typeStrTxtView);
        TextView damageView = findViewById(R.id.damagesNbTxtView);
        TextView durabilityView = findViewById(R.id.durabilityNbTxtView);
        ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Item item  = task.getResult().toObject(Item.class);
                    nameView.setText(item.getName());
                    descriptionView.setText(item.getDescription());
                    String uri = "@drawable/" + item.getImage();

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                    if(item.getId() != null){
                        idView.setText(item.getId().toString());
                    } else {
                        idView.setText("?");
                    }
                    if(item.getType() != null){
                        typeView.setText(item.getType());
                    } else {
                        typeView.setText("?");
                    }
                    if(item.getDamage() != null){
                        damageView.setText(item.getDamage().toString());
                    } else {
                        damageView.setText("?");
                    }
                    if(item.getDurability() != null){
                        durabilityView.setText(item.getDurability().toString());
                    } else {
                        durabilityView.setText("?");
                    }

                    List<String> linksBiomes = item.getLinksBiomes();
                    List<String> linksCrafts = item.getLinksCrafts();
                    List<String> linksDimensions = item.getLinksDimensions();
                    List<String> linksItems = item.getLinksItems();
                    List<String> linksMissions = item.getLinksMissions();
                    List<String> linksMobs = item.getLinksMobs();
                    List<String> linksStructures = item.getLinksStructures();
                    addObjectsToListToDisplay(linksBiomes, "biomes", Biome.class);
                    addObjectsToListToDisplay(linksCrafts, "crafts", Craft.class);
                    addObjectsToListToDisplay(linksDimensions, "dimensions", Dimension.class);
                    addObjectsToListToDisplay(linksItems, "items", Item.class);
                    addObjectsToListToDisplay(linksMissions, "missions", Mission.class);
                    addObjectsToListToDisplay(linksMobs, "mobs", Mob.class);
                    addObjectsToListToDisplay(linksStructures, "structures", Structure.class);
                }
                else{
                    nameView.setText("");
                    descriptionView.setText("");
                    imageView.setImageDrawable(null);
                    idView.setText("");
                    typeView.setText("");
                    damageView.setText("");
                    durabilityView.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.links);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkItemAdapter = new LinkItemAdapter(getApplicationContext(), linkObjectList);
        recyclerView.setAdapter(linkItemAdapter);
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
                        linkItemAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }
}