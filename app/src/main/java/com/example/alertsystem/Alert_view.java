package com.example.alertsystem;

import static com.example.alertsystem.webserver.getAlertDismissed;
import static com.example.alertsystem.webserver.updateAlertDismissed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class Alert_view extends AppCompatActivity {
    private ListView lvp, lve,status;
    private Button resolved, del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_view);
        getSupportActionBar().hide();

        //Back button
        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });


        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //catch passed data
        Intent pass = getIntent();
        String aide = pass.getStringExtra("aid");
        int aID = Integer.parseInt(aide);

        int roomCode = 0;
        try {
            roomCode = webserver.getAlertRoomCode(aID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Store patient info into array
        String m = Integer.toString(roomCode);
        String [] d= new String[0];
        try {

            d = new String[]{webserver.getPatientName(roomCode),webserver.getPatientAddress(roomCode),webserver.getPatientPhone(roomCode),m};
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Display patient info in non-clickable listview
        lvp=(ListView)findViewById(R.id.patient_info_alert);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(Alert_view.this,R.layout.list_item,R.id.list_content,d);
        lvp.setAdapter(adapter);


        //Store patient emergency information in array of strings
        String []s= new String[0];
        try {
            s = new String[]{webserver.getPatientEName(roomCode),webserver.getPatientEPhone(roomCode)};
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Display patient info in non-clickable listview
        lve=(ListView)findViewById(R.id.emergency_contact_alert);
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(Alert_view.this, R.layout.list_item,R.id.list_content,s);
        lve.setAdapter(adapter1);

        //display status of patient
        boolean state = true;
        String []dismissed= new String [1];
        try {
            state=webserver.getAlertDismissed(aID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (state==true){
            dismissed [0]="Dismissed";
        }
        else {
            dismissed[0]="Not Dismissed";
        }
        status=(ListView)findViewById(R.id.Last_registered_movement);
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(Alert_view.this, R.layout.list_item,R.id.list_content,dismissed);
        status.setAdapter(adapter2);


        //HERE
        resolved = (Button)findViewById(R.id.resolveButton);
        //Resolve button to dismiss alert in Database
        resolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (getAlertDismissed(aID) == false) {
                        new  AlertDialog.Builder(Alert_view.this) //dialog fragment is displayed in case the alert is already dismissed in the database
                                .setIcon(android.R.drawable.ic_delete)
                                .setTitle("Error")
                                .setMessage("The alert is already dismissed.")
                                .setPositiveButton("Go back to Pending alerts", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent= new Intent(Alert_view.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Close", null)
                                .show();
                    }

                    else {
                        updateAlertDismissed(false,aID); //update the alert
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        del = (Button)findViewById(R.id.resolveButton2); //Delete alert from database
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    webserver.delAlert(aID);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





    }
}