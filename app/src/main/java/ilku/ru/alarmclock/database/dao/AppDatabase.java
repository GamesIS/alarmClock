package ilku.ru.alarmclock.database.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ilku.ru.alarmclock.model.Alarm;

@Database(entities = {Alarm.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlarmDAO getAlarmDAO();
}
