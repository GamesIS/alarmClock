package ilku.ru.alarmclock.receive;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import ilku.ru.alarmclock.activity.AlarmClockActivity;

public class AlarmReceiver extends BroadcastReceiver {

    public static final String TAG = "AlarmReceiver";

    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
   /*     //Log.d("TEST2", "TEST2");
        Log.d(TAG, "onReceive");
        Log.d(TAG, "action = " + intent.getAction());
        Log.d(TAG, "extra = " + intent.getStringExtra("extra"));
        System.out.println("Test");*/
        System.out.println("action = " + intent.getAction() + " " + "extra = " +intent.getDataString());
        //System.out.println("action = " + intent.getAction() + " " + "extra = " + intent.getStringExtra("extra"));

        if(!AlarmClockActivity.isActive){
            Intent intentone = new Intent(context.getApplicationContext(), AlarmClockActivity.class);//Старт активти

            intentone.addFlags(/*Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | */Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentone);
        }







    }

    public void startRepeatingTimer(Context context){
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //(Intent) - это механизм для описания одной операции - выбрать фотографию, отправить письмо, сделать звонок, запустить браузер...
        Intent intent = new Intent("ilku.ru.alarmclock.receive.ALARM");
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis()+5000);

        int repeatingTime = 1000 * 60;

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                repeatingTime, pendingIntent);
    }

    public void cancelAlarm(Context context)
    {
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //(Intent) - это механизм для описания одной операции - выбрать фотографию, отправить письмо, сделать звонок, запустить браузер...
        Intent intent = new Intent("ilku.ru.alarmclock.receive.ALARM");
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmMgr.cancel(pendingIntent);//Отменяем будильник, связанный с интентом данного класса
    }
}
