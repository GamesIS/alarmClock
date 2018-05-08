package ilku.ru.alarmclock.model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class Alarm {
    private PendingIntent pendingIntent;
    private String soundPath;
    private Calendar calendar;
    private int repeatTime;
    private boolean isSoundIncrease;

    public Alarm(PendingIntent pendingIntent, String soundPath, Calendar calendar, int repeatTime, boolean isSoundIncrease) {
        this.pendingIntent = pendingIntent;
        this.soundPath = soundPath;
        this.calendar = calendar;
        this.repeatTime = repeatTime;
        this.isSoundIncrease = isSoundIncrease;
    }
    public boolean isSoundIncrease() {
        return isSoundIncrease;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }

    public long getTimeInMillis(){
        throw new UnsupportedOperationException("Метод не реализован");
    }
}