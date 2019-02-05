package com.example.newsreaderappwithandroidx;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    class NewsViewHolder extends RecyclerView.ViewHolder{
        private final TextView titleTextView;
        private final TextView scoreTextView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.newsTitleTextView);
            scoreTextView = itemView.findViewById(R.id.newsScoreTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Element " + titleTextView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<News> mNews;

    NewsListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater
                .inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        if (mNews != null) {
            News current = mNews.get(position);
            holder.titleTextView.setText(current.getTitle());
            holder.scoreTextView.setText(String.valueOf(current.getScore()));
        } else {
            // Covers the case of data not being ready yet.
            holder.titleTextView.setText("No news");
            holder.scoreTextView.setText("-");
        }
    }

    void setNews(List<News> news){
        mNews = news;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNews != null)
            return mNews.size();
        else return 0;
    }
}
