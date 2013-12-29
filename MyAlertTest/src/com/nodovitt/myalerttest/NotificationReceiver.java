package com.nodovitt.myalerttest;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NotificationReceiver extends Service {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);

        Log.d("NotificationReceiver","started");
    }*/

    public static final String MYACTION = "com.nodovitt.myalerttest.MYACTION";
    
    /*@Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NR","onReceive() called");
    }*/
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    

    @Override
    public void onCreate(){
        Log.d("Service","onCreate()");
    }
    
    @Override
    public void onDestroy(){
        Log.d("Service", "onDestroy()");
    }
    @Override
    public int  onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        Log.d("Service", "onStartCommand()");
        stopSelf();
        return START_STICKY;
    }
    

}
