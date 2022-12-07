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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PatientInfo extends AppCompatActivity {
    private Button Save;
    private EditText addName,addAdd,addPhone,addRoom,addEname,addEphone;
    boolean isAllFieldsChecked=false;
    private int roomCode = 0;
    private TextView RoomCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
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

        //Method to "catch" passed information
        Intent pass = getIntent();
        String room = pass.getStringExtra("code");
        roomCode = Integer.parseInt(room);
        String m = Integer.toString(roomCode);

        //Inizialize all edit text and textviews
        RoomCode = (TextView)findViewById(R.id.roomcode);
        RoomCode.setText(m);
        Save = (Button) findViewById(R.id.SaveButtonPatient);
        addName = (EditText) findViewById(R.id.Patient_Name);
        addAdd = (EditText) findViewById(R.id.Patient_Address);
        addPhone = (EditText) findViewById(R.id.Patient_Phone);
        addEname = (EditText) findViewById(R.id.Emergency_name);
        addEphone = (EditText) findViewById(R.id.Emergency_Phone);

        //if save button is clicked
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAllFieldsChecked = CheckAllFields();
                //If All the fields are fild then do this
                if (isAllFieldsChecked){
                    //add all user inputs to strings
                String name = addName.getText().toString();
                String address = addAdd.getText().toString();
                String ename = addEname.getText().toString();
                String phone = addPhone.getText().toString();
                String ephone = addEphone.getText().toString();
                try {
                    //set the patient information in Database
                    webserver.setPatientinfo(name, address, ename, roomCode, ephone, phone);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //return to Main activity
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