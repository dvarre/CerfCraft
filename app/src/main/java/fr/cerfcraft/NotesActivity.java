package fr.cerfcraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import fr.cerfcraft.WritingNotesActivity;
import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        final int listButtonId[] = {
                R.id.addNote
        };

        Class listClass[] = {
                WritingNotesActivity.class
        };


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
                                List<Note> notes = noteDao.getAll();
                                Log.d("Debut Notes","");
                                for (Note note: notes) {
                                    Log.d("note","name = "+note.getName()+" | message = "+note.getNoteTxt());
                                }
                            }
                        }).start();


                        
                        Intent intent = new Intent(this, WritingNotesActivity.class );
                        startActivity(intent);
                    });
                    break;
            }
        }



    }
}