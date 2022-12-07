package com.example.alertsystem;

import static com.example.alertsystem.webserver.createBatAlert;
import static com.example.alertsystem.webserver.createMovAlert;
import static com.example.alertsystem.webserver.createWifiAlert;
import static com.example.alertsystem.webserver.iDelength;
import static com.example.alertsystem.webserver.iDnameaid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.alertsystem.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private Button alert;
    private ListView lv, lvA;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Stop sending notifications when user accesses the Main Activity
        stopService(new Intent(MainActivity.this,BackgroundService.class));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Alert button for Viewing All Alerts
        alert = (Button)findViewById(R.id.AlertButton);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v =new Intent(getApplicationContext(),AllAlertsView.class);
                startActivity(v);
            }
        });

        //Store Non-dismissed ID's into array of strings
        int length = 0;
        try {
            length = iDelength(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] s = new String[length];
        try {
            s = iDnameaid(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Display Clickable listview for Alerts
        lvA=(ListView)findViewById(R.id.alert_notification_on_main);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,R.layout.list_item,R.id.list_content,s);
        lvA.setAdapter(adapter);
        lvA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //When the user clicks on the listview, the position clicked on the listview is passed as an integer to the Alert_view class
                int pos = lvA.getPositionForView(view);
                int j = 0;

                try {
                    j = webserver.getenumofaID();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int[] ref = new int[j];

                try {
                    ref = webserver.getAllAlertIDS();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int aid = ref[pos];
                String aide = Integer.toString(aid);
                //Navigate to patient info(InfoViewActivity)
                Intent pass = new Intent(MainActivity.this,Alert_view.class);
                pass.putExtra("aid",aide);
                startActivity(pass);
            }
        });
        //Store all roomCodes with patient info added into an array of strings
        int len = 0;
        try {
            len = webserver.getElenz();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] d = new String[len];
        try {
            d = webserver.getCodeandEname();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Display the array of strings in a Clickable listview
        lv=(ListView) findViewById(R.id.listview_of_devices);
        adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.list_item,R.id.list_content,d);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //When the user clicks on the listview, the position clicked on the listview is passed as an integer to the Info_view class
                int pos = lv.getPositionForView(view);
                int j = 0;

                try {
                    j = webserver.getElenz();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int[] ref = new int[j];

                try {
                    ref = webserver.getCodeEarr();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int roomCode = ref[pos];
                String room = Integer.toString(roomCode);
                //Navigate to patient info(InfoViewActivity)
                Intent pass = new Intent(MainActivity.this,InfoViewActivity.class);
                pass.putExtra("code",room);
                startActivity(pass);
            }
        });



    }

    //Inflate Menu in actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //When an option is selected from the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //Add device and remove device were both put on on the same activity easier access
        switch (item.getItemId()) {
            case R.id.add_new_device:
                Intent u = new Intent(getApplicationContext(),AvailableRooms.class);
                startActivity(u);
                break;
            case R.id.remove_device:
                Intent v =new Intent(getApplicationContext(),RemoveDevice.class);
                startActivity(v);
                break;

            case R.id.update_device:
                Intent y = new Intent(getApplicationContext(),Update_info.class);
                startActivity(y);
                break;

            case R.id.settings:
                Intent ss = new Intent(getApplicationContext(),ThemesActivity.class);
                startActivity(ss);
                //Toast.makeText(this, "Edit Patient Info was clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tS:
                Intent ts = new Intent(getApplicationContext(),SupportActivity.class);
                startActivity(ts);
                //Toast.makeText(this, "Edit Patient Info was clicked", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    //When the application is terminated begin the background service to check for alerts in the database
    @Override
    protected void onStop() {
        super.onStop();
        startService(new Intent(MainActivity.this, BackgroundService.class));
    }
}