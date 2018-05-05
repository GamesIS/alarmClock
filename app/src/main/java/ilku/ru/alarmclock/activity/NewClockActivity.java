package ilku.ru.alarmclock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TimePicker;

import ilku.ru.alarmclock.R;
import ilku.ru.alarmclock.model.Alarm;

public class NewClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_clock);

        Alarm alarm = new Alarm();

        findViewById(R.id.buttonDone).setOnClickListener((view) -> {
            Intent addNewClock = new Intent(NewClockActivity.this, AllClockActivity.class);
            startActivity(addNewClock);
        });

        TimePicker simpleTimePicker = findViewById(R.id.alarmTimePicker); // initiate a time picker
        Boolean mode = simpleTimePicker.is24HourView(); // check the current mode of the time picker
        simpleTimePicker.setOnTimeChangedListener((timePickerView, hour, minute)->{

        });
    }
}
