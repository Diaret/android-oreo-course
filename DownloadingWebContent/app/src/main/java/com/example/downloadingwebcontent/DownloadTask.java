package com.example.downloadingwebcontent;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL((String) objects[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();
            while (data != -1){
                char current = (char) data;
                result += current;
                data = inputStreamReader.read();
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }

    }
}
