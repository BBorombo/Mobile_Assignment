package com.borombo.mobileassignment.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.model.Forecast;
import com.borombo.mobileassignment.tasks.IconForecastTask;
import com.borombo.mobileassignment.utils.LocationsManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

public class LocationActivity extends LateralMenuActivity {

    private AnimationDrawable anim;
    private Forecast forecast;
    private String name;
    private Gson gson = new Gson();
    private Type type = new TypeToken<Forecast>(){}.getType();

    private TextView date;
    private TextView weatherMain;
    private TextView weatherDescription;
    private TextView temperature;
    private TextView maxTemperature;
    private TextView minTemperature;
    private TextView windDegree;
    private TextView windSpeed;
    private TextView humity;
    private TextView rain;
    private TextView cityName;
    private TextView locationName;

    private ImageView weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setupActivity();

        forecast = gson.fromJson(getIntent().getStringExtra(getString(R.string.forecastExtra)), type);
        name = getIntent().getStringExtra(getString(R.string.locationNameExtra));

        date = (TextView) findViewById(R.id.date);
        weatherMain = (TextView) findViewById(R.id.weatherMain);
        weatherDescription = (TextView) findViewById(R.id.weatherDescription);
        temperature = (TextView) findViewById(R.id.temperature);
        maxTemperature = (TextView) findViewById(R.id.maxTemperature);
        minTemperature = (TextView) findViewById(R.id.minTemperature);
        windDegree = (TextView) findViewById(R.id.windDegree);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        humity = (TextView) findViewById(R.id.humidity);
        rain = (TextView) findViewById(R.id.rain);
        cityName = (TextView) findViewById(R.id.cityName);
        locationName = (TextView) findViewById(R.id.locationName);

        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);

        if (LocationsManager.getInstance().showIcon(this) && forecast.getIcon() != null){
            IconForecastTask iconForecastTask = new IconForecastTask(weatherIcon);
            iconForecastTask.execute(forecast.getIcon());
        }

        weatherMain.setText(forecast.getWeatherMain());
        weatherDescription.setText(forecast.getWeatherDescription());

        temperature.setText( getString(R.string.temp, forecast.getTemperature()) );
        maxTemperature.setText( getString(R.string.maxTemp, forecast.getMax_temp()) );
        minTemperature.setText( getString(R.string.minTemp, forecast.getMin_temp()) );
        windDegree.setText(getString(R.string.windDegree, forecast.getWind_deg()));
        windSpeed.setText(getString(R.string.windSpeed, forecast.getWind_spedd()));
        humity.setText(getString(R.string.humidity, forecast.getHumidity()));
        rain.setText(getString(R.string.rain, forecast.getRain()));

        locationName.setText(name);
        cityName.setText(forecast.getCityName());

        Date currentDate = new Date();
        String stringDate =  DateFormat.getDateInstance().format(currentDate);
        date.setText(stringDate);

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
