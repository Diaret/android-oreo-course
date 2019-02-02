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

        try {
            SQLiteDatabase myDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            myDB.execSQL("DELETE FROM users");
            myDB.execSQL("INSERT INTO users (name, age) VALUES ('Nick', 29)");
            myDB.execSQL("INSERT INTO users (name, age) VALUES ('Marta', 33)");
            myDB.execSQL("INSERT INTO users (name, age) VALUES ('Daniel', 12)");
            myDB.execSQL("INSERT INTO users (name, age) VALUES ('Tomas', 43)");
            myDB.execSQL("INSERT INTO users (name, age) VALUES ('Dave', 35)");

            Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE age >= 18 AND name LIKE '%a%' LIMIT 2", null);
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", String.valueOf(cursor.getInt(ageIndex)));
                cursor.moveToNext();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }


    }
}
