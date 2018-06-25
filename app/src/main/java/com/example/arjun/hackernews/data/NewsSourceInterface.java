package com.example.arjun.hackernews.data;

import java.util.ArrayList;

public interface NewsSourceInterface {
    ArrayList<News> getNews();

    News createNewsList();

    void deleteNewsItem(News news);

    void insertNewsItem(News temporaryNewsItem);
}
