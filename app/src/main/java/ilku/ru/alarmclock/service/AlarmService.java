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

import static ilku.ru.alarmclock.receive.AlarmReceiver.ACTION_ALARM;

/**
 * Needed for background work
 * */
public class AlarmService extends Service {

    private BroadcastReceiver alarmReceiver;
    private long time;

    Alarm alarm = new Alarm();
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
    @Override
    public void onCreate() {
        System.out.println("Create AlarmService");
        /*alarmReceiver =new AlarmReceiver();
        final IntentFilter theFilter = new IntentFilter(ACTIVITY_SERVICE);
        //theFilter.addAction(ACTIVITY_SERVICE);
        alarmReceiver = new BroadcastReceiver() {*/

        /*IntentFilter intentFilter = new IntentFilter();

        // Add network connectivity change action.
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");

        // Set broadcast receiver priority.
        intentFilter.setPriority(100);

        // Create a network change broadcast receiver.
        screenOnOffReceiver = new ScreenOnOffReceiver();

        // Register the broadcast receiver with the intent filter object.
        registerReceiver(screenOnOffReceiver, intentFilter);*/

            /*@Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("test");
                *//*PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
                PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
                //Осуществляем блокировку
                wl.acquire();//Это нужно чтобы не погас экран


                Intent intentone = new Intent(context.getApplicationContext(), AllClockActivity.class);
                intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentone);

                Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();

                Vibrator v;
                v=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(3000);

                //Разблокируем поток.
                wl.release();*//*
            }
        };
        // Registers the receiver so that your service will listen for
        // broadcasts
        registerReceiver(alarmReceiver, theFilter);*/
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter();

        // Add network connectivity change action.
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");

        // Set broadcast receiver priority.
        intentFilter.setPriority(100);

        // Create a network change broadcast receiver.
        alarmReceiver = new AlarmReceiver();

        // Register the broadcast receiver with the intent filter object.
        registerReceiver(alarmReceiver, intentFilter);
    }

    /*@Override
    public void onStart(@Nullable Intent intent, int startId) {
        System.out.println("Start AlarmService");
        alarm.setAlarm(this);
        super.onStart(intent, startId);
    }
*/
    boolean t;

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        System.out.println("StartCommand AlarmService");
        alarm.setAlarm(this);
        /*Toast.makeText(this, "Служба создана",
                Toast.LENGTH_SHORT).show();
        t= true;
        while (t){
            synchronized (this){
                try {
                    wait(5000);
                    Vibrator v;
                    v=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(1000);
                    Toast.makeText(this, "Служба создана",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
*/

        //alarm.setAlarm(this);
        //return super.onStartCommand(intent, flags, startId);
        return super.onStartCommand(intent, flags, startId);
        //return START_NOT_STICKY;//Сервис будет не перезапущен после того как был убит системой
        //return START_STICKY;//Сервис будет перезапущен после того как был убит системой
    }

    @Override
    public void onDestroy() {
        System.out.println("Destroy AlarmService");
        /*unregisterReceiver(alarmReceiver);*/
        super.onDestroy();

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

    public void startRepeatingTimer(/*View view*/){
        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //(Intent) - это механизм для описания одной операции - выбрать фотографию, отправить письмо, сделать звонок, запустить браузер...
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction(ACTION_ALARM);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis()+5000);

        int repeatingTime = 1000 * 60;

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                repeatingTime, alarmIntent);
    }

    /*public void cancelRepeatingTimer(View view){
        Context context= this.getApplicationContext();
        if(alarmReceiver !=null){
            alarmReceiver.cancelAlarm(context);
        }else{
            Toast.makeText(context,"Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void onetimeTimer(View view){
        Context context= this.getApplicationContext();
        if(alarmReceiver !=null){
            alarmReceiver.setOnetimeTimer(context);
        }else{
            Toast.makeText(context,"Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }*/

}
