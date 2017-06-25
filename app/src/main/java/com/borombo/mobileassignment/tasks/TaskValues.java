package com.borombo.mobileassignment.tasks;

import android.content.Context;
import android.view.View;

import com.borombo.mobileassignment.model.Forecast;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Borombo on 25/06/2017.
 */

public interface TaskValues {
    Gson gson = new Gson();

    String WIND = "wind";
    String WIND_DEG = "deg";
    String WIND_SPEED = "speed";
    String NAME = "name";
    String MAIN = "main";
    String DESCRIPTION = "description";
    String ICON = "icon";
    String WEATHER = "weather";
    String TEMP = "temp";
    String TEMP_MIN = "temp_min";
    String TEMP_MAX = "temp_max";
    String HUMIDITY = "humidity";
    String RAIN = "rain";
    String THREE_H = "3h";
    String CITY = "city";
    String LIST = "list";
    String DT_TXT = "dt_txt";

    String START_URL = "http://api.openweathermap.org/data/2.5/weather";
    String START_URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast";
    String LAT_URL = "?lat=";
    String LNG_URL = "&lon=";
    String END_URL = "&appid=c6e381d8c7ff98f0fee43775817cf6ad&units=metric";


}
