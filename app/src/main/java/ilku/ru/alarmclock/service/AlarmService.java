package ilku.ru.alarmclock.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ilku.ru.alarmclock.activity.AllClockActivity;
import ilku.ru.alarmclock.model.Alarm;
import ilku.ru.alarmclock.receive.AlarmReceiver;


/**
 * Needed for background work
 * */
public class AlarmService extends Service {

    private AlarmReceiver alarmReceiver;
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    /*public AlarmService() {
        super("AlarmService");
    }*/

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    /*@Override
    protected void onHandleIntent(@Nullable Intent intent) {

        *//*startRepeatingTimer();

        while (true){
            synchronized (this){
                try {
                    wait(5000);
                    Vibrator v;
                    v=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(1000);
                    System.out.println("HW ANDROID SERVICE");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        long endTime = getAlarmDate().getTime();
        System.out.println("endTime = " + endTime);
        System.out.println("system time = " + System.currentTimeMillis());
        System.out.println("Date time = " + Calendar.getInstance().getTime());

        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                    System.out.println("HW ANDROID SERVICE");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*//*
    }*/

    //TODO We take from the alarm
    private Date getAlarmDate(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 20);
        c.set(Calendar.MINUTE, 11);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /*Can be useful*/
    public static int i = 0;
    @Override
    public void onCreate() {
        System.out.println("Create AlarmService");
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ilku.ru.alarmclock.receive.ALARM");
        //intentFilter.addAction("android.intent.action.SCREEN_ON");
        //intentFilter.addAction("android.intent.action.SCREEN_OFF");

        // Set broadcast receiver priority.
        intentFilter.setPriority(100);
        alarmReceiver = new AlarmReceiver();
        registerReceiver(alarmReceiver, intentFilter);
        //alarmReceiver.startRepeatingTimer(this);
        Toast.makeText(this,"onCreate" + i++, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        System.out.println("StartCommand AlarmService");

        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
        //return START_REDELIVER_INTENT;
        /*
        сервис будет перезапущен после того, как был убит системой. Кроме этого, сервис снова получит все вызовы startService, которые не были завершены методом stopSelf(startId).
        */
        //return START_NOT_STICKY;//Сервис будет не перезапущен после того как был убит системой
        //return START_STICKY;//Сервис будет перезапущен после того как был убит системой
    }

    @Override
    public void onDestroy() {
        System.out.println("Destroy AlarmService");
        super.onDestroy();
        //alarmReceiver.cancelAlarm(this);
        if(alarmReceiver!=null)
        {
            unregisterReceiver(alarmReceiver);
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
