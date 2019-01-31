package com.example.languagepreferencies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    String language;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.english:
                setLanguage(getString(R.string.english));
                return true;
            case R.id.spanish:
                setLanguage(getString(R.string.spanish));
                return true;
            default:
                return false;
        }

    }

    public void setLanguage(String language) {
        sharedPreferences.edit().putString(getString(R.string.language), language).apply();
        textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.languagepreferencies", Context.MODE_PRIVATE);
        textView = findViewById(R.id.textView);
        language = sharedPreferences.getString(getString(R.string.language), "");

        if (language.equals("")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like to use?")
                    .setPositiveButton(getString(R.string.english), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage(getString(R.string.english));
                        }
                    })
                    .setNegativeButton(getString(R.string.spanish), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage(getString(R.string.spanish));
                        }
                    })
                    .show();

        } else {
            textView.setText(language);
        }
    }
}
