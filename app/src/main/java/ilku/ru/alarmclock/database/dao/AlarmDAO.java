package ilku.ru.alarmclock.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ilku.ru.alarmclock.model.Alarm;

@Dao
public interface AlarmDAO {


    //TODO: maybe public

    @Query("SELECT * FROM alarm")
    List<Alarm> getAll();

    @Query("SELECT * FROM alarm WHERE id = :id")
    Alarm getById(long id);

    @Insert
    void insert(Alarm alarm);

    @Update
    void update(Alarm alarm);

    @Delete
    void delete(Alarm alarm);
}
