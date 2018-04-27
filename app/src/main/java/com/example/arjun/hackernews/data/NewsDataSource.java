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

//I am still working on it.

public class NewsDataSource implements NewsSourceInterface {
    private static final String TAG = "NewsDataSource";

    private int numberOfNews = 10;

    private ArrayList<String> newsTime;
    private ArrayList<String> newsTitle;
    private ArrayList<String> newsURL;

    private ArrayList<News> newsList;

    private OnDownloadComplete callback;

    public NewsDataSource(OnDownloadComplete onDownloadComplete) {
        this.callback = onDownloadComplete;
        newsTime = new ArrayList<>();
        newsTitle = new ArrayList<>();
        newsURL = new ArrayList<>();

        newsList = new ArrayList<>();

        Log.d(TAG, "NewsDataSource: Everything is set up");
    }

    public void getNewsDataSource(String URL) {

        Log.d(TAG, "getNewsDataSource: Starting Downloading");
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
                            Log.e(TAG, "onResponse: Download Failed");
                            e.printStackTrace();
                        }

                        //newsList is populated over here.

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: Download failed");
                        error.printStackTrace();

                    }
                });

        Context contextOfApplication = NewsActivity.getContextOfApplication();
        MySingleton.getInstance(contextOfApplication).addToRequestQueue(jsonObjectRequest);

        for (News list : newsList) {
            Log.d(TAG, "*******");
            Log.d(TAG, list.toString());
        }
        this.callback.onDownloadComplete();
    }

    @Override
    public ArrayList<News> getNews() {
        Log.d(TAG, "getNews: Returning the data");
        return this.newsList;
    }
}
