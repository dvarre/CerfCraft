package fr.cerfcraft;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;

public class WritingNotesActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public Intent intentN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writingnote);
        intentN = new Intent(this, NotifsActivity.class);
//        toolbar=findViewById((R.id.include_n));
//        setSupportActionBar(toolbar);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        final int listButtonId[] = {
                R.id.valider,
        };

        final int textName = R.id.writingName;
        final int textDesc = R.id.writingDescription;

        Class listClass[] = {
                NotesActivity.class
        };

        EditText nameEditText = findViewById(textName);
        EditText descEditText = findViewById(textDesc);
        for (int i=0; i<listButtonId.length; i++){
            Button activityToAcess = findViewById(listButtonId[i]);

            switch(i){
                default:

                    activityToAcess.setBackgroundColor(Color.BLUE);
                    activityToAcess.setOnClickListener(v -> {

                        Thread t3 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDataBase.class, "database-name").build();
                                NoteDao noteDao = db.noteDao();
                                int id = noteDao.getAll().size();
                                noteDao.insert(new Note(id,nameEditText.getText().toString(),
                                            null,
                                                descEditText.getText().toString()));

                            }
                        });
                        t3.start();
                        try {
                            t3.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(WritingNotesActivity.this, "Note ajoutée avec succes", Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(this, NotesActivity.class );
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