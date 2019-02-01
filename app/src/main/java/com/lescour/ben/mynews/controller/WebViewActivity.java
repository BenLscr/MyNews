package com.lescour.ben.mynews.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lescour.ben.mynews.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.lescour.ben.mynews.controller.MainActivity.BUNDLE_EXTRA_URL;


/**
 * Created by benja on 29/01/2019.
 */
public class WebViewActivity extends AppCompatActivity {

    private String articleUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_webview);

        Intent webViewActivity = getIntent();
        if (webViewActivity.hasExtra(BUNDLE_EXTRA_URL)) {
            articleUrl = webViewActivity.getStringExtra(BUNDLE_EXTRA_URL);
        }

        configureWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void configureWebView() {
        WebView webView = findViewById(R.id.article_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(articleUrl);
    }
}
