package ilku.ru.alarmclock.model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class Alarm {
    private int id;
    private PendingIntent pendingIntent;
    private String soundPath;
    private String hours;
    private String minutes;
    private int repeatTime;
    private boolean isSoundIncrease;

    public Alarm(int id, PendingIntent pendingIntent, String soundPath, String hours, String minutes, int repeatTime, boolean isSoundIncrease) {
        this.id = id;
        this.pendingIntent = pendingIntent;
        this.soundPath = soundPath;
        this.hours = hours;
        this.minutes = minutes;
        this.repeatTime = repeatTime;
        this.isSoundIncrease = isSoundIncrease;
    }

}