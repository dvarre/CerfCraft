package fr.cerfcraft.adapter;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import fr.cerfcraft.BiomeInfo;
import fr.cerfcraft.CraftItem;
import fr.cerfcraft.R;
import fr.cerfcraft.model.Biome;

public class BiomeAdapter extends RecyclerView.Adapter<BiomeAdapter.BiomeViewHolder> {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    List<Biome> biomes;
    CollectionReference ref = db.collection("biomes");

    public BiomeAdapter(Context context, List<Biome> biomes) {
        this.context = context;
        this.biomes = biomes;
    }

    @NonNull
    @Override
    public BiomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(context)
                .inflate(R.layout.biome, parent, false);

        return new BiomeViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull BiomeViewHolder holder, int position) {
        Biome biome = biomes.get(position);
        holder.biomeButton.setText(biome.getName());
        String uri = "@drawable/" + biome.getImage();
        int biomeDrawableId = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
        Log.d(TAG, String.valueOf(biomeDrawableId));
        Drawable biomeDrawable = context.getResources().getDrawable(biomeDrawableId);
        biomeDrawable.setBounds(0, 0, 390, 300);
        holder.biomeButton.setCompoundDrawables(biomeDrawable, null, null, null);

        holder.biomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.whereEqualTo("id", biome.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Intent intent = new Intent(context, BiomeInfo.class);
                                intent.putExtra("idToDisplay", document.getId());
                                context.startActivity(intent);
                            }
                        }
                    }
                });
            }
        });


        // Foncitonne parfaitement . Sert à ajouter des données à la BDD
//        holder.biomeButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                HashMap hashMap=new HashMap();
//                hashMap.put("id",25);
//                hashMap.put("description","test d'insertion dans la BDD avec les boutons des recycler view");
//                hashMap.put("image","biome_jungle");
//                hashMap.put("name","Biome de test");
//                hashMap.put("rarity","test");
//                hashMap.put("temperature",0.69);
//                hashMap.put("type","Aucune idée");
//                ref.add(hashMap).addOnSuccessListener(new OnSuccessListener() {
//                    @Override
//                    public void onSuccess(Object o) {
//                        Toast.makeText(context, "Data add succesfully", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

//        holder.biomeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if(documentSnapshot.exists()){
//                            Biome biomeQuery = documentSnapshot.toObject(Biome.class);
//
//                            //Object id =documentSnapshot.get("id");
//                            //String name=documentSnapshot.getString("name");
//                            System.out.println("id = " + biome.getId());
//                            System.out.println("name = " + biome.getName());
//                            //Map<String, Object> map = documentSnapshot.getData();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return biomes.size();
    }

    public static class BiomeViewHolder extends RecyclerView.ViewHolder {

        Button biomeButton;

        public BiomeViewHolder(@NonNull View itemView) {
            super(itemView);
            biomeButton = itemView.findViewById(R.id.biome_button);

        }
    }
}
