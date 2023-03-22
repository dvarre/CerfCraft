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

import java.util.List;

import fr.cerfcraft.R;
import fr.cerfcraft.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    Context context;
    List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(context)
                .inflate(R.layout.item_list, parent, false);

        return new ItemAdapter.ItemViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.itemButton.setText(item.getName());
        String uri = "@drawable/"+ item.getImage();
        int itemDrawableId = context.getResources().getIdentifier(uri,"drawable", context.getPackageName());
        Log.d(TAG, String.valueOf(itemDrawableId));
        Drawable itemDrawable = context.getResources().getDrawable(itemDrawableId);
        itemDrawable.setBounds(0,0,390,300);
        holder.itemButton.setCompoundDrawables(itemDrawable,null,null,null);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        Button itemButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemButton = itemView.findViewById(R.id.item_button);

        }
    }
}
