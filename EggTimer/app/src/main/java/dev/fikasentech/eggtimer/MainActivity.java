package dev.fikasentech.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button startTimerButton;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        startTimerButton = findViewById(R.id.startTimerButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(600);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int seekBarValue, boolean b) {
                updateTimer(seekBarValue);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekbar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });
    }

    public void updateTimer(int seekBarValue) {
        int minutes = seekBarValue / 60;
        int seconds = seekBarValue - (minutes * 60);

        String secondString = Integer.toString(seconds);
//                if (seconds < 10) {
//                    secondString = "0" + secondString;
//                }
        if(seconds <= 9) {
            secondString = "0" + secondString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void resetTimer() {
        timerTextView.setText("00:30");
        timerSeekBar.setProgress(30);
        startTimerButton.setText("Start Timer");
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        counterIsActive = false;
    }
    public void onStartTimer(View v) {

        if (counterIsActive) {
            resetTimer();
//            timerTextView.setText("00:30");
//            timerSeekBar.setProgress(30);
//            startTimerButton.setText("Start Timer");
//            timerSeekBar.setEnabled(true);
//            countDownTimer.cancel();
//            counterIsActive = false;
        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startTimerButton.setText("Stop Timer");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000); //miliseconds to seconds
                }

                @Override
                public void onFinish() {
                    Log.i("Timer", "Finished");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timeup);
                    mediaPlayer.start();
                    resetTimer();
//                    counterIsActive = false;
                }
            }.start();
        }

    }
}
