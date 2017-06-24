package com.borombo.mobileassignment.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.mobileassignment.CitiesManager;
import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.TodayForecastTask;
import com.borombo.mobileassignment.activities.CityActivity;
import com.borombo.mobileassignment.holders.CitiesViewHolder;
import com.borombo.mobileassignment.model.City;

/**
 * Created by Borombo on 24/06/2017.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesViewHolder> {

    private Context context;

    public CitiesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_row, parent, false);
        return new CitiesViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        final City city = CitiesManager.getInstance().getById(position);
        final Context context = holder.itemView.getContext();

        holder.updateUI(context,city);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayForecastTask todayForecastTask = new TodayForecastTask(context);
                todayForecastTask.execute(String.valueOf(city.getLatitude()), String.valueOf(city.getLongitude()));
            }
        });
    }

    public void swap(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return CitiesManager.getInstance().getNumberOfCities(context);
    }

}
