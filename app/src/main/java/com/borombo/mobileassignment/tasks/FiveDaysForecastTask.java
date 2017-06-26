package com.borombo.mobileassignment.tasks;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.activities.LocationActivity;
import com.borombo.mobileassignment.model.Forecast;
import com.borombo.mobileassignment.utils.LocationsManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Borombo on 25/06/2017.
 */

public class FiveDaysForecastTask extends AsyncTask<String, Void, JSONObject> implements TaskValues{

    private JSONObject jsonData;
    private Context context;
    private View content;
    private String locationName;
    private ProgressBar progressBar;
    private String preferenceHour;


    private ArrayList<Forecast> forecasts = new ArrayList<>();

    public FiveDaysForecastTask(Context context, View content, String locationName, ProgressBar progressBar) {
        this.context = context;
        this.content = content;
        this.locationName = locationName;
        this.progressBar = progressBar;
        preferenceHour = LocationsManager.getInstance().getForecastHour(context);
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        StringBuilder stringData = new StringBuilder();
        InputStream inputStream;

        StringBuilder builder = new StringBuilder();
        builder.append(START_URL_FORECAST);
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
                JSONObject city = jsonObject.getJSONObject(CITY);
                String name = city.getString(NAME);

                JSONArray list = jsonObject.getJSONArray(LIST);

                JSONObject jsonForecast;

                Forecast forecast;
                int position = 0;
                String hour = null;
                do {
                    jsonForecast = (JSONObject) list.get(position);
                    forecast = new Forecast();
                    String forecastDate = jsonForecast.getString(DT_TXT).split(" ")[0];
                    String forecastHour = jsonForecast.getString(DT_TXT).split(" ")[1];
                    if (position == 0){
                        if (preferenceHour.equals(context.getString(R.string.none)) ){
                            hour = forecastHour;
                        }else{
                            hour = preferenceHour;
                        }
                    }else{
                        if (!forecastHour.equals(hour)){
                            position++;
                            continue;
                        }
                    }

                    JSONObject main = jsonForecast.getJSONObject(MAIN);
                    JSONObject wind = jsonForecast.getJSONObject(WIND);
                    JSONArray weatherArray = jsonForecast.getJSONArray(WEATHER);
                    JSONObject weather = (JSONObject) weatherArray.get(0);

                    forecast.setDate(forecastDate);
                    forecast.setCityName(name);
                    forecast.setWeatherMain(weather.getString(MAIN));
                    forecast.setWeatherDescription(weather.getString(DESCRIPTION));
                    forecast.setIcon(weather.getString(ICON));

                    forecast.setTemperature(main.getDouble(TEMP));
                    forecast.setMax_temp(main.getDouble(TEMP_MAX));
                    forecast.setMin_temp(main.getDouble(TEMP_MIN));

                    forecast.setWind_deg(wind.getDouble(WIND_DEG));
                    forecast.setWind_spedd(wind.getDouble(WIND_SPEED));

                    forecast.setHumidity(main.getDouble(HUMIDITY));

                    if (jsonForecast.has(RAIN)){
                        JSONObject rain = jsonForecast.getJSONObject(RAIN);
                        if (rain.has(THREE_H)){
                            forecast.setRain(rain.getDouble(THREE_H));
                        }
                    }

                    forecasts.add(forecast);
                    position++;
                }while (position < list.length());


            }catch (JSONException e){
                e.printStackTrace();
            }
            Log.d("Size", String.valueOf(forecasts.size()));
            if (forecasts.size() > 1){
                Intent intent = new Intent(context, LocationActivity.class);
                intent.putExtra(context.getString(R.string.forecastsExtra), forecasts);
                intent.putExtra(context.getString(R.string.locationNameExtra), locationName);
                context.startActivity(intent);
            }
        }else{
            final Snackbar snackbar = Snackbar
                    .make(content, context.getText(R.string.noConnexion), Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {}
                    });
            snackbar.show();
        }
        progressBar.setVisibility(View.GONE);
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
