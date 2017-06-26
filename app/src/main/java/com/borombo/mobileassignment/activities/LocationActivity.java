package com.borombo.mobileassignment.activities;

import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.adapters.ForecastsAdapter;
import com.borombo.mobileassignment.model.Forecast;
import com.borombo.mobileassignment.tasks.IconForecastTask;
import com.borombo.mobileassignment.utils.LocationsManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This activity show the forecast informations for a location
 */
public class LocationActivity extends LateralMenuActivity {

    private AnimationDrawable anim;
    private Forecast forecast;
    private ArrayList<Forecast> forecasts = new ArrayList<>();
    private String name;
    private Gson gson = new Gson();
    private Type type = new TypeToken<Forecast>(){}.getType();
    private boolean fiveDaysForecast;
    private boolean showIcon;

    private TextView date;
    private TextView weatherMain;
    private TextView weatherDescription;
    private TextView temperature;
    private TextView maxTemperature;
    private TextView minTemperature;
    private TextView windDegree;
    private TextView windSpeed;
    private TextView humidity;
    private TextView rain;
    private TextView cityName;
    private TextView locationName;

    private ImageView weatherIcon;

    private RecyclerView forecastsRecyclerView;
    LinearLayoutManager linearLayoutManager;
    private ForecastsAdapter forecastsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setupActivity();

        // Get the preferences
        fiveDaysForecast = LocationsManager.getInstance().show5FDaysForecast(this);
        showIcon = LocationsManager.getInstance().showIcon(this);

        // According to preferences we take the good datas from the intent
        if (fiveDaysForecast){
            forecasts = (ArrayList<Forecast>) getIntent().getSerializableExtra(getString(R.string.forecastsExtra));
            forecast = forecasts.get(0);
        }else{
            forecast = gson.fromJson(getIntent().getStringExtra(getString(R.string.forecastExtra)), type);
        }


        name = getIntent().getStringExtra(getString(R.string.locationNameExtra));

        // Setup the view
        date = (TextView) findViewById(R.id.date);
        weatherMain = (TextView) findViewById(R.id.weatherMain);
        weatherDescription = (TextView) findViewById(R.id.weatherDescription);
        temperature = (TextView) findViewById(R.id.temperature);
        maxTemperature = (TextView) findViewById(R.id.maxTemperature);
        minTemperature = (TextView) findViewById(R.id.minTemperature);
        windDegree = (TextView) findViewById(R.id.windDegree);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        humidity = (TextView) findViewById(R.id.humidity);
        rain = (TextView) findViewById(R.id.rain);
        cityName = (TextView) findViewById(R.id.cityName);
        locationName = (TextView) findViewById(R.id.locationName);

        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);

        forecastsRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // According to preferences we download the icon or not
        if (showIcon && forecast.getIcon() != null){
            IconForecastTask iconForecastTask = new IconForecastTask(weatherIcon);
            iconForecastTask.execute(forecast.getIcon());
        }

        // Set the data inside the view
        weatherMain.setText(forecast.getWeatherMain());
        weatherDescription.setText(forecast.getWeatherDescription());

        temperature.setText( getString(R.string.temp, forecast.getTemperature()) );
        maxTemperature.setText( getString(R.string.maxTemp, forecast.getMax_temp()) );
        minTemperature.setText( getString(R.string.minTemp, forecast.getMin_temp()) );
        windDegree.setText(getString(R.string.windDegree, forecast.getWind_deg()));
        windSpeed.setText(getString(R.string.windSpeed, forecast.getWind_spedd()));
        humidity.setText(getString(R.string.humidity, forecast.getHumidity()));
        rain.setText(getString(R.string.rain, forecast.getRain()));

        locationName.setText(name);
        cityName.setText(forecast.getCityName());

        // Format the date
        Date currentDate = new Date();
        String stringDate =  DateFormat.getDateInstance().format(currentDate);
        date.setText(stringDate);

        // Background animation
        CoordinatorLayout container = (CoordinatorLayout) findViewById(R.id.container);
        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);

        if (fiveDaysForecast){
            setupReyclerView();
        }else {
            forecastsRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fiveDaysForecast){
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            } else {
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            }
        }
        forecastsRecyclerView.setLayoutManager(linearLayoutManager);
        // Handle the animation
        if (anim != null && !anim.isRunning())
            anim.start();
        // Show the icon if the user comes from preferences and choose to show them
        if (LocationsManager.getInstance().showIcon(this) && weatherIcon.getDrawable() == null){
            IconForecastTask iconForecastTask = new IconForecastTask(weatherIcon);
            iconForecastTask.execute(forecast.getIcon());
            if (fiveDaysForecast)
                forecastsAdapter.swap();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Handle the animation
        if (anim != null && anim.isRunning())
            anim.stop();
    }

    // Setup the recycler view
    private void setupReyclerView(){
        forecastsAdapter = new ForecastsAdapter(forecasts);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        forecastsRecyclerView.setLayoutManager(linearLayoutManager);
        forecastsRecyclerView.setAdapter(forecastsAdapter);
    }
}
