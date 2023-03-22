package fr.cerfcraft.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.cerfcraft.R;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Craft;

public class CraftAdapter extends RecyclerView.Adapter<CraftAdapter.CraftViewHolder>{
    Context context;
    List<Craft> crafts;

    public CraftAdapter(Context context, List<Craft> crafts) {
        this.context = context;
        this.crafts = crafts;
    }

    @NonNull
    @Override
    public CraftAdapter.CraftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(context)
                .inflate(R.layout.craft_list, parent, false);

        return new CraftAdapter.CraftViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CraftAdapter.CraftViewHolder holder, int position) {
        Craft craft = crafts.get(position);
        holder.craftButton.setText(craft.getName());
        String uri = "@drawable/"+ craft.getImage();
        int craftDrawableId = context.getResources().getIdentifier(uri,"drawable", context.getPackageName());
        Log.d(TAG, String.valueOf(craftDrawableId));
        Drawable craftDrawable = context.getResources().getDrawable(craftDrawableId);
        craftDrawable.setBounds(0,0,390,300);
        holder.craftButton.setCompoundDrawables(craftDrawable,null,null,null);
    }

    @Override
    public int getItemCount() {
        return crafts.size();
    }

    public static class CraftViewHolder extends RecyclerView.ViewHolder{

        Button craftButton;

        public CraftViewHolder(@NonNull View itemView) {
            super(itemView);
            craftButton = itemView.findViewById(R.id.craft_button);

        }
    }
}


//public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
//    private Context context;
//    private List<Affirmation> dataSet;
//
//    public ItemAdapter(Context context, List<Affirmation> dataSet){
//        this.context = context;
//        this.dataSet = dataSet;
//    }
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        System.out.println("Test avant le throw position = " + position);
//        System.out.println("Test avant le throw valeur dataset = " + dataSet.get(position).getClass());
//        Affirmation affirmation = dataSet.get(position);
//        System.out.println("Test après le throw " + position);
//        holder.textView.setText(context.getResources().getString(affirmation.getStringRessourceId())); //affirmation.getStringRessourceId()
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataSet.size();
//    }
//
//    public static class ItemViewHolder extends RecyclerView.ViewHolder{
//        TextView textView;
//        public ItemViewHolder(View view) {
//            super(view);
//            textView = (TextView) view.findViewById(R.id.itemTitle);
//        }
//    }
//}
