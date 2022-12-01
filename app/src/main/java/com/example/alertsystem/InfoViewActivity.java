package com.example.alertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class InfoViewActivity extends AppCompatActivity {

    private ListView lvp, lve,status;
    private int roomCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_view);
        //getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /*
        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        */

        Intent pass = getIntent();
        String room = pass.getStringExtra("code");

        roomCode = Integer.parseInt(room);


        String m = Integer.toString(roomCode);
        String [] d= new String[0];
        try {

            d = new String[]{webserver.getPatientName(roomCode),webserver.getPatientAddress(roomCode),webserver.getPatientPhone(roomCode),m};
        } catch (IOException e) {
            e.printStackTrace();
        }

        lvp=(ListView)findViewById(R.id.patient_info_alert1);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(InfoViewActivity.this,R.layout.list_item,R.id.list_content,d);
        lvp.setAdapter(adapter);

        String []s= new String[0];
        try {
            s = new String[]{webserver.getPatientEName(roomCode),webserver.getPatientEPhone(roomCode)};
        } catch (IOException e) {
            e.printStackTrace();
        }

        lve=(ListView)findViewById(R.id.emergency_contact_alert);
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(InfoViewActivity.this, R.layout.list_item,R.id.list_content,s);
        lve.setAdapter(adapter1);

        String []t= new String[0];
        try {
            t = new String[]{"Last Movement: "+webserver.getTimeOfMov(404),"Wifi Connection: "+webserver.getTimeOfWifi(404)};
        } catch (IOException e) {
            e.printStackTrace();
        }
        status=(ListView)findViewById(R.id.Movement_wifi);
        ArrayAdapter<String>adapter2=new ArrayAdapter<>(InfoViewActivity.this, R.layout.list_item,R.id.list_content,t);
        status.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_info,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_device:
                String v = Integer.toString(roomCode);
                Intent u = new Intent(InfoViewActivity.this, Update_info.class);
                u.putExtra("room",v);
                startActivity(u);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}