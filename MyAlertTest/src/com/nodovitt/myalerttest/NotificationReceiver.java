package com.nodovitt.myalerttest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NotificationReceiver extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);

        Log.d("NotificationReceiver","started");
    }
}
