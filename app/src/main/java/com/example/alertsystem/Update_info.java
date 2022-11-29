package com.example.alertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class Update_info extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        getSupportActionBar().hide();

        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        int length=0;
        int len = 0;
        int [] ref = new int [len];
        try {
            ref = webserver.getAllRoomCodesAllTables();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lv=(ListView) findViewById(R.id.roomCodes);
        ArrayList<String>arrayList=new ArrayList<String>();
        for (int s:ref){
            arrayList.add(String.valueOf(s));
        }
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.list_content,arrayList);
        lv.setAdapter(adapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = lv.getPositionForView(view);
                int j = 0;
                int[] ref = new int[j];
                try {
                    ref = webserver.getAllRoomCodesAllTables();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int roomCode = ref[pos];
                String room = Integer.toString(roomCode);
                //Navigate to patient info(InfoViewActivity)
                Intent pass = new Intent(Update_info.this,PatientInfo.class);
                pass.putExtra("code",room);
                startActivity(pass);
            }
        });

    }
}