package com.borombo.mobileassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.mobileassignment.utils.LocationsManager;
import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.tasks.TodayForecastTask;
import com.borombo.mobileassignment.holders.LocationsViewHolder;
import com.borombo.mobileassignment.model.Location;

/**
 * Created by Borombo on 24/06/2017.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsViewHolder> {

    private Context context;

    public LocationsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_row, parent, false);
        return new LocationsViewHolder(contentView, this);
    }

    @Override
    public void onBindViewHolder(final LocationsViewHolder holder, int position) {
        final Location location = LocationsManager.getInstance().getById(position);
        final Context context = holder.itemView.getContext();
        holder.updateUI(context, location, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayForecastTask todayForecastTask = new TodayForecastTask(context, holder.itemView, location.getName());
                todayForecastTask.execute(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
            }
        });
    }

    public void swap(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return LocationsManager.getInstance().getNumberOfCities(context);
    }

}
