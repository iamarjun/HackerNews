package com.example.arjun.hackernews.view;

import android.view.View;

import com.example.arjun.hackernews.data.News;

import java.util.ArrayList;

public interface ViewInterface {

    void startDetailActivity(String dateAndTime, String message, int colorResource, View viewRoot);

    void setAdapterAndView(ArrayList<News> newsList);

    void addNewsItemToView(News newsItem);

    void deleteNewsItemAt(int position);

    void showUndoSnackbar();

    void insertNewsItemAt(News temporaryNewsItem, int temporaryNewsItemPosition);
}
