package com.example.newsreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabase extends SQLiteOpenHelper {
    private static String dbName = "News";
    private static String tableNews = "news";

    // Database version
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, dbName, null, DATABASE_VERSION);
    }
    //in your oncreate you will write the queries to create your tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NEWS = "CREATE TABLE IF NOT EXISTS news (id INTEGER PRIMARY KEY, author VARCHAR, score INTEGER, time INTEGER, title VACHAR, type VARCHAR, url VARCHAR)";
        db.execSQL(CREATE_NEWS);
    }

    public void updateTable(SQLiteDatabase db, int id, String author, int score, int time, String title, String type, String url){
        ContentValues cv = new ContentValues();
        cv.put("id",id); //These Fields should be your String values of actual column names
        cv.put("author",author);
        cv.put("score",score);
        cv.put("time",time);
        cv.put("title",title);
        cv.put("type",type);
        cv.put("url",url);
        db.update(tableNews, cv, "_id="+id, null);
    }

    // upgrading tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop tables if exist
        db.execSQL("DROP TABLE IF EXISTS " + tableNews);
        // recreate tables
        onCreate(db);
    }
}