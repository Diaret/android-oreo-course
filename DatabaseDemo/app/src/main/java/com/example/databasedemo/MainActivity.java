package com.example.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase myDB = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS events (name VARCHAR, year INT(4))");
        //myDB.execSQL("INSERT INTO events (name, year) VALUES ('Millenium', 2000)");
        //myDB.execSQL("INSERT INTO events (name, year) VALUES ('Nick started teaching', 2014)");

        Cursor cursor = myDB.rawQuery("SELECT * FROM events", null);
        int nameIndex = cursor.getColumnIndex("name");
        int yearIndex = cursor.getColumnIndex("year");
        cursor.moveToFirst();
        while (cursor!=null){
            Log.i("name", cursor.getString(nameIndex));
            Log.i("year", String.valueOf(cursor.getInt(yearIndex)));
            cursor.moveToNext();
        }


    }
}
