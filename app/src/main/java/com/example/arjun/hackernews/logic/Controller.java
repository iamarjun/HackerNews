package com.example.arjun.hackernews.logic;

import com.example.arjun.hackernews.data.News;
import com.example.arjun.hackernews.data.NewsSourceInterface;
import com.example.arjun.hackernews.view.ViewInterface;

public class Controller {

    private ViewInterface viewInterface;
    private NewsSourceInterface newsSourceInterface;

    public Controller(ViewInterface viewInterface, NewsSourceInterface newsSourceInterface) {

        this.viewInterface = viewInterface;
        this.newsSourceInterface = newsSourceInterface;

        getListFromNewsSource();

    }


    public void getListFromNewsSource()  {

        viewInterface.setAdapterAndView(newsSourceInterface.getNews());
    }


    public void onNewsItemClicked(News news) {

        viewInterface.startNewsWebViewActivity(news.getNewsURL());
    }
}
