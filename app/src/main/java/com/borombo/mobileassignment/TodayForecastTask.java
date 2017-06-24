package com.borombo.mobileassignment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.borombo.mobileassignment.activities.CityActivity;
import com.borombo.mobileassignment.model.Forecast;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Borombo on 24/06/2017.
 */

public class TodayForecastTask extends AsyncTask<String, Void, JSONObject> {

    private JSONObject jsonData;
    private Context context;
    private Gson gson = new Gson();

    private Forecast forecast;

    private static final String WIND = "wind";
    private static final String WIND_DEG = "deg";
    private static final String WIND_SPEED = "speed";
    private static final String MAIN = "main";
    private static final String TEMP = "temp";
    private static final String TEMP_MIN = "temp_min";
    private static final String TEMP_MAX = "temp_max";
    private static final String HUMIDITY = "humidity";

    private static final String START_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String LAT_URL = "?lat=";
    private static final String LNG_URL = "&lon=";
    private static final String END_URL = "&appid=c6e381d8c7ff98f0fee43775817cf6ad&units=metric";

    public TodayForecastTask(Context context){
        this.context = context;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        StringBuilder stringData = new StringBuilder();
        InputStream inputStream;

        StringBuilder builder = new StringBuilder();
        builder.append(START_URL);
        builder.append(LAT_URL);
        builder.append(params[0]);
        builder.append(LNG_URL);
        builder.append(params[1]);
        builder.append(END_URL);

        if (hasActiveInternetConnection()){
            try {

                URL url = new URL(builder.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                inputStream = connection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringData.append(line);
                }
                jsonData = new JSONObject(stringData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Si il n'y a pas de connexion internet
        }else {

        }
        return jsonData;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.d("Data",jsonObject.toString());

        try{
            forecast = new Forecast();
            JSONObject main = jsonObject.getJSONObject(MAIN);
            JSONObject wind = jsonObject.getJSONObject(WIND);

            forecast.setTemperature(main.getDouble(TEMP));
            forecast.setMax_temp(main.getDouble(TEMP_MAX));
            forecast.setMin_temp(main.getDouble(TEMP_MIN));

            forecast.setWind_deg(wind.getDouble(WIND_DEG));
            forecast.setWind_spedd(wind.getDouble(WIND_SPEED));

            forecast.setHumidity(main.getDouble(HUMIDITY));

        }catch (JSONException e){
            e.printStackTrace();
        }

        if (forecast != null){
            Intent intent = new Intent(context, CityActivity.class);
            intent.putExtra(context.getString(R.string.forecastExtra), gson.toJson(forecast));
            context.startActivity(intent);
        }

    }

    public boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null);
    }

    public boolean hasActiveInternetConnection() {
        boolean res = false;
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection)
                        (new URL("http://clients3.google.com/generate_204")
                                .openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                res =  (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
            } catch (IOException e) {
                Log.e("Check Connection", "Error checking internet connection", e);
            }
        }
        return res;
    }
}
