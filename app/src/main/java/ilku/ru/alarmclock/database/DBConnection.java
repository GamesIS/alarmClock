package ilku.ru.alarmclock.database;

import android.app.Application;
import android.arch.persistence.room.Room;

import ilku.ru.alarmclock.database.dao.AppDatabase;

public class DBConnection extends Application {
    private static DBConnection dbConnection;
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        dbConnection = this;
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    public static DBConnection getInstance(){
        return dbConnection;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
