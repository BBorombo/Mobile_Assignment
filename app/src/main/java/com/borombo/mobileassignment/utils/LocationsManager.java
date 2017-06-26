package com.borombo.mobileassignment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.model.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Borombo on 24/06/2017.
 * Singleton that handle the storage of the locations in the preferences
 * and the preferences parameters
 */

public class LocationsManager {

    private static final LocationsManager instance = new LocationsManager();

    private static final String DATA_KEY = "Cities";

    private SharedPreferences preferences;
    private ArrayList<Location> cities = new ArrayList<>();
    private Gson gson = new Gson();

    public static LocationsManager getInstance() {
        return instance;
    }

    private LocationsManager() {
    }

    /**
     * Tell if the user want to download and show icons
     * @param context The context
     * @return A boolean with the value of this preference
     */
    public boolean showIcon(Context context){
        preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getBoolean(context.getString(R.string.showIconID), true);
    }

    /**
     * Tell if the user want to show the 5 days forecast
     * @param context The context
     * @return A boolean with the value of this preference
     */
    public boolean show5FDaysForecast(Context context){
        preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getBoolean(context.getString(R.string.showForecastID), true);
    }

    /**
     * Tell which hour the user want to use for the 5 days forecast
     * @param context The context
     * @return The value of the hour
     */
    public String getForecastHour(Context context){
        preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.hourForecastID), "none");
    }

    /**
     * Return the number of cities saved in the device
     * @param context The context
     * @return The number of cities
     */
    public int getNumberOfCities(Context context){
        getList(context);
        int res = 0;
        if (cities != null)
            res = cities.size();
        return res;
    }

    /**
     * Function which setup the list of locations from the preferences
     * @param context The context
     */
    private void getList(Context context){
        preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String json = preferences.getString(DATA_KEY, "");
        Type type = new TypeToken<List<Location>>(){}.getType();
        ArrayList<Location> data = gson.fromJson(json, type);
        if(data != null){
             cities = data;
         }

    }

    /**
     * Add a location to the list
     * @param location The location to add
     */
    public void add(Location location){
        cities.add(location);
        save();
    }

    /**
     * Delete a location from the list
     * @param location The location to delete
     */
    public void delete(Location location){
        cities.remove(location);
        save();
    }

    /**
     * Delete a location from the list
     * @param position The position of the location to delete
     */
    public void delete(int position){
        cities.remove(position);
        save();
    }

    /**
     * Delete all the locations
     */
    public void deleteAll(){
        cities.clear();
        save();
    }

    /**
     * Get a location by his id
     * @param id The id of the location
     * @return The location that correspond to the id
     */
    public Location getById(int id){
        return cities.get(id);
    }

    /**
     * Save into the preferences the modifications that have been made in the list
     */
    private void save(){
        SharedPreferences.Editor editor = preferences.edit();
        String json = gson.toJson(cities);
        editor.putString(DATA_KEY, json);
        editor.commit();
    }

}
