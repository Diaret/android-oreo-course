package com.example.notesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> notes;
    private ListView listViewNotes;
    private SharedPreferences sharedPreferences;
    private boolean isNotAFirstCall;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.listViewNotes);
        initializeMainScreen();

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToNotes(notes.get(position), position);
            }
        });

        listViewNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                createDialog(position);
                return true;
            }

        });
    }

    private void createDialog(final int position) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.alertTitle))
                .setMessage(getString(R.string.alertMessage))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notes.remove(position);
                        resetAdapter();
                    }
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }

    private void initializeMainScreen() {
        sharedPreferences = this.getSharedPreferences("com.example.notesapp", MODE_PRIVATE);
        notes = new ArrayList<>();
        notes.add(getString(R.string.exampleNote));
        resetAdapter();
    }

    private void resetAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listViewNotes.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.addNote:
                goToNotes(getString(R.string.empty), notes.size());
                return true;
            default:
                return false;
        }
    }

    private void goToNotes(String notesText, int position) {
        if (position >= notes.size()) {
            notes.add(notesText);
        }

        Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
        //intent.putExtra("friendName", friendsList.get(position));
        try {
            sharedPreferences.edit().putString(getString(R.string.notes), ObjectSerializer.serialize(notes)).apply();
            sharedPreferences.edit().putInt(getString(R.string.notesPosition), position).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = this.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
        if (isNotAFirstCall) {
            try {
                notes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(getString(R.string.notes), ObjectSerializer.serialize(new ArrayList<String>())));
                resetAdapter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            isNotAFirstCall = true;
        }

    }
}
