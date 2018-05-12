package ilku.ru.alarmclock.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

import ilku.ru.alarmclock.R;
import ilku.ru.alarmclock.model.Alarm;

public class NewClockActivity extends AppCompatActivity {

    private SharedPreferences savedAlarmPref;
    private TimePickerDialog picker;
    private EditText alarmTime;
    private IDGenerator idGenerator = new IDGenerator();
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_clock);


        findViewById(R.id.buttonDone).setOnClickListener((view) -> {
            Intent addNewClock = new Intent(NewClockActivity.this, AllClockActivity.class);
            startActivity(addNewClock);
        });

        buildNewAlarm();
    }

    private void buildNewAlarm(){
        Alarm alarm = new Alarm();

        alarmTime = findViewById(R.id.alarmName);
        alarmTime.setInputType(InputType.TYPE_NULL);
        alarmTime.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY); // set default time by current time
            mMinute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                alarm.setHours(hourOfDay);
                alarm.setMinutes(minute);

                alarmTime.setText(hourOfDay + " : " + minute);
            }, mHour, mMinute, true);
            timePickerDialog.show();
        });
        saveThisAlarm(alarm);
    }

    private void saveThisAlarm(Alarm thisAlarm) {
        savedAlarmPref = getPreferences(MODE_PRIVATE); // set access mode only for this app
        Editor editor = savedAlarmPref.edit(); // started editing data
        editor.putString(idGenerator.getSetID().toString(), new Gson().toJson(thisAlarm));
        editor.commit(); // commit new data
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    public Alarm getAlarmByID(int id) {
        savedAlarmPref = getPreferences(MODE_PRIVATE);
        String savedAlarm = savedAlarmPref.getString(new Integer(id).toString(), "");
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
        return new Gson().fromJson(savedAlarm, Alarm.class);
    }

    class IDGenerator {
        private SharedPreferences preferences;
        private static final String ID_FILE_NAME = "alardIDs";

        public Integer getSetID() {
            preferences = getPreferences(MODE_PRIVATE);
            int id = preferences.getInt(ID_FILE_NAME, 000);

            Editor editor = preferences.edit();
            editor.putInt(ID_FILE_NAME, id++);
            editor.commit();
            return id;
        }

    }
}
