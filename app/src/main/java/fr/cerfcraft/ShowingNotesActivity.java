package fr.cerfcraft;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;

public class ShowingNotesActivity extends AppCompatActivity {

    Note note;
    int id;

    public Intent intentN;

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
        TextView txtName = findViewById(R.id.modificationName);
        txtName.setText(name);

        TextView txtDesc = findViewById(R.id.modificationDescription);
        txtDesc.setText(desc);

        this.id = id;
    }

    //public ShowingNotesActivity(Note note){this.note=note;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifienote);
        getIncomingIntent();
        intentN = new Intent(this, NotifsActivity.class);
        final int listButtonId[] = {
                R.id.delete,
                R.id.modifie,
        };

        final int textName = R.id.modificationName;
        final int textDesc = R.id.modificationDescription;

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


                        Thread t2 = new Thread(new Runnable() {
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
                        });
                        t2.start();
                        try {
                            t2.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(ShowingNotesActivity.this, "Note modifié avec succes", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, NotesActivity.class );
                        startActivity(intent);
                    });
                    break;
                default:
                    activityToAcess.setBackgroundColor(Color.RED);
                    activityToAcess.setOnClickListener(v -> {



                        Thread t1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDataBase.class, "database-name").build();
                                NoteDao noteDao = db.noteDao();
                                note = noteDao.searchNoteById(id);
                                noteDao.delete(note);

                            }
                        });
                        Toast.makeText(ShowingNotesActivity.this, "Note supprimée avec succes", Toast.LENGTH_SHORT).show();
                        t1.start();
                        try {
                            t1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


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