package com.example.mapandlocationdemo;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Location {
    private static JSONObject getLocationInfo(String address)
    {

        StringBuilder stringBuilder = new StringBuilder();
        try {

            address = address.replaceAll(" ","%20");

            HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false");
            HttpClient client = new DefaultHttpClient();
            HttpResponse response;
            stringBuilder = new StringBuilder();


            response = client.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
            Log.i("ClientProtocolException", e.toString());
        } catch (IOException e) {

            Log.i("IOException", e.toString());
        }


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.i("JSONException", e.toString());
        }

        return jsonObject;
    }

    private static double[] getLatLong(JSONObject jsonObject){
        double longitute, latitude;

        try {

            longitute = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
            Log.i("Log1", longitute+"");
            latitude = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");
            Log.i("lat1", latitude+"");
        } catch (JSONException e) {

            longitute = 0;
            latitude = 0;
            Log.i("getLatLong", e.toString());
            return null;
        }

        return new double[]{longitute, latitude};
    }

    public static double[] getLatLongByAddress(String address){
        JSONObject jsonObject = getLocationInfo(address);
        return getLatLong(jsonObject);
    }
}
