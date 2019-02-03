package com.example.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey //(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public String getWord() {
        return this.mWord;
    }

    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    }
}
