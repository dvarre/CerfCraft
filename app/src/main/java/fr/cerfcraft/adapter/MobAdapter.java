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

import fr.cerfcraft.CraftItem;
import fr.cerfcraft.MobInfo;
import fr.cerfcraft.R;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Mob;

public class MobAdapter extends RecyclerView.Adapter<MobAdapter.MobViewHolder> {

    Context context;
    List<Mob> mobs;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("mobs");

    public MobAdapter(Context context, List<Mob> mobs) {
        this.context = context;
        this.mobs = mobs;
    }

    @NonNull
    @Override
    public MobAdapter.MobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(context)
                .inflate(R.layout.mob_list, parent, false);

        return new MobAdapter.MobViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MobAdapter.MobViewHolder holder, int position) {
        Mob mob = mobs.get(position);
        holder.mobButton.setText(mob.getName());
        String uri = "@drawable/" + mob.getImage();
        int mobDrawableId = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
        Log.d(TAG, String.valueOf(mobDrawableId));
        Drawable mobDrawable = context.getResources().getDrawable(mobDrawableId);
        mobDrawable.setBounds(0, 0, 390, 300);
        holder.mobButton.setCompoundDrawables(mobDrawable, null, null, null);

        holder.mobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.whereEqualTo("id", mob.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Intent intent = new Intent(context, MobInfo.class);
                                intent.putExtra("idToDisplay", document.getId());
                                context.startActivity(intent);
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mobs.size();
    }

    public static class MobViewHolder extends RecyclerView.ViewHolder {

        Button mobButton;

        public MobViewHolder(@NonNull View mobView) {
            super(mobView);
            mobButton = mobView.findViewById(R.id.mob_button);
        }
    }
}
