package com.example.notesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowInsets;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    EditText editTextNote;
    ArrayList<String> notes;
    SharedPreferences sharedPreferences;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        sharedPreferences = this.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);

        editTextNote = findViewById(R.id.editTextNote);

        try {
            notes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString(getString(R.string.notes), ObjectSerializer.serialize(new ArrayList<String>())));
            position = sharedPreferences.getInt(getString(R.string.notesPosition), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        editTextNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                notes.set(position, String.valueOf(editTextNote.getText()));
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            sharedPreferences.edit().putString(getString(R.string.notes), ObjectSerializer.serialize(notes)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
