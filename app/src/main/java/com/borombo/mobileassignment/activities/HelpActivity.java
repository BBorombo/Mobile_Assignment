package com.borombo.mobileassignment.activities;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.borombo.mobileassignment.R;

public class HelpActivity extends LateralMenuActivity {

    private WebView webView;
    private Bundle webViewBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupActivity();

        webView = (WebView) findViewById(R.id.webView);

        if (webViewBundle == null) {
            webView.loadUrl("file:///android_asset/help.html");
        } else {
            webView.restoreState(webViewBundle);
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
    }
}
