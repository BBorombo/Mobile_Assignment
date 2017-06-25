package com.borombo.mobileassignment.tasks;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.activities.LocationActivity;
import com.borombo.mobileassignment.model.Forecast;
import com.google.gson.Gson;

import org.json.JSONArray;
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

public class TodayForecastTask extends AsyncTask<String, Void, JSONObject> implements TaskValues {

    private JSONObject jsonData;
    private Context context;
    private View content;
    private String locationName;

    private Forecast forecast;

    public TodayForecastTask(Context context, View content, String locationName){
        this.context = context;
        this.content = content;
        this.locationName = locationName;
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
        }
        return jsonData;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (jsonObject != null){
            try{
                forecast = new Forecast();
                JSONObject main = jsonObject.getJSONObject(MAIN);
                JSONObject wind = jsonObject.getJSONObject(WIND);
                JSONArray weatherArray = jsonObject.getJSONArray(WEATHER);
                JSONObject weather = (JSONObject) weatherArray.get(0);

                forecast.setCityName(jsonObject.getString(NAME));
                forecast.setWeatherMain(weather.getString(MAIN));
                forecast.setWeatherDescription(weather.getString(DESCRIPTION));
                forecast.setIcon(weather.getString(ICON));

                forecast.setTemperature(main.getDouble(TEMP));
                forecast.setMax_temp(main.getDouble(TEMP_MAX));
                forecast.setMin_temp(main.getDouble(TEMP_MIN));

                forecast.setWind_deg(wind.getDouble(WIND_DEG));
                forecast.setWind_spedd(wind.getDouble(WIND_SPEED));

                forecast.setHumidity(main.getDouble(HUMIDITY));

                if (jsonObject.has(RAIN)){
                    JSONObject rain = jsonObject.getJSONObject(RAIN);
                    if (rain.has(THREE_H)){
                        forecast.setRain(rain.getDouble(THREE_H));
                    }
                }

            }catch (JSONException e){
                e.printStackTrace();
            }

            if (forecast != null){
                Intent intent = new Intent(context, LocationActivity.class);
                intent.putExtra(context.getString(R.string.forecastExtra), gson.toJson(forecast));
                intent.putExtra(context.getString(R.string.locationNameExtra), locationName);
                context.startActivity(intent);
            }
        }else{

            final Snackbar snackbar = Snackbar
                    .make(content, context.getText(R.string.noConnexion), Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
            snackbar.show();
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

                urlc.setConnectTimeout(500);
                urlc.connect();
                res =  (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
            } catch (IOException e) {
                Log.e("Check Connection", "Error checking internet connection", e);
            }
        }
        return res;
    }
}
