package fr.cerfcraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.LinkBiomeAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Craft;
import fr.cerfcraft.model.Dimension;
import fr.cerfcraft.model.Item;
import fr.cerfcraft.model.Mission;
import fr.cerfcraft.model.Mob;
import fr.cerfcraft.model.Structure;

public class BiomeInfo extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("biomes");
    LinkBiomeAdapter linkBiomeAdapter;
    public List<Object> linkObjectList = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.links);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkBiomeAdapter = new LinkBiomeAdapter(getApplicationContext(), linkObjectList);
        recyclerView.setAdapter(linkBiomeAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biome_info);

        // On récupère les élements d'affichages
        TextView txtView = findViewById(R.id.titre);
        ImageView imageView = findViewById(R.id.image);
        TextView descriptionView = findViewById(R.id.description);

        // Chargement de l'élement séléctionné depuis la vue précédente
        Intent intent = getIntent();
        if (intent != null){
            String id ="";
            // On regarde si on a bien reçu l'élement de la vue précédente
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }

            //On get le document dans la BDD
            ref.document(id).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    //Si le document est trouvé, on set les éléments graphiques en fonctions des éléments récupérés depuis la BDD
                    Biome biome = task.getResult().toObject(Biome.class);
                    assert biome != null;
                    txtView.setText(biome.getName());
                    descriptionView.setText(biome.getDescription());
                    String uri = "@drawable/" + biome.getImage();

                    @SuppressLint("DiscouragedApi") int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    @SuppressLint("UseCompatLoadingForDrawables") Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                    List<String> linksBiomes = biome.getLinksBiomes();
                    List<String> linksCrafts = biome.getLinksCrafts();
                    List<String> linksDimensions = biome.getLinksDimensions();
                    List<String> linksItems = biome.getLinksItems();
                    List<String> linksMissions = biome.getLinksMissions();
                    List<String> linksMobs = biome.getLinksMobs();
                    List<String> linksStructures = biome.getLinksStructures();
                    addObjectsToListToDisplay(linksBiomes, "biomes", Biome.class);
                    addObjectsToListToDisplay(linksCrafts, "crafts", Craft.class);
                    addObjectsToListToDisplay(linksDimensions, "dimensions", Dimension.class);
                    addObjectsToListToDisplay(linksItems, "items", Item.class);
                    addObjectsToListToDisplay(linksMissions, "missions", Mission.class);
                    addObjectsToListToDisplay(linksMobs, "mobs", Mob.class);
                    addObjectsToListToDisplay(linksStructures, "structures", Structure.class);
                }
                else{
                    txtView.setText("");
                    descriptionView.setText("");
                }
            });
        }

    }

    private void addObjectsToListToDisplay(List<String> referencelist, String collectionBD, Class testObj){
        for (int i=0; i<referencelist.size(); i++){
            if(referencelist.get(i) != null && referencelist.get(i)!="")
            {
                System.out.println("collectionBD = " + collectionBD);
                System.out.println("referencelist.get(i) = " + referencelist.get(i));
                db.collection(collectionBD).document(referencelist.get(i)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Object obj = documentSnapshot.toObject(testObj);
                        linkObjectList.add(obj);
                        linkBiomeAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }
}

