package com.example.memorableplaces;

import android.app.Application;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MyApplication extends Application {
    private ArrayList<PlaceInfo> placeInfoArrayList;

    public ArrayList<PlaceInfo> placeInfoArrayList() {
        return placeInfoArrayList;
    }

    public void setPlaceInfos(ArrayList<PlaceInfo> placeInfoArrayList) {
        this.placeInfoArrayList = placeInfoArrayList;
    }

    public ArrayList<String> getPlaces(){
        ArrayList<String> temp = new ArrayList<>();
        for (PlaceInfo placeInfo: placeInfoArrayList
             ) {
            temp.add(placeInfo.getPlace());
        }
        return temp;
    }
}
