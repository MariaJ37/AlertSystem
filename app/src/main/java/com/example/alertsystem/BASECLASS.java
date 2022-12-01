package com.example.alertsystem;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class BASECLASS extends Application {

    boolean nMood;
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPref.init(getApplicationContext());
        SharedPref.putBoolean("night",true);
        nMood = SharedPref.getBoolean("night",true);
        if (nMood){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


    }
}
