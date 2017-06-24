package com.borombo.mobileassignment.model;

/**
 * Created by Borombo on 24/06/2017.
 */

public class City {

    private String name;
    private Long latitude;
    private Long longitude;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
