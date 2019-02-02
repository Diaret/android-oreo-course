package com.example.multipleactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView friendsListView;
    ArrayList<String> friendsList = new ArrayList<String>();

    public void goToNext(View view){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("age", 28);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friendsListView = findViewById(R.id.friendsListView);

        friendsList.add("Fr1");
        friendsList.add("Fr2");
        friendsList.add("Fr3");
        friendsList.add("Fr4");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, friendsList);
        friendsListView.setAdapter(arrayAdapter);

        friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("friendName", friendsList.get(position));
                startActivity(intent);
            }
        });
    }
}
