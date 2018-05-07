package ilku.ru.alarmclock.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

import ilku.ru.alarmclock.R;
import ilku.ru.alarmclock.service.AlarmService;

public class AllClockActivity extends AppCompatActivity {

    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;

    static boolean isActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clock);

        Intent intent = new Intent(this, AlarmService.class);
        startService(intent);
        startRepeatingTimer();

        findViewById(R.id.buttonPlus).setOnClickListener((buttonPlus) -> {
            Intent createNewClock = new Intent(AllClockActivity.this, NewClockActivity.class);
            startActivity(createNewClock);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    public void startRepeatingTimer() {
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //(Intent) - это механизм для описания одной операции - выбрать фотографию, отправить письмо, сделать звонок, запустить браузер...
        Intent intent = new Intent("ilku.ru.alarmclock.receive.ALARM");
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        PendingIntent p2 = PendingIntent.getBroadcast(this, 1, intent, 0);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + 5000);

        int repeatingTime = 1000 * 60;

        //alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
        //        repeatingTime, pendingIntent);
        /*alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                repeatingTime+10000, p2);*/
    }
}
