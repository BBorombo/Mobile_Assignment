package com.borombo.mobileassignment.holders;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.model.Forecast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Borombo on 25/06/2017.
 */

public class ForecastsViewHolder extends RecyclerView.ViewHolder {

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

    private Context context;

    public ForecastsViewHolder(View itemView) {
        super(itemView);
        date = (TextView) itemView.findViewById(R.id.date);
        weatherMain = (TextView) itemView.findViewById(R.id.weatherMain);
        weatherDescription = (TextView) itemView.findViewById(R.id.weatherDescription);
        temperature = (TextView) itemView.findViewById(R.id.temperature);
        maxTemperature = (TextView) itemView.findViewById(R.id.maxTemperature);
        minTemperature = (TextView) itemView.findViewById(R.id.minTemperature);
        windDegree = (TextView) itemView.findViewById(R.id.windDegree);
        windSpeed = (TextView) itemView.findViewById(R.id.windSpeed);
        humity = (TextView) itemView.findViewById(R.id.humidity);
        rain = (TextView) itemView.findViewById(R.id.rain);
    }

    public void updateUI(Context context, Forecast forecast){
        this.context =  context;
        Date _date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            _date = format.parse(forecast.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("MMM dd, yyyy", getCurrentLocale());
        date.setText(format.format(_date));
        weatherMain.setText(forecast.getWeatherMain());
        weatherDescription.setText(forecast.getWeatherDescription());

        temperature.setText( context.getString(R.string.temp, forecast.getTemperature()) );
        maxTemperature.setText( context.getString(R.string.maxTemp, forecast.getMax_temp()) );
        minTemperature.setText( context.getString(R.string.minTemp, forecast.getMin_temp()) );
        windDegree.setText(context.getString(R.string.windDegree, forecast.getWind_deg()));
        windSpeed.setText(context.getString(R.string.windSpeed, forecast.getWind_spedd()));
        humity.setText(context.getString(R.string.humidity, forecast.getHumidity()));
        rain.setText(context.getString(R.string.rain, forecast.getRain()));

    }

    @TargetApi(Build.VERSION_CODES.N)
    private Locale getCurrentLocale(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

}
