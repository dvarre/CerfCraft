package fr.cerfcraft;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CuissonActivity extends AppCompatActivity {
    private Toolbar toolbar;

    protected NotificationManager notificationManager;
    Button button;
    NotificationCompat.Builder builder;
    RadioGroup typeFurnace;
    private int fourneau_cuisson_time = 10;
    private int haut_fourneau_cuisson_time = 5;
    private int fumoir_cuisson_time = 5;
    TextView number_item;
    TextView number_furnaces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisson);

        toolbar=findViewById((R.id.include_cuisson));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        button = (Button)findViewById(R.id.button_cuisson_start);
        button.setOnClickListener(v -> startNotif());
        number_furnaces = (TextView)findViewById(R.id.number_furnace);
        number_item = (TextView)findViewById(R.id.number_items);
        typeFurnace = (RadioGroup) findViewById(R.id.type_furnace);
        builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.logo_craft)
                .setContentTitle("Cuisson des items terminés")
                .setContentText("Tous les items ont cuis")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }


    public void startNotif() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.notify(0, builder.build());
                }
            }
        }, ComputeCuissonTime());
    }

    private int ComputeCuissonTime() {
        return Integer.parseInt(number_item.getText().toString())
                * FindCuissonType()
                / Integer.parseInt(number_furnaces.getText().toString())
                * 1000;
    }

    private int FindCuissonType() {
        switch (typeFurnace.getCheckedRadioButtonId()) {
            case R.id.fourneau_button:
                return fourneau_cuisson_time;
            case R.id.haut_fourneau_button:
                return haut_fourneau_cuisson_time;
            case R.id.fumoir_button:
                return fumoir_cuisson_time;
            default :
                return 10;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
}