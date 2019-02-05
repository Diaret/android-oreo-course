package com.example.newsreaderappwithandroidx;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository mRepository;
    private LiveData<List<News>> mAllNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new NewsRepository(application);
        mAllNews = mRepository.getAllNews();
    }

    LiveData<List<News>> getAllNews() { return mAllNews; }
    public void insert(News news) { mRepository.insert(news); }
}
