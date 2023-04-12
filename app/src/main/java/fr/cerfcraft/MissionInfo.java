package fr.cerfcraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import fr.cerfcraft.model.Biome;
import fr.cerfcraft.model.Mission;

public class MissionInfo extends AppCompatActivity {
    private Toolbar toolbar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("missions");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_info);

        toolbar=findViewById((R.id.include_mission_info));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String id ="";
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }
        }

        TextView txtView = findViewById(R.id.titre);
        ImageView imageView = findViewById(R.id.image);
        TextView descriptionView = findViewById(R.id.description);
        ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Mission mission = task.getResult().toObject(Mission.class);
                    txtView.setText(mission.getName());
                    descriptionView.setText(mission.getDescription());
                    String uri = "@drawable/" + mission.getImage();

                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                    Drawable res = getResources().getDrawable(imageResource);
                    imageView.setImageDrawable(res);
                }
                else{
                    txtView.setText("");
                    descriptionView.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
}