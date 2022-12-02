package com.example.alertsystem;

import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static com.example.alertsystem.webserver.alertchecker;
import static com.example.alertsystem.webserver.createBatAlert;
import static com.example.alertsystem.webserver.createMovAlert;
import static com.example.alertsystem.webserver.createWifiAlert;
import static com.example.alertsystem.webserver.getAlertAlert;
import static com.example.alertsystem.webserver.getAlertRoomCode;
import static com.example.alertsystem.webserver.getAlertTimeOf;
import static com.example.alertsystem.webserver.getNotificationParameter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class BackgroundService extends Service {
    private Timer timer;
    private TimerTask task;
    private Ringtone ringtone;
    public static final String Channel_Name = "Notify";
    final Handler handler = new Handler();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Start Background service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        backgroundChecker();
        try {
            alertCreateRe();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }


    //create notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Channel_Name,"Test Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

    }

    //create customized notfications for each alert in DB
    private void createNotification() throws IOException {
        int[] num = alertchecker();
        for (int i = 0; i < num.length; i++) {
            int aid = num [i];
            String type = getAlertAlert(aid);
            int room = getAlertRoomCode(aid);
            String last = getAlertTimeOf(aid);
            if (room != 0) {
                String aide = Integer.toString(aid);
                Intent notifyint = new Intent(this,MainActivity.class);
                PendingIntent pending = PendingIntent.getActivity(this,0,notifyint,FLAG_IMMUTABLE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this,Channel_Name);
                builder.setContentTitle("Alert: " + type + " in room " + room);
                builder.setContentText(type + " recorded at: " + last);
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.drawable.ic_baseline_warning_24);
                builder.setContentIntent(pending);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
                managerCompat.notify(i,builder.build());
            }
        }

    }

    //Method to check if there are alerts in the DB every 3 seconds after the app closes by 5 seconds
    public void backgroundChecker() {
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(alertcheck() == true) {
                        pushNotification();
                        timer1.cancel();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        },3000,3000);

    }


    //check if there are undismissed alerts in DB
    public boolean alertcheck() throws IOException {
        int[] num = alertchecker();
        if (num == null){
            return false;
        }
        else {
            return true;
        }

    }

    //Method to
    public void pushNotification() throws IOException{
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                createNotificationChannel();
                try {
                    createNotification();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },0,getNotificationParameter()*1000);
    }

    //creates an alert in the DB if parameter is met every 3 seconds in the background
    public void alertCreateRe() throws IOException {
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    createBatAlert();
                    createMovAlert();
                    createWifiAlert();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },0,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}