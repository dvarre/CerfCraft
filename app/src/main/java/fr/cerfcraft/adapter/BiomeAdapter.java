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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.cerfcraft.R;
import fr.cerfcraft.model.Biome;

public class BiomeAdapter extends RecyclerView.Adapter<BiomeAdapter.BiomeViewHolder> {

    Context context;
    List<Biome> biomes;

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
        String uri = "@drawable/"+ biome.getImage();
        int biomeDrawableId = context.getResources().getIdentifier(uri,"drawable", context.getPackageName());
        Log.d(TAG, String.valueOf(biomeDrawableId));
        Drawable biomeDrawable = context.getResources().getDrawable(biomeDrawableId);
        biomeDrawable.setBounds(0,0,390,300);
        holder.biomeButton.setCompoundDrawables(biomeDrawable,null,null,null);
    }

    @Override
    public int getItemCount() {
        return biomes.size();
    }

    public static class BiomeViewHolder extends RecyclerView.ViewHolder{

        Button biomeButton;

        public BiomeViewHolder(@NonNull View itemView) {
            super(itemView);
            biomeButton = itemView.findViewById(R.id.biome_button);

        }
    }
}
