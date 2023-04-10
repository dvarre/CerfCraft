package fr.cerfcraft;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.MainActivity;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;
import fr.cerfcraft.adapter.NoteAdapter;

public class NotesActivity extends AppCompatActivity {

    NoteAdapter noteAdapter;
    List<Note> notes;

    RecyclerView recyclerView;

    //public static void StartShowingActivity(NotesActivity notesActivity){
       // Intent intent = new Intent(, ShowingNotesActivity.class);
        //notesActivity.startActivity(intent);
    //}


    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }





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


        recyclerView = findViewById(R.id.listNote);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notes = new ArrayList<>();
        noteAdapter = new NoteAdapter(notes, this);



        //RecyclerView finalRecyclerView = recyclerView;
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "database-name").build();
                NoteDao noteDao = db.noteDao();
                notes = noteDao.getAll();
                noteAdapter.setListNote(notes);
                //finalRecyclerView.setAdapter(noteAdapter);
                recyclerView.setAdapter(noteAdapter);
                Log.d("Debut Notes","");
                for (Note note: notes) {
                    Log.d("note","id = "+note.getId()+"name = "+note.getName()+" | message = "+note.getNoteTxt());
                }
            }
        }).start();






        for (int i=0; i<listButtonId.length; i++){
            Button activityToAcess = findViewById(listButtonId[i]);
            switch(i){
                default:

                    activityToAcess.setBackgroundColor(Color.BLUE);
                    activityToAcess.setOnClickListener(v -> {
                        Intent intent = new Intent(this, WritingNotesActivity.class );
                        startActivity(intent);
                    });
                    break;
            }
        }



    }
}