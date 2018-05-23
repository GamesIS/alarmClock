package ilku.ru.alarmclock.model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

@Entity
public class Alarm {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "pending_intent")
    private String pendingIntent; //TODO: save json

    @ColumnInfo(name = "sound_path")
    private String soundPath;

    @ColumnInfo(name = "alarm_time")
    private long alarmTime;

    @ColumnInfo(name = "repeat_time")
    private long repeatTime;

    //We can't save boolean value into sqllite then we wil save 0 as false and 1 as true
    @ColumnInfo
    private int isSoundIncrease;

    public Alarm() {
    }

    public Alarm(String pendingIntent, String soundPath, long alarmTime, long repeatTime, int isSoundIncrease) {
        this.pendingIntent = pendingIntent;
        this.soundPath = soundPath;
        this.alarmTime = alarmTime;
        this.repeatTime = repeatTime;
        this.isSoundIncrease = isSoundIncrease;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPendingIntent() {
        return pendingIntent;
    }

    public void setPendingIntent(String pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }

    public long getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(long alarmTime) {
        this.alarmTime = alarmTime;
    }

    public long getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(long repeatTime) {
        this.repeatTime = repeatTime;
    }

    public int getIsSoundIncrease() {
        return isSoundIncrease;
    }

    public void setIsSoundIncrease(int isSoundIncrease) {
        this.isSoundIncrease = isSoundIncrease;
    }
}