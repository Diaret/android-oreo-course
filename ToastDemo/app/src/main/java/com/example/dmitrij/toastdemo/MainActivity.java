package com.example.dmitrij.toastdemo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        Log.i("info", "button is pressed");

        ImageView catImageView = (ImageView) findViewById(R.id.catImageView);

        int cat01 = R.drawable.cat01;
        catImageView.setImageResource(cat01);

        Toast.makeText(this, "Nice cat it is!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
