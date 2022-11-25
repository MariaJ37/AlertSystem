package com.example.alertsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class ThemesActivity extends AppCompatActivity {


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

        switchCompat = findViewById(R.id.btn);
        imageView = findViewById(R.id.img);

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
    }
}
