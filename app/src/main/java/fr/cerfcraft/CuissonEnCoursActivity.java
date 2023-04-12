package fr.cerfcraft;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import fr.cerfcraft.activity.MainActivity;

public class CuissonEnCoursActivity extends AppCompatActivity {

    private Toolbar toolbar;
    protected NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    Button button;
    public static int nbItem;
    public static int nbFour;
    public static String typeFour;

    private int fourneau_cuisson_time = 10;
    private int haut_fourneau_cuisson_time = 5;
    private int fumoir_cuisson_time = 5;

    private boolean cuissonStarted;

    private TextView itemRestantV;
    private TextView timeRestantV;
    private int itemRestant;
    private int timeRestant;

    private int timeBeforeItem;



    @Override
    public void onResume() {
        super.onResume();
        if (!cuissonStarted) {
            cuissonStarted = true;
            startCuisson();
        }
    }

    private void startCuisson() {
        timeRestant = ComputeCuissonTime();
        itemRestant = nbItem;
        timeBeforeItem = FindCuissonTime() * 1000;
        Cuisson();
    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
    private void Cuisson() {
        UpdateGraphics();
        if (timeRestant <= 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.notify(0, builder.build());
            }
            CuissonActivity.cuissonEnCours = false;
        } else {
            timeRestant -= 1000;
            timeBeforeItem -= 1000;
            while (timeBeforeItem <= 0) {
                itemRestant = Math.max(0, itemRestant - nbFour);
                timeBeforeItem += FindCuissonTime() * 1000;
            }
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Cuisson();
                }
            }, 1000);
        }
    }
    private void UpdateGraphics() {
        itemRestantV.setText(""+itemRestant);
        timeRestantV.setText(""+(timeRestant/1000));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisson_en_cours);
        cuissonStarted = false;
        toolbar=findViewById((R.id.include_cuisson));
        setSupportActionBar(toolbar);
        //ActionBar actionBar=getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification cuisson";
            String description = "Le channel de notifs de la cuisson";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        itemRestantV = (TextView) findViewById(R.id.textViewItemsCuissons);
        timeRestantV = (TextView)findViewById(R.id.textViewTimeCuisson);
        button = (Button)findViewById(R.id.button_cuisson_end);
        button.setOnClickListener(v -> Cancel());
        builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.logo_craft)
                .setContentTitle("Cuisson des items terminés")
                .setContentText("Tous les items ont cuis")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private int ComputeCuissonTime() {
        return nbItem * FindCuissonTime()
                / ComputeFurnaceTimeNumber()
                * 1000;
    }

    private int ComputeFurnaceTimeNumber(){
         return Math.min(nbFour,nbItem)+
                 (Math.min(nbFour,nbItem)%nbFour == 0 ? 0 : 1 );
    }

    private int FindCuissonTime() {
        switch (typeFour) {
            case "fourneau":
                return fourneau_cuisson_time;
            case "haut_fourneau":
                return haut_fourneau_cuisson_time;
            case "fumoir":
                return fumoir_cuisson_time;
            default :
                return 10;
        }
    }

    public void Cancel() {
        CuissonActivity.cuissonEnCours = false;
        Intent intent = new Intent(this, CuissonActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
}