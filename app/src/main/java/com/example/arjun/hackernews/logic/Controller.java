package com.example.arjun.hackernews.logic;

import android.util.Log;
import android.view.View;

import com.example.arjun.hackernews.data.News;
import com.example.arjun.hackernews.data.NewsSourceInterface;
import com.example.arjun.hackernews.view.ViewInterface;

public class Controller {
    private static final String TAG = "Controller";

    private News temporaryNewsItem;
    private int temporaryNewsItemPosition;

    private ViewInterface view;
    private NewsSourceInterface newsSource;

    public Controller(ViewInterface view, NewsSourceInterface newsSource) {

        this.view = view;
        this.newsSource = newsSource;
        getListFromNewsSource();

    }

    public void getListFromNewsSource()  {
        Log.d(TAG, "getListFromNewsSource: Calling for the data");
        view.setAdapterAndView(newsSource.getNews());
    }

    public void onNewsItemClicked(News news, View viewRoot) {
        //view.startNewsWebViewActivity(news.getNewsURL());

        view.startDetailActivity(
                news.getDateAndTime(),
                news.getHeadline(),
                news.getColorResource(),
                viewRoot
        );
    }


    public void createNewsItem() {
        News newsItem = newsSource.createNewsList();
        view.addNewsItemToView(newsItem);
    }

    public void onNewsItemSwiped(int position, News news) {
        newsSource.deleteNewsItem(news);
        view.deleteNewsItemAt(position);

        temporaryNewsItem = news;
        temporaryNewsItemPosition = position;

        view.showUndoSnackbar();

    }

    public void onUndoConfirmed() {
        if (temporaryNewsItem != null){
            newsSource.insertNewsItem(temporaryNewsItem);
            view.insertNewsItemAt(temporaryNewsItem, temporaryNewsItemPosition);

            temporaryNewsItem = null;
            temporaryNewsItemPosition = 0;

        } else{

        }
    }

    public void onSnackbarTimeout() {
        temporaryNewsItem = null;
        temporaryNewsItemPosition = 0;
    }
}