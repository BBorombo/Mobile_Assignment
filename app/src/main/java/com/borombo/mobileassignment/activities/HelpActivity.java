package com.borombo.mobileassignement.activities;

import android.os.Bundle;
import android.webkit.WebView;

import com.borombo.mobileassignement.R;

public class HelpActivity extends LateralMenuActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupActivity();
    }
}
