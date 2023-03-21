package fr.cerfcraft;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
import fr.cerfcraft.activity.Note;

public class WritingNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        final int listButtonId[] = {
                R.id.valider,
        };

        Class listClass[] = {
                NotesActivity.class
        };


        for (int i=0; i<listButtonId.length; i++){
            Button activityToAcess = findViewById(listButtonId[i]);
            switch(i){
                default:
                    activityToAcess.setOnClickListener(v -> {

                        /*
                        File file = new File("Notes.txt");
                        file.setWritable(true);
                        Log.d("Write", String.valueOf(file.canWrite()));

                        try {
                            FileWriter write = new FileWriter(file);

                            write.write("Name,icone,description\n");

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }*/




                        /*
                        String filename = "memoireNote";
                        String fileContents = "Hello world!";
                        try (FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE)) {
                            fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        FileInputStream fis = null;
                        try {
                            fis = openFileInput(filename);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                        StringBuilder stringBuilder = new StringBuilder();
                        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                            String line = reader.readLine();
                            while (line != null) {
                                stringBuilder.append(line).append('\n');
                                line = reader.readLine();
                                Log.d("Line",line);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {
                            String contents = stringBuilder.toString();
                        }*/


                        /*
                        String[] files = fileList();
                        for(int j=0;j< files.length;j++){
                            Log.d("List Fichier", String.valueOf(files[j]));
                        }*/

                        Intent intent = new Intent(this, NotesActivity.class );
                        startActivity(intent);
                    });
                    break;
            }
        }




    }
}