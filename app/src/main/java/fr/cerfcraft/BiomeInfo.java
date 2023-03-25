package fr.cerfcraft;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import fr.cerfcraft.model.Biome;

public class BiomeInfo extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("biomes");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biome_info);

        Intent intent = getIntent();
        if (intent != null){
            String id ="";
            if(intent.hasExtra("idToDisplay")){
                id = intent.getStringExtra("idToDisplay");
            }
            TextView txtView = findViewById(R.id.titre);
            ImageView imageView = findViewById(R.id.image);
            TextView descriptionView = findViewById(R.id.description);
            ref.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Biome biome = task.getResult().toObject(Biome.class);
                        txtView.setText(biome.getName());
                        descriptionView.setText(biome.getDescription());
                        String uri = "@drawable/" + biome.getImage();

                        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                        Drawable res = getResources().getDrawable(imageResource);
                        imageView.setImageDrawable(res);
                    }
                    else{
                        txtView.setText("");
                        descriptionView.setText("");
                    }
                }
            });
        }
    }
}