package ilku.ru.alarmclock.model;

import android.app.PendingIntent;

public class Alarm{
    private int id;
    private PendingIntent pendingIntent;
    private String soundPath;
    private int hours;
    private int minutes;
    private int repeatTime;
    private boolean isSoundIncrease;

    public Alarm(int id, PendingIntent pendingIntent, String soundPath, int hours, int minutes, int repeatTime, boolean isSoundIncrease) {
        this.id = id;
        this.pendingIntent = pendingIntent;
        this.soundPath = soundPath;
        this.hours = hours;
        this.minutes = minutes;
        this.repeatTime = repeatTime;
        this.isSoundIncrease = isSoundIncrease;
    }

    public Alarm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }

    public boolean isSoundIncrease() {
        return isSoundIncrease;
    }

    public void setSoundIncrease(boolean soundIncrease) {
        isSoundIncrease = soundIncrease;
    }
}