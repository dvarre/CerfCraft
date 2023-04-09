package fr.cerfcraft;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;

public class WritingNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writingnote);

        final int listButtonId[] = {
                R.id.valider,
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

                    activityToAcess.setBackgroundColor(Color.BLUE);
                    activityToAcess.setOnClickListener(v -> {

                        new Thread(new Runnable() {
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
                        }).start();
                        Toast.makeText(WritingNotesActivity.this, "Note ajoutée avec succes", Toast.LENGTH_SHORT).show();



                        Intent intent = new Intent(this, NotesActivity.class );
                        startActivity(intent);
                    });
                    break;
            }
        }




    }
}