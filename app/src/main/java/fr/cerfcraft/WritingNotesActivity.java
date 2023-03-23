package fr.cerfcraft;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;

import fr.cerfcraft.R;
import fr.cerfcraft.activity.AppDataBase;
import fr.cerfcraft.activity.Note;
import fr.cerfcraft.activity.NoteDao;

public class WritingNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

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
                    activityToAcess.setOnClickListener(v -> {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDataBase.class, "database-name").build();
                                NoteDao noteDao = db.noteDao();
                                noteDao.insert(new Note(nameEditText.getText().toString(),
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