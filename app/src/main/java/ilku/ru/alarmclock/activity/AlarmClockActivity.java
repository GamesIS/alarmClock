package ilku.ru.alarmclock.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ilku.ru.alarmclock.R;

public class AlarmClockActivity extends Activity {
    public static boolean isActive = false;

    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;
    private AlarmThread alarmThread;
    private Vibrator vibrator;
    private MediaPlayer mMediaPlayer;

    private final static int MAX_VOLUME = 100;
    private final static int VOLUME_STEP = 15;//Шаг увеличения
    private int CURRENT_VOLUME = 10;
    private boolean soundIncrease = true;//Нарастающий звук

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager pm=(PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        //Осуществляем блокировку
        wl.acquire();//Это нужно чтобы не погас экран

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED// Выводим поверх экрана блокировки
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);

        setContentView(R.layout.activity_alarm_clock);


        vibrator =(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        //Разблокируем поток.
        wl.release();

        findViewById(R.id.buttonOff).setOnClickListener((buttonOffAlarm)->{
            TextView textView = findViewById(R.id.textView);
            textView.setText("Ты проклят");
            cancelAlarm();
            vibrator.vibrate(1000);
            if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            alarmThread.interrupt();
            finish();
        });

        findViewById(R.id.buttonDefer).setOnClickListener((buttonIgnor)->{
            TextView textView = findViewById(R.id.textView);
            textView.setText("Игнор");
            vibrator.vibrate(1000);
            if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();

            }
            alarmThread.interrupt();
            finish();
        });

        playSound(this, getAlarmUri());

        alarmThread = new AlarmThread();
        alarmThread.start();
    }

    public void cancelAlarm()
    {
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //(Intent) - это механизм для описания одной операции - выбрать фотографию, отправить письмо, сделать звонок, запустить браузер...
        Intent intent = new Intent("ilku.ru.alarmclock.receive.ALARM");
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmMgr.cancel(pendingIntent);//Отменяем будильник, связанный с интентом данного класса

        pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmMgr.cancel(pendingIntent);//Отменяем будильник, связанный с интентом данного класса

    }

    private void playSound(Context context, Uri alert) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setLooping(true);//
        setVolume(CURRENT_VOLUME);
        try {
            mMediaPlayer.setDataSource(context, alert);
            final AudioManager audioManager = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch (IOException e) {
            System.out.println("OOPS");
        }
    }

    private Uri getAlarmUri() {
        Uri alert = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alert == null) {
            alert = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alert == null) {
                alert = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }

    private class AlarmThread extends Thread{
        @Override
        public void run() {
            //isActive = true;
            final long[] pattern = {0, 2000, 1000};
            for(int i = 0; i < 20; i++){
                if(!isInterrupted()){
                    vibrator.vibrate(pattern, -1);
                    if (soundIncrease && CURRENT_VOLUME < MAX_VOLUME){
                        CURRENT_VOLUME += VOLUME_STEP;
                        if(CURRENT_VOLUME > MAX_VOLUME) CURRENT_VOLUME = MAX_VOLUME;
                    }
                    setVolume(CURRENT_VOLUME);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread disable");
                        Toast.makeText(AlarmClockActivity.this,"Поток завершен аварийно", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else return;
            }
            if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            finish();
        }
    }
    private void setVolume(int volume){
        float vol = (float) (1 - (Math.log(MAX_VOLUME - volume) / Math.log(MAX_VOLUME)));
        mMediaPlayer.setVolume(vol, vol);
    }
}
