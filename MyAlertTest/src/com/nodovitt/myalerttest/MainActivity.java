package com.nodovitt.myalerttest;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAlarm();
    }

    public void setAlarm() {
        AlarmManager alarm = (AlarmManager) this
                .getSystemService(Context.ALARM_SERVICE);
        
        Intent i = new Intent(this,NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTimeInMillis();
        
        calendar.set(2013,12,23,10,27,0);
        long when = calendar.getTimeInMillis();
        Log.d("MainActivity","setAlarm() - TimeNow: "+now+ " TimeWhen: "+System.currentTimeMillis());
        alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10000, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
