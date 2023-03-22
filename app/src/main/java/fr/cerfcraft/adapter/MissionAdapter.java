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
            System.out.println("valeur du nom avant throw = " + mission.getName());
            holder.missionButton.setText(mission.getName());
            String uri = "@drawable/"+ mission.getImage();
            int missionDrawableId = context.getResources().getIdentifier(uri,"drawable", context.getPackageName());
            Log.d(TAG, String.valueOf(missionDrawableId));
            Drawable missionDrawable = context.getResources().getDrawable(missionDrawableId);
            missionDrawable.setBounds(0,0,390,300);
            holder.missionButton.setCompoundDrawables(missionDrawable,null,null,null);
            }

    @Override
    public int getItemCount() {
            return missions.size();
            }

    public static class MissionViewHolder extends RecyclerView.ViewHolder{

        Button missionButton;

        public MissionViewHolder(@NonNull View missionView) {
            super(missionView);
            missionButton = missionView.findViewById(R.id.mission_button);

        }
    }
}
