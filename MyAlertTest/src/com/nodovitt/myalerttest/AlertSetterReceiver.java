package com.nodovitt.myalerttest;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlertSetterReceiver extends BroadcastReceiver {

    Context c;
    String notificationText = "some default text";
    @Override
    public void onReceive(Context context, Intent intent) {

        c = context;
        if(intent.getAction().equals("com.nodovitt.myalerttest.ALERTSETTER")){
            notificationText = intent.getStringExtra("NOTIFY");
            
            Log.d("Alert", notificationText+"blah");
            callServiceToSetAlarm();
        }
    }

    public void callServiceToSetAlarm() {
        AlarmManager alarm = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(c,NotificationReceiver.class);
        i.putExtra("NOTIFY", notificationText);

        PendingIntent pendingIntent = PendingIntent.getService(c, 0, i,
                PendingIntent.FLAG_CANCEL_CURRENT);
       
        alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 4000,
                pendingIntent);
        Log.d("Alert","callServiceTosetAlarm()");
    }
}
