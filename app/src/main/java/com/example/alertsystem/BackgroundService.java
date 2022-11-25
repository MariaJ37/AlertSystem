package com.example.alertsystem;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class BackgroundService extends Service {
    private Timer timer;
    private TimerTask task;
    public static final String Channel_Name = "Notify";
    final Handler handler = new Handler();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startBackground();
        return START_STICKY;
    }



    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Channel_Name,"Test Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

    }

    private void createNotification() {
        Intent notifyint = new Intent(this,MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(this,0,notifyint,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,Channel_Name);
        builder.setContentTitle("Alert Test");
        builder.setContentText("This is to test the notification button");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentIntent(pending);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }

    public void startBackground() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(webserver.alertcheckertest() == true) {
                        createNotificationChannel();
                        createNotification();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
            },5000,10000);

    }


}
