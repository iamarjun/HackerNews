package com.example.arjun.hackernews.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.arjun.hackernews.view.NewsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class NewsDownloadTask extends AsyncTask<String, Void, String> implements NewsSourceInterface {

    private int numberOfNews = 15;

    private ArrayList<String> newsTime;
    private ArrayList<String> newsTitle;
    private ArrayList<String> newsURL;

    private boolean isAboveCodeComplete; // To Check if Objects are initialised.

    @Override
    protected String doInBackground(String... strings) {

        isAboveCodeComplete = false ;
        URL url;
        HttpURLConnection httpURLConnection;

        try {

            url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();
            StringBuilder urlInfo = new StringBuilder();

            while (data != -1){

                char current = (char) data;

                urlInfo.append(current) ;

                data = inputStreamReader.read();

            }

            JSONArray jsonArray = new JSONArray(urlInfo.toString());

            if (numberOfNews > jsonArray.length()) {

                numberOfNews = jsonArray.length();
            }


            for (int i = 0; i < numberOfNews ; i++) {

                String newsID = jsonArray.getString(i);

                url = new URL("https://hacker-news.firebaseio.com/v0/item/"+newsID+".json?print=pretty");
                httpURLConnection = (HttpURLConnection) url.openConnection();

                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);

                data = inputStreamReader.read();
                StringBuilder newsInfo = new StringBuilder();

                while (data != -1){

                    char current = (char) data;

                    newsInfo.append(current) ;

                    data = inputStreamReader.read();

                }

                JSONObject jsonObject = new JSONObject(newsInfo.toString());

                newsTime = new ArrayList<>();
                newsTitle = new ArrayList<>();
                newsURL = new ArrayList<>();


                if (!jsonObject.isNull("time") && !jsonObject.isNull("title") && !jsonObject.isNull("url")){

                    newsTime.add(jsonObject.getString("time"));
                    newsTitle.add(jsonObject.getString("title"));
                    newsURL.add(jsonObject.getString("url"));

                }

            }

        } catch (IOException | JSONException e) {

            e.printStackTrace();

        }

        isAboveCodeComplete = true;   //Ensuring objects are initialised.
        return null;
    }

    @Override
    public ArrayList<News> getNews() {

        //Waiting for the doInbackground() to complete. Then only its execution will start.
        while(!isAboveCodeComplete);

        ArrayList<News> newsArrayList = new ArrayList<>();

        for (int i = 0; i < numberOfNews; i++) {

            News news = new News(

                    String.valueOf(newsTime.get(i)),
                    String.valueOf(newsTitle.get(i)),
                    String.valueOf(newsURL.get(i))
            );

            newsArrayList.add(news);

        }


        return newsArrayList;
    }
}
