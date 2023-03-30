package fr.cerfcraft;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.icu.number.NumberRangeFormatter;
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
import androidx.core.app.NotificationManagerCompat;

public class NotifsActivity extends AppCompatActivity{
    private Toolbar toolbar;

    protected NotificationManager notificationManager;
    Button button;
    TextView time;
    NotificationCompat.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifs);

        toolbar=findViewById((R.id.include_notifs));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification temps";
            String description = "Le channel de notifs du temps";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("0", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        button = (Button)findViewById(R.id.button_start_notif);
        button.setOnClickListener(v -> startNotif());
        time = (TextView)findViewById(R.id.timeNotif);
        builder = new NotificationCompat.Builder(this, "0")
                .setSmallIcon(R.drawable.logo_craft)
                .setContentTitle("Temps de jeu terminé")
                .setContentText("Le temps de jeu souhaité est écoulé")
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
        }, Integer.parseInt(time.getText().toString()) * 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

}