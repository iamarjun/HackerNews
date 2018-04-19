package com.example.arjun.hackernews.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;

public class NewsSharedPref {

    public NewsSharedPref() {
    }


    private static SharedPreferences getPrefs(Context context) {

        return context.getSharedPreferences("com.example.arjun.hackernews.data", Context.MODE_PRIVATE);
    }

    public static HashSet<String> getNewsTime(Context context) {

        return (HashSet<String>) getPrefs(context).getStringSet("time",null);
    }

    public static void setNewsTime(Context context, HashSet<String> time) {

        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putStringSet("time", time);
        editor.apply();
    }

    public static HashSet<String> getNewsTitle(Context context) {

        return (HashSet<String>) getPrefs(context).getStringSet("title", null);
    }

    public static void setNewsTitle(Context context, HashSet<String> title) {

        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putStringSet("title", title);
        editor.apply();
    }

    public static HashSet<String> getNewsURL(Context context) {

        return (HashSet<String>) getPrefs(context).getStringSet("url", null);
    }

    public static void setNewsURL(Context context, HashSet<String> url) {

        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putStringSet("url", url);
        editor.apply();
    }
}
