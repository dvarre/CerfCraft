package fr.cerfcraft.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import fr.cerfcraft.BiomesActivity;
import fr.cerfcraft.CraftActivity;
import fr.cerfcraft.CuissonActivity;
import fr.cerfcraft.ItemsActivity;
import fr.cerfcraft.MissionsActivity;
import fr.cerfcraft.MobsActivity;
import fr.cerfcraft.NotesActivity;
import fr.cerfcraft.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int listButtonId[] = {
                R.id.buttonCraft,
                R.id.buttonBiomes,
                R.id.buttonItems,
                R.id.buttonMobs,
                R.id.buttonCuisson,
                R.id.buttonMissions,
                R.id.buttonNotes
        };
        Class listActivityClass[] = {
                CraftActivity.class,
                BiomesActivity.class,
                ItemsActivity.class,
                MobsActivity.class,
                CuissonActivity.class,
                MissionsActivity.class,
                NotesActivity.class
        };
//test
        for (int i=0; i<listButtonId.length; i++){
            Button activityToAcess = findViewById(listButtonId[i]);
            switch(i){
                case 1:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, BiomesActivity.class );
                        startActivity(intent);
                    });
                    break;
                case 2:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, ItemsActivity.class );
                        startActivity(intent);
                    });
                    break;
                case 3:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, MobsActivity.class );
                        startActivity(intent);
                    });
                    break;
                case 4:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, CuissonActivity.class);
                        startActivity(intent);
                    });
                    break;
                case 5:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, MissionsActivity.class);
                        startActivity(intent);
                    });
                    break;
                case 6:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, NotesActivity.class);
                        startActivity(intent);
                    });
                    break;
                default:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, CraftActivity.class);
                        startActivity(intent);
                    });
                    break;
            }
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }
}