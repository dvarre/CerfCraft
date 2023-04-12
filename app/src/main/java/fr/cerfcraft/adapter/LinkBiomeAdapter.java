package fr.cerfcraft.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import fr.cerfcraft.BiomeInfo;
import fr.cerfcraft.CraftItem;
import fr.cerfcraft.ItemInfo;
import fr.cerfcraft.MissionInfo;
import fr.cerfcraft.MobInfo;
import fr.cerfcraft.R;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Craft;
import fr.cerfcraft.model.Item;
import fr.cerfcraft.model.Loot;
import fr.cerfcraft.model.Mission;
import fr.cerfcraft.model.Mob;

public class LinkBiomeAdapter extends RecyclerView.Adapter<LinkBiomeAdapter.LinkBiomeViewHolder>{

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    List<Object> objects;
    CollectionReference ref;

    public LinkBiomeAdapter(Context context, List<Object> objects) {
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public LinkBiomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(context)
                .inflate(R.layout.link, parent, false);

        return new LinkBiomeViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkBiomeViewHolder holder, int position) {
        Object object = objects.get(position);
        String uri="";

        if (object.getClass() == Biome.class){
            Biome newObject = (Biome) object;
            holder.linkButton.setText(newObject.getName());
            uri = "@drawable/" + newObject.getImage();
            ref = db.collection("biomes");
            holder.linkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ref.whereEqualTo("id", newObject.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Intent intent = new Intent(context, BiomeInfo.class);
                                    intent.putExtra("idToDisplay", document.getId());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                            }
                        }
                    });
                }
            });
        }else if(object.getClass() == Craft.class){
            Craft newObject = (Craft) object;
            holder.linkButton.setText(newObject.getName());
            uri = "@drawable/" + newObject.getImage();
            ref = db.collection("crafts");

            holder.linkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ref.whereEqualTo("id", newObject.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Intent intent = new Intent(context, CraftItem.class);
                                    intent.putExtra("idToDisplay", document.getId());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                            }
                        }
                    });
                }
            });
        } else if(object.getClass() == Item.class){
            Item newObject = (Item) object;
            holder.linkButton.setText(newObject.getName());
            uri = "@drawable/" + newObject.getImage();
            ref = db.collection("items");

            holder.linkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ref.whereEqualTo("id", newObject.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Intent intent = new Intent(context, ItemInfo.class);
                                    intent.putExtra("idToDisplay", document.getId());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                            }
                        }
                    });
                }
            });
        }else if(object.getClass() == Mission.class){
            Mission newObject = (Mission) object;
            holder.linkButton.setText(newObject.getName());
            uri = "@drawable/" + newObject.getImage();
            ref = db.collection("missions");

            holder.linkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ref.whereEqualTo("id", newObject.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Intent intent = new Intent(context, MissionInfo.class);
                                    intent.putExtra("idToDisplay", document.getId());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                            }
                        }
                    });
                }
            });
        }else if(object.getClass() == Mob.class){
            Mob newObject = (Mob) object;
            holder.linkButton.setText(newObject.getName());
            uri = "@drawable/" + newObject.getImage();
            ref = db.collection("mobs");

            holder.linkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ref.whereEqualTo("id", newObject.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Intent intent = new Intent(context, MobInfo.class);
                                    intent.putExtra("idToDisplay", document.getId());
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);
                                }
                            }
                        }
                    });
                }
            });
        }

        int objectDrawableId = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
        Log.d(TAG, String.valueOf(objectDrawableId));
        Drawable objectDrawable = context.getResources().getDrawable(objectDrawableId);
        objectDrawable.setBounds(0, 0, 390, 300);
        holder.linkButton.setCompoundDrawables(objectDrawable, null, null, null);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public static class LinkBiomeViewHolder extends RecyclerView.ViewHolder {

        Button linkButton;

        public LinkBiomeViewHolder(@NonNull View itemView) {
            super(itemView);
            linkButton = itemView.findViewById(R.id.link_button);
        }
    }
}
