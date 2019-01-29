package com.example.memorableplaces;

import com.google.android.gms.maps.model.LatLng;

class PlaceInfo {
    private String place;
    private LatLng latLng;

    PlaceInfo(String place, LatLng latLng) {
        this.place = place;
        this.latLng = latLng;
    }

    String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
