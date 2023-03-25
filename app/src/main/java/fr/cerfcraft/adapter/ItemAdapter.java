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
import fr.cerfcraft.ItemInfo;
import fr.cerfcraft.R;
import fr.cerfcraft.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    Context context;
    List<Item> items;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("items");

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

        holder.itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.whereEqualTo("id", item.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Intent intent = new Intent(context, ItemInfo.class);
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
