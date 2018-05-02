package ilku.ru.alarmclock.model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Calendar;

import ilku.ru.alarmclock.activity.AllClockActivity;

public class Alarm extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        System.out.println("Test");
        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        //Осуществляем блокировку
        wl.acquire();//Это нужно чтобы не погас экран

        /*//Здесь можно делать обработку.
        Bundle extras= intent.getExtras();
        StringBuilder msgStr=new StringBuilder();

        if(extras!=null && extras.getBoolean(ONE_TIME, Boolean.FALSE)){
        //проверяем параметр ONE_TIME, если это одиночный будильник,
        //выводим соответствующее сообщение.
            msgStr.append("Одноразовый будильник: ");
        }
        *//*Format formatter=new SimpleDateFormat("hh:mm:ss a");

        String d

        msgStr.append(formatter.format(newDate()));*/

        /*this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);*/

        Intent intentone = new Intent(context.getApplicationContext(), AllClockActivity.class);
        intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentone);

        /*Intent i = new Intent();
        i.setClassName("ilku.ru.alarmclock.activity", "ilku.ru.alarmclock.activity.AllClockActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/


        Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();

        Vibrator v;
        v=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(3000);

        //Разблокируем поток.
        wl.release();
    }

    public void setAlarm(Context context)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis()+5000);

        int repeatingTime = 1000 * 60;


        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), repeatingTime, pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}