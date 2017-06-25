package com.borombo.mobileassignment.model;

/**
 * Created by Borombo on 24/06/2017.
 */

public class Forecast {

    private String weatherMain;
    private String weatherDescription;
    private String cityName;
    private String icon;

    private Double temperature = 0.0;
    private Double max_temp = 0.0;
    private Double min_temp = 0.0;
    private Double humidity = 0.0;
    private Double wind_deg = 0.0;
    private Double wind_spedd = 0.0;
    private Double rain = 0.0;

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(Double max_temp) {
        this.max_temp = max_temp;
    }

    public Double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(Double min_temp) {
        this.min_temp = min_temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(Double wind_deg) {
        this.wind_deg = wind_deg;
    }

    public Double getWind_spedd() {
        return wind_spedd;
    }

    public void setWind_spedd(Double wind_spedd) {
        this.wind_spedd = wind_spedd;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }
}
