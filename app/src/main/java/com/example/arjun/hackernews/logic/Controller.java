package com.example.arjun.hackernews.logic;

import android.util.Log;

import com.example.arjun.hackernews.data.News;
import com.example.arjun.hackernews.data.NewsSourceInterface;
import com.example.arjun.hackernews.view.ViewInterface;

public class Controller {
    private static final String TAG = "Controller";

    private ViewInterface viewInterface;
    private NewsSourceInterface newsSourceInterface;

    public Controller(ViewInterface viewInterface, NewsSourceInterface newsSourceInterface) {

        this.viewInterface = viewInterface;
        this.newsSourceInterface = newsSourceInterface;
        getListFromNewsSource();

    }

    public void getListFromNewsSource()  {

        Log.d(TAG, "getListFromNewsSource: Calling for the data");
        viewInterface.setAdapterAndView(newsSourceInterface.getNews());
    }

    public void onNewsItemClicked(News news) {

        viewInterface.startNewsWebViewActivity(news.getNewsURL());
    }
}