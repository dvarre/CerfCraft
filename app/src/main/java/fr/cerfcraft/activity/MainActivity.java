package fr.cerfcraft.activity;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

import fr.cerfcraft.BiomesActivity;
import fr.cerfcraft.CraftActivity;
import fr.cerfcraft.CuissonActivity;
import fr.cerfcraft.DAO.BasicDAO;
import fr.cerfcraft.ItemsActivity;
//import fr.cerfcraft.Manifest;
import fr.cerfcraft.MissionsActivity;
import fr.cerfcraft.MobsActivity;
import fr.cerfcraft.NotesActivity;
import fr.cerfcraft.NotifsActivity;
import fr.cerfcraft.R;
import fr.cerfcraft.model.Mission;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private boolean test = false;
    public String[] activitys = {"Craft", "Biome", "Item", "Mob", "Cuisson", "Mission", "Notes", "Notif"};
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DocumentReference docRef = db.collection("missions").document("oHO89WngEDDSoDoZxsbD");
        BasicDAO dao = new BasicDAO() ;
        Map<String, Object> map = dao.getDocumentsFromBD(docRef);
        System.out.println("la map vaut : " + map);
        Mission mission = new Mission();
        System.out.println("Les missions fonctionnent " + mission.getMissionList().isEmpty());
//        System.out.println("TEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSSSSSSSSTTTTTTTTTTTTTTT");
//        System.out.println("est que map est rempli ? " + map.equals(null) + " sinon vaut : " + map);
//        //map.forEach((key,value) -> System.out.print("test"));
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
                MissionsActivity.class,
                NotesActivity.class,
                NotifsActivity.class
        };

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

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Spinner sp = (Spinner) findViewById(R.id.spinner2);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, activitys);
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            sp.setAdapter(adapter);

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String value = adapterView.getItemAtPosition(i).toString();
                    test = true;
                    Switch(value);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }

    public void Switch(String value) {
        Intent intent = null;
        switch (value) {
            case "Biome":
                intent = new Intent(this, BiomesActivity.class );
                break;
            case "Item":
                intent = new Intent(this, ItemsActivity.class );
                break;
            case "Mob":
                intent = new Intent(this, MobsActivity.class );
                break;
            case "Cuisson":
                intent = new Intent(this, CuissonActivity.class);
                break;
            case "Mission":
                intent = new Intent(this, MissionsActivity.class);
                break;
            case "Notes":
                intent = new Intent(this, NotesActivity.class);
                break;
            case "Notif":
                intent = new Intent(this, NotifsActivity.class);
                break;
            case "Craft":
                intent = new Intent(this, CraftActivity.class);
                break;
            default:
                intent = null;
                break;
        }
        if (test) {
            startActivity(intent);
            test = false;
        }

    }
}