package com.example.alertsystem;

import static com.example.alertsystem.webserver.alertchecker;
import static com.example.alertsystem.webserver.getAlertAlert;
import static com.example.alertsystem.webserver.getAlertRoomCode;
import static com.example.alertsystem.webserver.getAlertTimeOf;

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

public class ForegroundService extends Service {
    public static final String Channel_Name = "Notify";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


 /*   public int onStartCommand(Intent intent, int flags, int startId) {
          Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_Name);
        builder.setContentTitle("Test Notification Foreground");
        builder.setContentText("Room Code 400");
        builder.setSmallIcon(R.drawable.ic_baseline_warning_24_notification);
        builder.setContentIntent(pending);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1, builder.build());

// Notification ID cannot be 0.
        startForeground(Channel_Name, notification);
        return super.onStartCommand(intent, flags, startId);
    }*/




}
