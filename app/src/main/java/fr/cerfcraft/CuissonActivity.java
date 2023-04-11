package fr.cerfcraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import fr.cerfcraft.activity.MainActivity;

public class CuissonActivity extends AppCompatActivity {

    public static boolean cuissonEnCours = false;
    protected NotificationManager notificationManager;
    Button button;

    RadioGroup typeFurnace;

    TextView number_item;
    TextView number_furnaces;
    @Override
    public void onResume() {
        super.onResume();
        if (cuissonEnCours) {
            Intent intent = new Intent(this, CuissonEnCoursActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisson);
        button = (Button)findViewById(R.id.button_cuisson_start);
        button.setOnClickListener(v -> startNotif());
        number_furnaces = (TextView)findViewById(R.id.number_furnace);
        number_item = (TextView)findViewById(R.id.number_items);
        typeFurnace = (RadioGroup) findViewById(R.id.type_furnace);

    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
    public void startNotif() {
        cuissonEnCours = true;
        CuissonEnCoursActivity.nbItem = Integer.parseInt(number_item.getText().toString());
        CuissonEnCoursActivity.nbFour = Integer.parseInt(number_furnaces.getText().toString());
        CuissonEnCoursActivity.typeFour = FindCuissonType();
        Intent intent = new Intent(this, CuissonEnCoursActivity.class);
        startActivity(intent);
    }

    private String FindCuissonType() {
        switch (typeFurnace.getCheckedRadioButtonId()) {
            case R.id.fourneau_button:
                return "fourneau";
            case R.id.haut_fourneau_button:
                return "haut_fourneau";
            case R.id.fumoir_button:
                return "fumoir_cuisson";
            default :
                return "";
        }
    }


}