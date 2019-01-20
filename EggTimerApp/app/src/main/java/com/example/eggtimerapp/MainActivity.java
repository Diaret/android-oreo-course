package com.example.eggtimerapp;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CountParams countParams;

    Button goButton;

    SeekBar seekBar;
    static int seekBarMax = 30;
    static int seekBarStart = 1;

    static long countDownInterval = 1000;

    TextView timeLeft;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;

    public class CountParams{
        private int interval = 60;
        int step = 5;

        boolean counterIsActive = false;

        public boolean isCounterIsActive() {
            return counterIsActive;
        }

        public void setCounterIsActive(boolean counterIsActive) {
            this.counterIsActive = counterIsActive;
        }

        private int curProgress;
        private int totalSeconds;

        public int getTotalSeconds() {
            return totalSeconds;
        }

        private int curMinutes;
        private int curSeconds;

        private String timerText;
        public String getTimerText() {
            return timerText;
        }

        public void reset(){
            this.curProgress = seekBarStart;
            this.totalSeconds = seekBarStart * step;
            recalculate(this.totalSeconds);

            goButton.setText("GO!");
            counterIsActive = false;
            countDownTimer.cancel();
            seekBar.setEnabled(true);
        }

        public void recalculate(int secondsLeft){
            this.totalSeconds = secondsLeft;
            this.curMinutes = this.totalSeconds / interval;
            this.curSeconds = this.totalSeconds % interval;
            timerText = String.valueOf(this.curMinutes) + ":" + String.format("%02d", this.curSeconds);
        }

        public CountParams(int curProgress) {
            this.curProgress = curProgress;
            this.totalSeconds = curProgress * step;
            recalculate(this.totalSeconds);
            counterIsActive = false;

        }
    }

    public void startTimer(View view){
        if (countParams.isCounterIsActive()){
            countParams.reset();
        } else {

            seekBar.setEnabled(false);
            goButton.setText("STOP");
            countParams.setCounterIsActive(true);
            //Start the timer
            countDownTimer = new CountDownTimer(countParams.getTotalSeconds() * 1000, countDownInterval) {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void onTick(long millisecondsUntilDone) {
                    Log.i("Seconds left!", String.valueOf(millisecondsUntilDone / 1000));
                    countParams.recalculate((int) (millisecondsUntilDone / 1000));
                    timeLeft.setText(countParams.getTimerText());
                }

                public void onFinish() {
                    Log.i("We're done!", "No more countdown");
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    countParams.reset();
                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.button);

        countParams = new CountParams(seekBarStart);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(seekBarMax);
        seekBar.setProgress(seekBarStart);

        timeLeft = findViewById(R.id.textView);
        timeLeft.setText(countParams.getTimerText());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countParams.recalculate(progress * countParams.step);
                timeLeft.setText(countParams.getTimerText());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
