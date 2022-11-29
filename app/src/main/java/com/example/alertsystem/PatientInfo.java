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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PatientInfo extends AppCompatActivity {
    //This is Edit info class, Referenced from the application design.
    private Button Save;
    private EditText addName,addAdd,addPhone,addRoom,addEname,addEphone;
    boolean isAllFieldsChecked=false;

    //****** check to see if anything is different here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
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

        Save = (Button) findViewById(R.id.SaveButtonPatient);
        addName = (EditText) findViewById(R.id.Patient_Name);
        addAdd = (EditText) findViewById(R.id.Patient_Address);
        addPhone = (EditText) findViewById(R.id.Patient_Phone);
        addEname = (EditText) findViewById(R.id.Emergency_name);
        addEphone = (EditText) findViewById(R.id.Emergency_Phone);
        addRoom = (EditText) findViewById(R.id.Patient_Room);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                //If All the fields are fild then do this
                if (isAllFieldsChecked){
                String name = addName.getText().toString();
                String address = addAdd.getText().toString();
                String ename = addEname.getText().toString();
                String phone = addPhone.getText().toString();
                String ephone = addEphone.getText().toString();
                String roomtmp = addRoom.getText().toString();
                int room = Integer.parseInt(roomtmp);
                try {
                    webserver.setPatientinfo(name, address, ename, room, ephone, phone);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent v = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(v);
            }
        }
        });

    }

    //Checking if the field is empty, if it is, it shows "This Field is required"
    private boolean CheckAllFields(){
        if (addName.length()==0){
            addName.setError("This Field is required");
            return false;
        }
        if (addAdd.length()==0){
            addAdd.setError("This Field is required");
            return false;
        }
        if (addPhone.length()==0){
            addPhone.setError("This Field is required");
            return false;
        }
        if (addRoom.length()==0){
            addRoom.setError("This Field is required");
            return false;
        }
        if(addEname.length()==0){
            addEname.setError("This Field is required");
            return false;
        }
        if(addEphone.length()==0){
            addEphone.setError("This Field is required");
            return false;
        }
        return true;
    }
}