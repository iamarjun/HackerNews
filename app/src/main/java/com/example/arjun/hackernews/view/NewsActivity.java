package com.example.arjun.hackernews.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arjun.hackernews.R;
import com.example.arjun.hackernews.data.News;
import com.example.arjun.hackernews.data.NewsDataSource;
import com.example.arjun.hackernews.data.NewsDownloadTask;
import com.example.arjun.hackernews.data.OnDownloadComplete;
import com.example.arjun.hackernews.logic.Controller;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements ViewInterface, OnDownloadComplete {
    private static final String TAG = "NewsActivity";

    private ArrayList<News> newsList;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private Controller controller;
    private static Context contextOfApplication;
    private NewsDownloadTask newsDownloadTask;
//    private NewsDataSource newsDataSource;

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    public void onDownloadComplete() {
        Log.d(TAG, "onDownloadComplete: Callback Success");
        controller = new Controller(this, newsDownloadTask);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        contextOfApplication = getApplicationContext();

        recyclerView = findViewById(R.id.recyclerView);
        layoutInflater = getLayoutInflater();
        newsDownloadTask = new NewsDownloadTask(this);
        newsDownloadTask.execute("https://newsapi.org/v2/top-headlines?sources=hacker-news&apiKey=0479148276a84cf5bdb90c9e04801f60");

//        newsDataSource.getNewsDataSource("https://newsapi.org/v2/top-headlines?sources=hacker-news&apiKey=0479148276a84cf5bdb90c9e04801f60");
    }

    @Override
    public void startNewsWebViewActivity(String newsURL) {

        Intent intent = new Intent(getApplicationContext(), NewsWebView.class );
        intent.putExtra("URL", newsURL);
        startActivity(intent);

    }

    @Override
    public void setAdapterAndView(ArrayList<News> newsList) {

        this.newsList = newsList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView time;
            private TextView title;
            private ViewGroup viewGroup;

            ViewHolder(View itemView) {
                super(itemView);

                this.time = itemView.findViewById(R.id.time);
                this.title = itemView.findViewById(R.id.title);
                this.viewGroup = itemView.findViewById(R.id.recyclerViewList);

                this.viewGroup.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

                News news = newsList.get(this.getAdapterPosition());

                controller.onNewsItemClicked(news);

            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.activity_recyclerviewlist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            News currentNews = newsList.get(position);

            holder.time.setText(currentNews.getTime());
            holder.title.setText(currentNews.getTitle());

        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

    }


}