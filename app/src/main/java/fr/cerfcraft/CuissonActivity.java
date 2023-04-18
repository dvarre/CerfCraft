package fr.cerfcraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import fr.cerfcraft.activity.MainActivity;

public class CuissonActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public Intent intentN;
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
        toolbar=findViewById((R.id.include_cuisson));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        intentN = new Intent(this, NotifsActivity.class);

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

    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
    public void startNotif() {
        cuissonEnCours = true;
        try {
            CuissonEnCoursActivity.nbItem = Integer.parseInt(number_item.getText().toString());
            CuissonEnCoursActivity.nbFour = Integer.parseInt(number_furnaces.getText().toString());
            CuissonEnCoursActivity.typeFour = FindCuissonType();
            Intent intent = new Intent(this, CuissonEnCoursActivity.class);
            startActivity(intent);
        } catch ( Exception e)  {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setMessage("Veuillez rentrez des nombres valides.")
                    .setTitle("Error Cuisson")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}

                    });
            AlertDialog dialog = builder2.create();
            dialog.show();
        }
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