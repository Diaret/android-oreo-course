package com.example.dmitrij.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void testNumber(View view){
        Log.i("info", "button was pressed");
        EditText numberEditText = findViewById(R.id.numberEditText);
        Number num = new Number();
        String str;
        num.setNumber(Integer.parseInt(numberEditText.getText().toString()));
        if (num.isTriangular()){
            if (num.isSquare()){
                str = "is both triangular and square";
            } else
                str = "is triangular";
        }   else if (num.isSquare()){
                str = "is square";
            } else{
                str = "is nor triangular nor square";
        }
        Toast.makeText(this, "The number " + num.getNumber() + " " + str, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
