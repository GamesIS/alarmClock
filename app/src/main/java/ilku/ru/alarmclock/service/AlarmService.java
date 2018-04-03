package ilku.ru.alarmclock.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Needed for background work
 * */
public class AlarmService extends IntentService {
    private long time;
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public AlarmService() {
        super("AlarmService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //long endTime = System.currentTimeMillis() + 5*1000;
        //String givenDateString = "Apr 04 00:02:30 2018";


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
        }
    }

    //TODO We take from the alarm
    private Date getAlarmDate(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /*Can be useful*/
    @Override
    public void onCreate() {
        System.out.println("Create AlarmService");
        super.onCreate();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        System.out.println("Start AlarmService");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("Destroy AlarmService");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

}
