package com.borombo.mobileassignment.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.widget.LinearLayout;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.model.City;
import com.borombo.mobileassignment.model.Forecast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CityActivity extends LateralMenuActivity {

    private AnimationDrawable anim;
    private Forecast forecast;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        setupActivity();

        Type type = new TypeToken<Forecast>(){}.getType();
        forecast = gson.fromJson(getIntent().getStringExtra(getString(R.string.forecastExtra)), type);

        CoordinatorLayout container = (CoordinatorLayout) findViewById(R.id.container);
        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
}
