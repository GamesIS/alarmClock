package ilku.ru.alarmclock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ilku.ru.alarmclock.R;

public class NewClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_clock);

        findViewById(R.id.buttonDone).setOnClickListener((view)->{
            Intent addNewClock = new Intent(NewClockActivity.this, AllClockActivity.class);
            startActivity(addNewClock);
        });
    }
}
