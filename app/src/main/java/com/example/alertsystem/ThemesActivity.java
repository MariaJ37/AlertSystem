package com.example.alertsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import java.io.IOException;

public class ThemesActivity extends AppCompatActivity {

    private Button buttonSaveParameters;
    private EditText editTextPercent, editTextMovement, editTextWifi, editTextNotification;
    SwitchCompat switchCompat;
    ImageView imageView;
    boolean nMood;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        switchCompat = findViewById(R.id.btn);
        imageView = findViewById(R.id.img);
        buttonSaveParameters = findViewById(R.id.buttonSaveParameters);
        editTextPercent = findViewById(R.id.editTextPercent);
        editTextMovement = findViewById(R.id.editTextMovement);
        editTextWifi = findViewById(R.id.editTextWifi);
        editTextNotification = findViewById(R.id.editTextNotification);

        findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nMood = sharedPreferences.getBoolean("night", false);

        if (nMood) {
            switchCompat.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ThemesActivity.this, "Haha", Toast.LENGTH_SHORT).show();
                if (nMood) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor =sharedPreferences.edit();
                    editor.putBoolean("night",false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor =sharedPreferences.edit();
                    editor.putBoolean("night",true);
                }
            }
        });
        //Get the values from parameters table and insert into the edit text so the user knows the current values.
        int curPerc = 0;
        int curMove = 0;
        int curWifi = 0;
        int curNotif = 0;
        try {
            curPerc = webserver.getPercentParameter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            curMove = webserver.getMovementParameter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            curWifi = webserver.getWifiParameter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            curNotif = webserver.getNotificationParameter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String strPerc = Integer.toString(curPerc);
        String strMove = Integer.toString(curMove);
        String strWifi = Integer.toString(curWifi);
        String strNotif = Integer.toString(curNotif);
        editTextPercent.setText(strPerc, TextView.BufferType.EDITABLE);
        editTextMovement.setText(strMove, TextView.BufferType.EDITABLE);
        editTextWifi.setText(strWifi, TextView.BufferType.EDITABLE);
        editTextNotification.setText(strNotif, TextView.BufferType.EDITABLE);

        //editTextNotification.setText(get current interval);

        buttonSaveParameters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the settings in fields
                int percent = 0, wifi = 0, movement = 0, notification = 0;
                try {
                    percent = Integer.parseInt(String.valueOf(editTextPercent.getText()));
                } catch (Exception e){
                }
                try {
                    wifi = Integer.parseInt(String.valueOf(editTextWifi.getText()));
                } catch (Exception e){
                }
                try {
                    movement = Integer.parseInt(String.valueOf(editTextMovement.getText()));
                } catch (Exception e){
                }
                try {
                    notification = Integer.parseInt(String.valueOf(editTextNotification.getText()));
                } catch (Exception e){
                }
                //try to set the parameters if the field is not 0 or null. Toast otherwise
                if (percent != 0){
                    try {
                        webserver.setPercentParameter(percent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ThemesActivity.this, "Percent Field Not Updated. Invalid Input.", Toast.LENGTH_SHORT).show();
                }
                if(wifi != 0) {
                    try {
                        webserver.setWifiParameter(wifi);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ThemesActivity.this, "Wifi Field Not Updated. Invalid Input.", Toast.LENGTH_SHORT).show();
                }
                if (movement != 0) {
                    try {
                        webserver.setMoveParameter(movement);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ThemesActivity.this, "Movement Field Not Updated. Invalid Input.", Toast.LENGTH_SHORT).show();
                }
                if(notification != 0) {
                    try {
                        webserver.setNotificationParameter(notification);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ThemesActivity.this, "Notification Field Not Updated. Invalid Input.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
