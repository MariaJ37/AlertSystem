package com.example.alertsystem;

import static com.example.alertsystem.webserver.iDelength;
import static com.example.alertsystem.webserver.iDepos;
import static com.example.alertsystem.webserver.iDnameaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;

public class AllAlertsView extends AppCompatActivity {
    private ListView lv1,lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_alerts_view);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Back button
        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        //Store non-dismissed alerts in array of strings
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

        //display non dismissed alerts on clickable listview
        lv1=(ListView)findViewById(R.id.patient_info_alert1);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(AllAlertsView.this,R.layout.list_item,R.id.list_content,s);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //when user clicks on an item, the application navigates accordingly
                int pos = lv1.getPositionForView(view);
                int j = 0;

                try {
                    j = iDelength(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int[] ref = new int[j];

                try {
                    ref = iDepos(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int aid = ref[pos];
                String aide = Integer.toString(aid);
                //Navigate to patient info(InfoViewActivity)
                Intent pass = new Intent(AllAlertsView.this,Alert_view.class);
                pass.putExtra("aid",aide);
                startActivity(pass);
            }
        });

        //Store dismissed alerts in array of strings
        int len = 0;
        try {
            len = iDelength(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] d = new String[len];
        try {
            d = iDnameaid(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //display dismissed alerts in clickable list view
        lv2=(ListView) findViewById(R.id.emergency_contact_alert);
        adapter=new ArrayAdapter<String>(AllAlertsView.this,R.layout.list_item,R.id.list_content,d);
        lv2.setAdapter(adapter);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = lv2.getPositionForView(view);
                int j = 0;

                try {
                    j = iDelength(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int[] ref = new int[j];

                try {
                    ref = iDepos(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int roomCode = ref[pos];
                String room = Integer.toString(roomCode);
                //Navigate to patient info(InfoViewActivity)
                Intent pass = new Intent(AllAlertsView.this,Alert_view.class);
                pass.putExtra("aid",room);
                startActivity(pass);
            }
        });


    }
}