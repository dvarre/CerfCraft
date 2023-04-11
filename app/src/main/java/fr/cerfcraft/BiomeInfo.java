package fr.cerfcraft;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.BiomeAdapter;
import fr.cerfcraft.adapter.LinkBiomeAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Item;
import fr.cerfcraft.model.Mob;

public class BiomeInfo extends AppCompatActivity {
    private Toolbar toolbar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("biomes");
    LinkBiomeAdapter linkBiomeAdapter;
    List<Object> linkObjectList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biome_info);

        // On récupère les élements d'affichages
        TextView txtView = findViewById(R.id.titre);
        ImageView imageView = findViewById(R.id.image);
        TextView descriptionView = findViewById(R.id.description);


        // Chargement de l'élement séléctionné depuis la vue précédente
        toolbar=findViewById((R.id.include_biome_info));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null){
            String id ="";
            // On regarde si on a bien reçu l'élement de la vue précédente
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }

            //On get le document dans la BDD
            ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        //Si le document est trouvé, on set les éléments graphiques en fonctions des éléments récupérés depuis la BDD
                        Biome biome = task.getResult().toObject(Biome.class);
                        txtView.setText(biome.getName());
                        descriptionView.setText(biome.getDescription());
                        String uri = "@drawable/" + biome.getImage();

                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                        Drawable res = getResources().getDrawable(imageResource);
                        imageView.setImageDrawable(res);
                        System.out.println("Passe bien par les premiere conditions");
                        List<DocumentReference> referencelist = biome.getLinks();
                        System.out.println("referencelist = " + referencelist);
                        String primaryId;
                        for (int i=0; i<referencelist.size(); i++)
                        {
                             primaryId = cleanPrimaryBDDKey(referencelist.get(i));
                             //TODO régler le probleme de la clé primaire brute
                            db.collection("items").document("4WACCmcviTb8ajuF9JpM").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    System.out.println("est-ce qu'il est encore null? " + documentSnapshot.exists());
                                    Object obj = documentSnapshot.toObject(Object.class);
                                    System.out.println("obj a afficher = " + obj);
                                    System.out.println("liste avant ajout : " + linkObjectList.size());
                                    linkObjectList.add(obj);
                                    System.out.println("liste apres ajout : " + linkObjectList.size());
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
        RecyclerView recyclerView = findViewById(R.id.links);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println("nb d'objets passés : " + linkObjectList.size());
        Item itemTest = new Item();
        itemTest.setName("test");
        itemTest.setImage("item_string");
        itemTest.setId(287);
        Mob mobTest = new Mob();
        mobTest.setImage("mob_enderman");
        mobTest.setName("MobTest");
        mobTest.setId(58);
        List<Object> listTest = new ArrayList<>();
        listTest.add(itemTest);
        linkBiomeAdapter = new LinkBiomeAdapter(this, listTest); //TODO probleme ici de liste vide qui est passée. Avant la liste brute j'avais mis ça linkObjectList
        recyclerView.setAdapter(linkBiomeAdapter);
    }

    private String cleanPrimaryBDDKey(DocumentReference dref)
    {
        String keyClear = dref.toString();
        int longRef = keyClear.length();
        for(int i=0;i<longRef;i++){
            if(keyClear.charAt(i) == '/' && i!=0){
                keyClear = keyClear.substring(i++,longRef);
                break;
            }
        }
        System.out.println("keyclear : " + keyClear);
        return keyClear;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
}