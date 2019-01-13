package com.example.dmitrij.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction (View view){
        Log.i ("info", "Button is pressed");

        EditText loginEditText = (EditText) findViewById(R.id.loginEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Log.i ("status", "User " + loginEditText.getText().toString() + " has logged in");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
