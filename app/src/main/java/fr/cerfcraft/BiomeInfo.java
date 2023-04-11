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
import fr.cerfcraft.model.Item;
import fr.cerfcraft.model.Mob;

public class BiomeInfo extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("biomes");
    LinkBiomeAdapter linkBiomeAdapter;
    public List<Object> linkObjectList = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(" dans le refresh = " + FixBugRecyclerView.getAucuneIdeeList().size());
        RecyclerView recyclerView = findViewById(R.id.links);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkBiomeAdapter = new LinkBiomeAdapter(getApplicationContext(), linkObjectList); //TODO probleme ici de liste vide qui est passée. Avant la liste brute j'avais mis ça linkObjectList et ça FixBugRecyclerView.getAucuneIdeeList()
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
                    List<String> referencelist = biome.getLinks();
                    System.out.println("referencelist " + referencelist);
                    String primaryId;

                    for (int i=0; i<referencelist.size(); i++){
                        db.collection("items").document(referencelist.get(i)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                           @Override
                           public void onSuccess(DocumentSnapshot documentSnapshot) {
                               System.out.println("est-ce qu'il existe " + documentSnapshot.exists());

                               Item obj = documentSnapshot.toObject(Item.class);

                               linkObjectList.add(obj);

                               linkBiomeAdapter.notifyDataSetChanged();
                           }
                        });
                    }
                }
                else{
                    txtView.setText("");
                    descriptionView.setText("");
                }
            });
        }

    }



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_biome_info);
//
//        RecyclerView recyclerView = findViewById(R.id.links);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Chargement de l'élement séléctionné depuis la vue précédente
//        Intent intent = getIntent();
//        if (intent != null){
//            String id ="";
//            // On regarde si on a bien reçu l'élement de la vue précédente
//            if(intent.hasExtra("idToDisplay")){
//                id = intent.getStringExtra("idToDisplay");
//            }
//
//            //On get le document dans la BDD
//            ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        db.collection("items").document("oaCUXjVkeX3AtJ8BnIiB").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                Object obj = documentSnapshot.toObject(Object.class);
//                                System.out.println("liste avant ajout : " + linkObjectList); // vide
//                                linkObjectList.add(obj);
//                                System.out.println("liste apres ajout : " + linkObjectList); // Contient l'objet voulu
//                            }
//                        });
//                        // linkObjectList devient vide
//                    }
//                    else{
//                    // Pas important
//                    }
//                }
//            });
//            linkBiomeAdapter = new LinkBiomeAdapter(getApplicationContext(), linkObjectList);
//            recyclerView.setAdapter(linkBiomeAdapter);
//        }
//        System.out.println("nb d'objets passés : " + linkObjectList.size()); // Toujours égale à 0
//    }



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



    class QueryThread extends Thread {
        private volatile Object linkObjectList;
        String id;


        QueryThread(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            System.out.println("nb de fois passe");
            //TODO régler le probleme de la clé primaire brute
            db.collection("items").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    System.out.println("est-ce qu'il existe " + documentSnapshot.exists());
                    Object obj = documentSnapshot.toObject(Object.class);
                    linkObjectList = obj;
                    System.out.println("obj a afficher = " + obj);
                    System.out.println("liste avant ajout : " + linkObjectList);
                    System.out.println("liste apres ajout : " + linkObjectList);

                }
            });

        }

        public Object getLinkObjectList() {
            return linkObjectList;
        }

    }
}

