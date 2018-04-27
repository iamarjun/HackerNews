package com.example.arjun.hackernews.data;

public class News {

    private String time;
    private String title;
    private String newsURL;

    public News(String time, String title, String newsURL) {

        this.time = time;
        this.title = title;
        this.newsURL = newsURL;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getNewsURL() {

        return newsURL;
    }

    public void setNewsURL(String newsURL) {

        this.newsURL = newsURL;
    }

    @Override
    public String toString() {
        return "News{" +
                "time='" + time + '\n' +
                ", title='" + title + '\n' +
                ", newsURL='" + newsURL + '\n' +
                '}';
    }
}