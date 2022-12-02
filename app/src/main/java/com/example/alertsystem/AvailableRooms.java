package com.example.alertsystem;

import static com.example.alertsystem.webserver.getAllRoomCodesAllTables;
import static com.example.alertsystem.webserver.iDelength;

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
import java.util.ArrayList;

public class AvailableRooms extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_rooms);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        int[] rooms= new int [1];
        try {
            rooms=webserver.getAllRoomCodesAllTables();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        int len = 0;
//        int [] ref = new int [len];
        ArrayList<Integer> tempListArray = new ArrayList<Integer>();
        for (int i = 0; i<=rooms.length -1; i++) {
            String temp =" ";
            try {
                temp = webserver.getPatientName(rooms[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (temp == "Field Not Set" || temp == " ") {
                tempListArray.add(rooms[i]);
            }
        }
        int [] ref = new int[tempListArray.size()];
        for (int i = 0; i< tempListArray.size(); i++) {
            ref[i] = tempListArray.get(i);
        }
        lv=(ListView) findViewById(R.id.roomCodes);
        ArrayList<String> arrayList=new ArrayList<String>();
        for (int s:ref){
            arrayList.add(String.valueOf(s));
        }
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.list_content,arrayList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long s) {
                int pos = lv.getPositionForView(view);
                int[] rooms= new int [1];
                try {
                    rooms=webserver.getAllRoomCodesAllTables();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayList<Integer> tempListArray = new ArrayList<Integer>();
                for (int l = 0;l<=rooms.length -1; l++) {
                    String temp =" ";
                    try {
                        temp = webserver.getPatientName(rooms[l]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (temp == "Field Not Set" || temp == " ") {
                        tempListArray.add(rooms[l]);
                    }
                }
                int [] ref = new int[tempListArray.size()];
                for (int t = 0; t< tempListArray.size(); t++) {
                    ref[t] = tempListArray.get(i);
                }
                int roomCode = ref[pos];
                String room = Integer.toString(roomCode);

                //Navigate to patient info(InfoViewActivity)
                Intent pass = new Intent(AvailableRooms.this,PatientInfo.class);
                pass.putExtra("code",room);
                startActivity(pass);
            }
        });
    }
}

