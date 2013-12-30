package com.nodovitt.myalerttest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.util.Log;

public class NotificationReceiver extends Service {

    /*
     * @Override protected void onCreate(Bundle savedInstanceState) {
     * super.onCreate(savedInstanceState);
     * setContentView(R.layout.activity_notification_receiver);
     * 
     * Log.d("NotificationReceiver","started"); }
     */

    public static final String MYACTION = "com.nodovitt.myalerttest.MYACTION";

    /*
     * @Override public void onReceive(Context context, Intent intent) {
     * Log.d("NR","onReceive() called"); }
     */

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("Service", "onCreate()");
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        
        Log.d("Service", "onStartCommand()");
        String userText = intent.getStringExtra(MainActivity.INPUT_TEXT);
        Log.d("Service", userText);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("ALERT")
                .setContentText(userText)
                .setSmallIcon(R.drawable.ic_launcher)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        NotificationManager nM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nM.notify(0, noti);

        stopSelf();
        return START_STICKY;
    }

}
