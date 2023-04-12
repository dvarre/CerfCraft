package fr.cerfcraft.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import fr.cerfcraft.BiomesActivity;
import fr.cerfcraft.CraftActivity;
import fr.cerfcraft.CuissonActivity;
import fr.cerfcraft.DAO.BasicDAO;
import fr.cerfcraft.ItemsActivity;
//import fr.cerfcraft.Manifest;
import fr.cerfcraft.MissionCategoriesActivity;
import fr.cerfcraft.MobsActivity;
import fr.cerfcraft.NotesActivity;
import fr.cerfcraft.NotifsActivity;
import fr.cerfcraft.R;
import fr.cerfcraft.SearchAdapter;
import fr.cerfcraft.model.Mission;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Pour la recherche
    private RecyclerView searchRecyclerView;
    private RecyclerView.Adapter searchAdapter;
    private RecyclerView.LayoutManager searchLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        toolbar=findViewById(R.id.appToolBar); // On get la toolbar personnalisée qu'on a créé
        setSupportActionBar(toolbar);

        // Pour la recherche

        searchLayoutManager = new LinearLayoutManager(MainActivity.this); // Initialiser le LinearLayoutManager

        searchRecyclerView = findViewById(R.id.search_recyclerView);
        //searchLayoutManager = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(searchLayoutManager);

        //TODO Faire le bind à la place du switch
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(@NonNull MenuItem menuItem) {
                Toast.makeText(MainActivity.this,"Search is expanded",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(@NonNull MenuItem menuItem) {
                Toast.makeText(MainActivity.this,"Search is collapsed",Toast.LENGTH_SHORT).show();
                return true;
            }

        };

        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this,"je suis un boss",Toast.LENGTH_SHORT).show();
                // Effectuer la recherche ici avec la requête "query"
                String[] results = {"Résultat 1", "Résultat 2", "Résultat 3"};

                // Initialiser l'adaptateur avec les résultats de la recherche
                searchAdapter = new SearchAdapter(MainActivity.this,results);

                // Définir l'adaptateur sur le RecyclerView
                searchRecyclerView.setAdapter(searchAdapter);

                // Afficher le RecyclerView
                searchRecyclerView.setVisibility(View.VISIBLE);

                // Masquer le clavier virtuel
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setQueryHint("Search Data here");
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.options:
                Toast.makeText(this, "Le bouton 'options' est pressé", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}