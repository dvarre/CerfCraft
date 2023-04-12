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

import fr.cerfcraft.MissionCategoryInfo;
import fr.cerfcraft.R;
import fr.cerfcraft.model.MissionCategory;

public class MissionCategoryAdapter extends RecyclerView.Adapter<MissionCategoryAdapter.MissionViewHolder> {

    Context context;
    List<MissionCategory> missionCategories;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("missions");

    public MissionCategoryAdapter(Context context, List<MissionCategory> missions) {
            this.context = context;
            this.missionCategories = missions;
            }

    @NonNull
    @Override
    public MissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View adapterLayout = LayoutInflater.from(context)
            .inflate(R.layout.mission_category_list, parent, false);

            return new MissionViewHolder(adapterLayout);
            }

    @Override
    public void onBindViewHolder(@NonNull MissionViewHolder holder, int position) {
            MissionCategory mission = missionCategories.get(position);
            holder.missionButton.setText(mission.getName());
            String uri = "@drawable/"+ mission.getImage();
            int missionDrawableId = context.getResources().getIdentifier(uri,"drawable", context.getPackageName());
            Log.d(TAG, String.valueOf(missionDrawableId));
            Drawable missionDrawable = context.getResources().getDrawable(missionDrawableId);
            missionDrawable.setBounds(0,0,390,300);
            holder.missionButton.setCompoundDrawables(missionDrawable,null,null,null);

            holder.missionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ref.whereEqualTo("id", mission.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    Intent intent = new Intent(context, MissionCategoryInfo.class);
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
            return missionCategories.size();
            }

    public static class MissionViewHolder extends RecyclerView.ViewHolder{

        Button missionButton;

        public MissionViewHolder(@NonNull View missionView) {
            super(missionView);
            missionButton = missionView.findViewById(R.id.mission_button);

        }
    }
}
