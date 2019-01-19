package com.example.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        final ArrayList<String> familyList = new ArrayList<String>();
        familyList.add("Father");
        familyList.add("Mother");
        familyList.add("Son");
        familyList.add("Daugher");

        ArrayAdapter<String> adapterView = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, familyList);
        listView.setAdapter(adapterView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hello, " + familyList.get(position) + "!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
