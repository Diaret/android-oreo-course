package com.example.jsondemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Document document;
    TextView textView;
    JSONObject jsonObject;
    static String urlAddress = "https://samples.openweathermap.org/data/2.5/weather?q=Minsk&appid=b6907d289e10d714a6e88b30761fae22"; //https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22";
    String jsonBody;
    JSONArray jsonArray;

    public class DownloadTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                document = Jsoup.connect(urlAddress).ignoreContentType(true).get();
                jsonBody = Jsoup.connect(urlAddress).ignoreContentType(true).execute().body();
                //Elements elements = document.select();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return (Object) document.html();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Log.i("JSON", o.toString());
            try {
                jsonObject = new JSONObject(jsonBody);
                String weatherInfo = jsonObject.getString("weather");
                Log.i("weather content", weatherInfo);
                jsonArray = new JSONArray(weatherInfo);

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonTemp = jsonArray.getJSONObject(i);
                    Log.i("main", jsonTemp.getString("main"));
                    Log.i("description", jsonTemp.getString("description"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        DownloadTask downloadTask = new DownloadTask();
        try {
            textView.setText((CharSequence) downloadTask.execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
