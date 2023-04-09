package fr.cerfcraft;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;

public class ShowingNotesActivity extends AppCompatActivity {

    Note note;
    int id;


    private void getIncomingIntent(){
        if(getIntent().hasExtra("Id") &&
                getIntent().hasExtra("Name") &&
                getIntent().hasExtra("Description")){
            String id = getIntent().getStringExtra("Id");
            String name = getIntent().getStringExtra("Name");
            String desc = getIntent().getStringExtra("Description");

            setNote(Integer.parseInt(id),name,desc);
        }
    }


    private void setNote(int id, String name, String desc){
        TextView txtName = findViewById(R.id.editTextTextPersonName);
        txtName.setText(name);

        TextView txtDesc = findViewById(R.id.editTextTextMultiLine);
        txtDesc.setText(desc);

        this.id = id;
    }

    //public ShowingNotesActivity(Note note){this.note=note;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifienote);
        getIncomingIntent();

        final int listButtonId[] = {
                R.id.delete,
                R.id.modifie,
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
                case 1:
                    activityToAcess.setBackgroundColor(Color.BLUE);
                    activityToAcess.setOnClickListener(v -> {


                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDataBase.class, "database-name").build();
                                NoteDao noteDao = db.noteDao();
                                note = noteDao.searchNoteById(id);
                                noteDao.delete(note);
                                note.setName(nameEditText.getText().toString());
                                note.setNoteTxt(descEditText.getText().toString());
                                noteDao.insert(note);
                            }
                        }).start();
                        Toast.makeText(ShowingNotesActivity.this, "Note modifié avec succes", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, NotesActivity.class );
                        startActivity(intent);
                    });
                    break;
                default:
                    activityToAcess.setBackgroundColor(Color.RED);
                    activityToAcess.setOnClickListener(v -> {



                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDataBase.class, "database-name").build();
                                NoteDao noteDao = db.noteDao();
                                note = noteDao.searchNoteById(id);
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