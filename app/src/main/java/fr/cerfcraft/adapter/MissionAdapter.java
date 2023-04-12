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
import android.widget.TextView;

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
import fr.cerfcraft.model.Mission;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MissionViewHolder> {

    Context context;
    List<Mission> missions;

    public MissionAdapter(Context context, List<Mission> missions) {
            this.context = context;
            this.missions = missions;
            }

    @NonNull
    @Override
    public MissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View adapterLayout = LayoutInflater.from(context)
            .inflate(R.layout.mission_list, parent, false);

            return new MissionViewHolder(adapterLayout);
            }

    @Override
    public void onBindViewHolder(@NonNull MissionViewHolder holder, int position) {
        Mission mission = missions.get(position);
        holder.title.setText(mission.getName());
        holder.description.setText(mission.getDescription());
    }

    @Override
    public int getItemCount() {
            return missions.size();
            }

    public static class MissionViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;

        public MissionViewHolder(@NonNull View missionView) {
            super(missionView);
            title = missionView.findViewById(R.id.mission_title);
            description = missionView.findViewById(R.id.mission_description);
        }
    }
}
