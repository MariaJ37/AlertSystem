package com.example.alertsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class RemoveDevice extends AppCompatActivity {
    Button delete;
    EditText editText;
    ListView lv;
    ArrayList<String> listItems;
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_remove_device);
        getSupportActionBar().hide();
        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        int len = 0;
        try {
            len = webserver.getenumofEnt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] d = new String[len];
        try {
            d = webserver.getCodeandname();
        } catch (IOException e) {
            e.printStackTrace();
        }
        delete=(Button)findViewById(R.id.DeleteButton);
        editText=(EditText)findViewById(R.id.editTextPhone);
        lv=(ListView) findViewById(R.id.remove_devices_listview);
        adapter=new ArrayAdapter<String>(RemoveDevice.this,R.layout.list_item,R.id.list_content,d);
        lv.setAdapter(adapter);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getroom = editText.getText().toString();
                int i = Integer.parseInt(getroom);

                try {
                    webserver.delPatientinfo(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent v =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(v);

            }
        });

    }
}