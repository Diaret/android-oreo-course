package com.example.newsreaderappwithandroidx;

import android.widget.ListView;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NewsDao {

    @Insert
    void insert (News news);

    @Query("DELETE FROM news_table")
    void deleteAll();

    @Query("SELECT * from news_table ORDER BY score DESC LIMIT 500")
    LiveData<List<News>> getALLNews();

}
