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
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("AlertSetterReceiver", "onReceive()");
        c = context;
        setAlarm();
    }

    public void setAlarm() {
        AlarmManager alarm = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(c, NotificationReceiver.class);
        i.setAction(NotificationReceiver.MYACTION);
        i.putExtra(MainActivity.INPUT_TEXT, "HELO");

        PendingIntent pendingIntent = PendingIntent.getService(c, 0, i,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTimeInMillis();

        calendar.set(2013, 12, 23, 10, 27, 0);
        long when = calendar.getTimeInMillis();
        Log.d("MainActivity", "setAlarm() - TimeNow: " + now + " TimeWhen: "
                + System.currentTimeMillis());
        alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000,
                pendingIntent);
    }
}
