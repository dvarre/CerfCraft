package fr.cerfcraft;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.adapter.BiomeAdapter;
import fr.cerfcraft.adapter.MobAdapter;
import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Mob;

public class MobsActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public Intent intentN;
    RecyclerView recyclerView;
    CollectionReference mobReference = FirebaseFirestore.getInstance().collection("mobs");
    MobAdapter mobAdapter;
    List<Mob> mobList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobs);
        intentN = new Intent(this, NotifsActivity.class);
        toolbar=findViewById((R.id.include_mobs));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.mob_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mobAdapter = new MobAdapter(this, mobList);

        mobReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                            Mob mob = snapshot.toObject(Mob.class);
                            CollectionReference lootsRef = snapshot.getReference().collection("loots");
                            mob.setLoots(lootsRef);
                            mobList.add(mob);
                        }
                        recyclerView.setAdapter(mobAdapter);
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"On Failure : ", e);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem.OnMenuItemClickListener clickMenu = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                startActivity(intentN);
                return true;
            }
        };
        menu.findItem(R.id.menu).setOnMenuItemClickListener(clickMenu);
        return true;
    }
}
