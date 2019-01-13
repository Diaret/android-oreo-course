package com.example.dmitrij.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public void convertFunction(View view){
        Log.i("info", "button is pressed");

        DecimalFormat df2 = new DecimalFormat(".##");

        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);

        Toast.makeText(this, "It will be " + df2.format(Double.parseDouble(amountEditText.getText().toString())*1.3) + "$", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
