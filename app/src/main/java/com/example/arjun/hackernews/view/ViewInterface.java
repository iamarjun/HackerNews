package com.example.arjun.hackernews.view;

import com.example.arjun.hackernews.data.News;

import java.util.ArrayList;

public interface ViewInterface {

    void startNewsWebViewActivity(String newsURL);

    void setAdapterAndView(ArrayList<News> newsList);
}
