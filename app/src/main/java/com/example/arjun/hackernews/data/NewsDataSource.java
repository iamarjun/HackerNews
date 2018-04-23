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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arjun.hackernews.view.NewsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class NewsDataSource implements NewsSourceInterface {

    private int numberOfNews = 10;

    private ArrayList<String> newsTime;
    private ArrayList<String> newsTitle;
    private ArrayList<String> newsURL;

    private ArrayList<News> newsList;
    private ArrayList<News> newsArrayList;

    private void getNewsDataSource(final NewsListener newsListener){

        final String URL = "https://newsapi.org/v2/top-headlines?sources=hacker-news&apiKey=0479148276a84cf5bdb90c9e04801f60";

        newsTime = new ArrayList<>();
        newsTitle = new ArrayList<>();
        newsURL = new ArrayList<>();

        newsList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray article = response.getJSONArray("articles");

                            for (int i = 0; i < numberOfNews ; i++) {

                                JSONObject topNews = article.getJSONObject(i);

                                newsTime.add(topNews.getString("publishedAt")) ;
                                newsTitle.add(topNews.getString("title"));
                                newsURL.add(topNews.getString("url"));

                            }

                            for (int i = 0; i < numberOfNews; i++) {

                                News news = new News(

                                        String.valueOf(newsTime.get(i)),
                                        String.valueOf(newsTitle.get(i)),
                                        String.valueOf(newsURL.get(i))

                                );

                                newsList.add(news);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        newsListener.onNewsReceived(newsList);

                        //newsList is populated over here.
                        //Log.i("news:", newsList.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                });

        Context contextOfApplication = NewsActivity.getContextOfApplication();
        MySingleton.getInstance(contextOfApplication).addToRequestQueue(jsonObjectRequest);


    }

    @Override
    public ArrayList<News> getNews() {

        newsArrayList = new ArrayList<>();

        getNewsDataSource(new NewsListener() {
            @Override
            public void onNewsReceived(ArrayList<News> newsList) {

                Log.i("arjun", newsList.toString());
                // how do i return the newsList below

                newsArrayList = newsList;

            }
        });

        Log.i("arjun", newsList.toString());


        return newsArrayList;

    }
}

