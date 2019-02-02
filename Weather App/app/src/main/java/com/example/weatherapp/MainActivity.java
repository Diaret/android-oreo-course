package com.example.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    static String urlAddressP1 = "https://api.openweathermap.org/data/2.5/weather?q=";
    static String apiKey = "&appid=0f2deadf2b73446aede04cde3014e6a5"; //https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22";
    TextView weatherInfoTextView;
    EditText cityEditText;
    Button getWeatherButton;
    String cityName, urlAddress;

    public class DownloadTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            Document document;
            String jsonBody = "";
            try {
                document = Jsoup.connect(urlAddress).ignoreContentType(true).get();
                jsonBody = Jsoup.connect(urlAddress).ignoreContentType(true).execute().body();
                //Elements elements = document.select();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return (Object) jsonBody;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Log.i("JSON", o.toString());
        }
    }

    public void getWeatherButton(View view){
        try {
            cityName = URLEncoder.encode(String.valueOf(cityEditText.getText()), "UTF-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        urlAddress = urlAddressP1 + cityName + apiKey;

        DownloadTask downloadTask = new DownloadTask();
        JSONObject jsonObject;

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(cityEditText.getWindowToken(), 0);

        try {
            jsonObject = new JSONObject((String) downloadTask.execute().get());
            String weatherInfo = jsonObject.getString("weather");

            JSONArray jsonArray = new JSONArray(weatherInfo);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonTemp = jsonArray.getJSONObject(i);
                weatherInfoTextView.setText("abcde".substring(2, 3));
                        //jsonTemp.getString("main") + ": " +jsonTemp.getString("description"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = findViewById(R.id.cityEditText);
        getWeatherButton = findViewById(R.id.getWeatherButton);
        weatherInfoTextView = findViewById(R.id.weatherInfoTextView);

    }
}
