package ilku.ru.alarmclock.model;

public class Alarm {
    private int id;
    String soundPath;
    String hours;
    String minutes;
    int repeatTime;
    boolean isSoundIncrease;

    public Alarm() {
    }

    public Alarm(int id, String soundPath, String hours, String minutes, int repeatTime, boolean isSoundIncrease) {
        this.id = id;
        this.soundPath = soundPath;
        this.hours = hours;
        this.minutes = minutes;
        this.repeatTime = repeatTime;
        this.isSoundIncrease = isSoundIncrease;
    }
}