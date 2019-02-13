package com.example.newsreaderappwithandroidx;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {News.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();

    private static volatile NewsRoomDatabase INSTANCE;



    static NewsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsRoomDatabase.class, "news_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private String TAG = MainActivity.class.getSimpleName();
        private final NewsDao mDao;

        PopulateDbAsync(NewsRoomDatabase db) {
            mDao = db.newsDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
            String jsonStr = sh.makeServiceCall(url);

            String urlItemP1 = "https://hacker-news.firebaseio.com/v0/item/";
            String urlItemP2 = ".json?print=pretty";
            String jsonStrItem;

            Log.e("just", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    // looping through All News
                    for (int i = 0; i < jsonArray.length(); i++) {
                        int id  = (Integer) jsonArray.get(i);

                        jsonStrItem = sh.makeServiceCall(urlItemP1 + Integer.toString(id) + urlItemP2);
                        if (jsonStrItem != null) {
                            JSONObject jsonObj = new JSONObject(jsonStrItem);
                            String title = jsonObj.getString("title");
                            String itemUrl = jsonObj.getString("url");
                            int score = jsonObj.getInt("score");

                            News news = new News(id, title, itemUrl, score);
                            mDao.insert(news);
                        }

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    })
                    ;

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG).show();
//                    }
//                })
                ;
            }

            return null;

//            //todo replace
//            for (int i = 1; i <= 10; i++) {
//                News news = new News(i, "Title " + String.valueOf(i), "https://www.youtube.com/watch?v=cDF_yBCflXk&t=1706s", 300 + i);
//                mDao.insert(news);
//            }
        }
    }


}
