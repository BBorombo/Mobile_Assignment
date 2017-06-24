package com.borombo.mobileassignment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.borombo.mobileassignment.model.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Borombo on 24/06/2017.
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

    public int getNumberOfCities(Context context){
        getList(context);
        int res = 0;
        if (cities != null)
            res = cities.size();
        return res;
    }

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

    public void add(Location location){
        cities.add(location);
        SharedPreferences.Editor editor = preferences.edit();
        String json = gson.toJson(cities);
        editor.putString(DATA_KEY, json);
        editor.commit();
    }

    public Location getById(int id){
        return cities.get(id);
    }


}
