package fr.cerfcraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import fr.cerfcraft.WritingNotesActivity;

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
                        Intent intent = new Intent(this, WritingNotesActivity.class );
                        startActivity(intent);
                    });
                    break;
            }
        }



    }
}