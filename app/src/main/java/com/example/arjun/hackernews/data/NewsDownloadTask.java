package com.example.arjun.hackernews.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class NewsDownloadTask extends AsyncTask<String, Void, String> implements NewsSourceInterface {

    private static final String TAG = "NewsDownloadTask";

    private ArrayList<String> newsTime;
    private ArrayList<String> newsTitle;
    private ArrayList<String> newsURL;
    private ArrayList<News> newsArrayList;
    private OnDownloadComplete complete;    //To callback NewsActivity


    public NewsDownloadTask(OnDownloadComplete complete) {
        this.complete = complete;
        this.newsArrayList = new ArrayList<>();
        this.newsTime = new ArrayList<>();
        this.newsTitle = new ArrayList<>();
        this.newsURL = new ArrayList<>();
    }

    @Override
    public ArrayList<News> getNews() {
        Log.d(TAG, "getNews: Returning the array list");
        return this.newsArrayList;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: started making objects");
        int newsNumber = 0;
        try {

            JSONObject jsonObject = new JSONObject(s);
            JSONArray articles = jsonObject.getJSONArray("articles");
            newsNumber = articles.length();
            Log.d(TAG, "onPostExecute: Started parsing");
            for (int i = 0; i < newsNumber; i++) {

                JSONObject source = articles.getJSONObject(i);
                this.newsTitle.add(source.getString("title"));
                this.newsTime.add(source.getString("publishedAt"));
                this.newsURL.add(source.getString("url"));

            }
        } catch (JSONException e) {
            Log.e(TAG, "onPostExecute: JSONException", e);
        }

        for (int i = 0; i < newsNumber; i++) {

            News news = new News(

                    String.valueOf(this.newsTime.get(i)),
                    String.valueOf(this.newsTitle.get(i)),
                    String.valueOf(this.newsURL.get(i))
            );

            this.newsArrayList.add(news);

        }
        Log.d(TAG, "onPostExecute: Parsing Complete");
        this.complete.onDownloadComplete();
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.d(TAG, "doInBackground: Download started with link " + strings[0]);
        URL url;
        StringBuilder urlInfo = new StringBuilder();

        HttpURLConnection httpURLConnection;

        try {

            url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            while (data != -1){

                char current = (char) data;

                urlInfo.append(current) ;

                data = inputStreamReader.read();

            }

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }

        Log.d(TAG, "doInBackground: Download Complete");

        return urlInfo.toString();
    }
}