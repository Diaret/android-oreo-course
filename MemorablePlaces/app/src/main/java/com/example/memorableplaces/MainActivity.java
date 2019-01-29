package com.example.memorableplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView placesListView;
    ArrayList<PlaceInfo> placeInfoArrayList;

    public void onResume() {
        super.onResume();
        MyApplication app = (MyApplication) getApplication();
        placeInfoArrayList = app.placeInfoArrayList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, app.getPlaces());
        placesListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placesListView = findViewById(R.id.placesListView);
        placeInfoArrayList = new ArrayList<>();
        placeInfoArrayList.add(new PlaceInfo(getString(R.string.newPlace), new LatLng(0,0)));
        final MyApplication app = (MyApplication) getApplication();
        app.setPlaceInfos(placeInfoArrayList);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, app.getPlaces());
        placesListView.setAdapter(arrayAdapter);

        final Intent intent = new Intent(getApplicationContext(), mapPlacesActivity.class);

        placesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra(getString(R.string.placeNumber), position);
                startActivity(intent);
            }
        });
    }
}
