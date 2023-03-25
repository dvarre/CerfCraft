package fr.cerfcraft.model;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.cerfcraft.NotesActivity;
import fr.cerfcraft.R;
import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;

public class ShowingNotesActivity extends AppCompatActivity {

    Note note;

    public ShowingNotesActivity(Note note){this.note=note;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        final int listButtonId[] = {
                R.id.delete,
        };

        final int textName = R.id.editTextTextPersonName;
        final int textDesc = R.id.editTextTextMultiLine;

        Class listClass[] = {
                NotesActivity.class
        };

        EditText nameEditText = findViewById(textName);
        EditText descEditText = findViewById(textDesc);
        for (int i=0; i<listButtonId.length; i++){
            Button activityToAcess = findViewById(listButtonId[i]);

            switch(i){
                default:
                    activityToAcess.setOnClickListener(v -> {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDataBase.class, "database-name").build();
                                NoteDao noteDao = db.noteDao();
                                noteDao.delete(note);

                            }
                        }).start();
                        Toast.makeText(ShowingNotesActivity.this, "Note supprimée avec succes", Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(this, NotesActivity.class );
                        startActivity(intent);
                    });
                    break;
            }
        }




    }
}