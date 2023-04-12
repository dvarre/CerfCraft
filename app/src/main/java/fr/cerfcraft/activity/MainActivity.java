package fr.cerfcraft.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import fr.cerfcraft.BiomesActivity;
import fr.cerfcraft.CraftActivity;
import fr.cerfcraft.CuissonActivity;
import fr.cerfcraft.ItemsActivity;
//import fr.cerfcraft.Manifest;
import fr.cerfcraft.MissionCategoriesActivity;
import fr.cerfcraft.MobsActivity;
import fr.cerfcraft.NotesActivity;
import fr.cerfcraft.NotifsActivity;
import fr.cerfcraft.R;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.N)
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
                R.id.buttonNotes,
                R.id.buttonNotifs
        };
        Class listActivityClass[] = {
                CraftActivity.class,
                BiomesActivity.class,
                ItemsActivity.class,
                MobsActivity.class,
                CuissonActivity.class,
                MissionCategoriesActivity.class,
                NotesActivity.class,
                NotifsActivity.class
        };


        // Bind manuel des boutons sur leurs vues respectives
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
                        Intent intent = new Intent(this, MissionCategoriesActivity.class);
                        startActivity(intent);
                    });
                    break;
                case 6:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, NotesActivity.class);
                        startActivity(intent);
                    });
                    break;
                case 7:
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, NotifsActivity.class);
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
    }
}