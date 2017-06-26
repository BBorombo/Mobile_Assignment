package com.borombo.mobileassignment.activities;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.borombo.mobileassignment.R;

/**
 * Activity for the Help page that show a WebView
 */
public class HelpActivity extends LateralMenuActivity {

    private WebView webView;
    private Bundle webViewBundle = null;

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
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
    }
}
