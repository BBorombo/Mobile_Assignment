package com.borombo.mobileassignment.model;

/**
 * Created by Borombo on 24/06/2017.
 */

public class Forecast {

    private Double temperature;
    private Double max_temp;
    private Double min_temp;
    private Double humidity;
    private Double wind_deg;
    private Double wind_spedd;

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
}
