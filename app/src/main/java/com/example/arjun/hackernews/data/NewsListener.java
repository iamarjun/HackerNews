package com.example.arjun.hackernews.data;

import java.util.ArrayList;

public interface NewsListener {

    void onNewsReceived(ArrayList<News> newsList);
}
