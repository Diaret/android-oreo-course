package com.example.dmitrij.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int guessNumber;

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int quessNumber) {
        this.guessNumber = quessNumber;
    }

    public void initializeRandomNumber(){
        setGuessNumber((int)(Math.random() * 20));
    }

    public void quessNumberFunction (View view){
        EditText numberEditText = findViewById(R.id.numberEditText);
        int a = Integer.parseInt(numberEditText.getText().toString());
        int guessNumber = getGuessNumber();
        if (a < guessNumber) {
            Toast.makeText(this, "Higher!", Toast.LENGTH_SHORT).show();
        } else if (a > guessNumber) {
            Toast.makeText(this, "Lower!", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "You guess it right! Try 1 more!", Toast.LENGTH_LONG).show();
            initializeRandomNumber();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRandomNumber();
    }
}
