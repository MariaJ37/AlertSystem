package com.example.alertsystem;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class RemoveDevice extends AppCompatActivity {
   private EditText editText;
   private ListView lv;
   private ArrayList<String> listItems;
   private ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_remove_device);
        getSupportActionBar().hide();
        //back button
        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });


        //Method to store patient roomCode and name in array of strings
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

        //display clickable listviews
        lv=(ListView) findViewById(R.id.remove_devices_listview);
        adapter=new ArrayAdapter<String>(RemoveDevice.this,R.layout.list_item,R.id.list_content,d);
        lv.setAdapter(adapter);
        String[] finalD = d;
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int which_item= i;
                //Alert dialog to ask if the user wants to delete the patient
                new  AlertDialog.Builder(RemoveDevice.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("you want to delete patient")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int s) {

                                String data = finalD[i];
                                String idData =  data.substring(10,14).trim();
                                int ID = Integer.parseInt(idData);

                                try {
                                    //remove patient info from roomCode and remove all alerts associated with the removed patient from the Database
                                    webserver.delPatientinfo(ID);
                                    webserver.alertRem(ID);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                //Return to main activity
                                Intent v =new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(v);
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

                return false;
            }
        });


    }
}


