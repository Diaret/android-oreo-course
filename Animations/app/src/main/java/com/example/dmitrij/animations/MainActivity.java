package com.example.dmitrij.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public void fade(View view){
        Log.i ("info","Image is tapped");

        ImageView bartImageView = findViewById(R.id.bartImageView);
       // bartImageView.animate().translationXBy(-1000).setDuration(2000);
        //bartImageView.animate().rotation(1000).alpha(0).setDuration(1000);
        bartImageView.animate().scaleX(0.5f).setDuration(1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bartImageView = findViewById(R.id.bartImageView);
        bartImageView.setX(-1000);
        bartImageView.animate().translationXBy(1000).rotation(360*5).setDuration(1000);

    }
}
