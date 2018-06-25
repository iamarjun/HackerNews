package com.example.arjun.hackernews.data;

public class News {

    private String dateAndTime;

    private String headline;

    private String newsURL;

    private int colorResource;

    public News(String dateAndTime, String title, String newsURL, int colorResource) {
        this.dateAndTime = dateAndTime;
        this.headline = title;
        this.newsURL = newsURL;
        this.colorResource = colorResource;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    @Override
    public String toString() {
        return "News{" +
                "dateAndTime='" + dateAndTime + '\n' +
                ", headline='" + headline + '\n' +
                ", newsURL='" + newsURL + '\n' +
                ", colorResource='" + colorResource + '\n' +
                '}';
    }
}