package com.example.arjun.hackernews.data;

import android.content.Context;

import android.content.SharedPreferences;

import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.arjun.hackernews.view.NewsActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;

public class NewsDataSource implements NewsSourceInterface {


    private int numberOfNews = 15;

    private ArrayList<String> newsTime;
    private ArrayList<String> newsTitle;
    private ArrayList<String> newsURL;

    private HashSet<String> timeSet;
    private HashSet<String> titleSet;
    private HashSet<String> urlSet;

    private NewsSharedPref newsSharedPref;
    private Context contextOfApplication;



    @Override
    public ArrayList<News> getNews() {

        final String URL = "https://newsapi.org/v2/top-headlines?sources=hacker-news&apiKey=0479148276a84cf5bdb90c9e04801f60";

        newsTime = new ArrayList<>();
        newsTitle = new ArrayList<>();
        newsURL = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < numberOfNews; i++) {

                            try {

                                newsTime.add(response.getJSONObject(i).getString("publishedAt")) ;
                                newsTitle.add(response.getJSONObject(i).getString("title"));
                                newsURL.add(response.getJSONObject(i).getString("url"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                        timeSet = new HashSet<>(newsTime);
                        titleSet = new HashSet<>(newsTitle);
                        urlSet = new HashSet<>(newsURL);

                        contextOfApplication = NewsActivity.getContextOfApplication();

                        NewsSharedPref.setNewsTime(contextOfApplication, timeSet);
                        NewsSharedPref.setNewsTitle(contextOfApplication, titleSet);
                        NewsSharedPref.setNewsURL(contextOfApplication, urlSet);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }
        });

        contextOfApplication = NewsActivity.getContextOfApplication();


        MySingleton.getInstance(contextOfApplication).addToRequestQueue(jsonArrayRequest);

        timeSet = NewsSharedPref.getNewsTime(contextOfApplication);
        titleSet = NewsSharedPref.getNewsTitle(contextOfApplication);
        urlSet = NewsSharedPref.getNewsURL(contextOfApplication);

        newsTime = new ArrayList<>(timeSet);
        newsTitle = new ArrayList<>(titleSet);
        newsURL = new ArrayList<>(urlSet);


        ArrayList<News> newsList = new ArrayList<>();


        for (int i = 0; i < numberOfNews; i++) {

            News news = new News(

                    String.valueOf(newsTime.get(i)),
                    String.valueOf(newsTitle.get(i)),
                    String.valueOf(newsURL.get(i))

            );

            newsList.add(news);
        }


        return newsList;
    }
}

