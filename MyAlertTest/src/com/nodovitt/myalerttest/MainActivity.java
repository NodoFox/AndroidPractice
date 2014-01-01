package com.nodovitt.myalerttest;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private Button addTextB;
    private EditText addTextEt;
    private String userText;
    public static final String INPUT_TEXT = "com.nodovitt.myalerttext.INPUT_TEXT";

    private BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addTextEt = (EditText) findViewById(R.id.addTextEt);
        addTextB = (Button) findViewById(R.id.addTextButton);
        addTextB.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                userText = addTextEt.getText().toString();
                if(!userText.equals("")){
                    setAlarm();
                }
                addTextEt.setText("");
            }
        });
        
        //Broadcast
        br = new BroadcastReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("MainActivity","onReceive() ");
                //cancel(0);
            }
            
        };
        
        registerReceiver(br, new IntentFilter("com.nodovitt.myalerttest.SNOOZERECEIVER"));
    }

    public void setAlarm() {
        AlarmManager alarm = (AlarmManager) this
                .getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this, NotificationReceiver.class);
        i.setAction(NotificationReceiver.MYACTION);
        i.putExtra(INPUT_TEXT, userText);
        
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i,
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

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
