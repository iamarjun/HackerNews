package com.example.arjun.hackernews.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.arjun.hackernews.R;


public class NewsWebView extends AppCompatActivity {

    //private static final String EXTRA_URL = "EXTRA_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newswebview);

        Intent intent = getIntent();
        String urlExtra = intent.getStringExtra("URL");

        WebView webView = findViewById(R.id.newsWebView);

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(urlExtra);

    }


}
