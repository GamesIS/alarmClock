package ilku.ru.alarmclock.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import ilku.ru.alarmclock.R;
import ilku.ru.alarmclock.service.AlarmService;

public class AllClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clock);

        Intent intent = new Intent(this, AlarmService.class);
        startService(intent);
        //intent.

        //Button plus new activity listener
        findViewById(R.id.buttonPlus).setOnClickListener((buttonPlus)->{
            Intent createNewClock = new Intent(AllClockActivity.this, NewClockActivity.class);
            startActivity(createNewClock);
        });

    }


}
