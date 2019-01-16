package com.example.sounddemo;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    public void playSound(View view){
//        Button playButton = findViewById(R.id.playButton);
        mediaPlayer.start();
    }

    public void pauseSound(View view){
//        Button playButton = findViewById(R.id.playButton);
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.hoovering);

    }



}
