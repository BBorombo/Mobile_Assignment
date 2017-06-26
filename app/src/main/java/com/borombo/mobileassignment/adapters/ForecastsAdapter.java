package com.borombo.mobileassignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.holders.ForecastsViewHolder;
import com.borombo.mobileassignment.model.Forecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Borombo on 25/06/2017.
 * RecyclerView.Adapter for the forecast list on the location page (5 days forecast)
 */

public class ForecastsAdapter extends RecyclerView.Adapter<ForecastsViewHolder> {

    private List<Forecast> forecasts = new ArrayList<>();

    public ForecastsAdapter(ArrayList<Forecast> list){
        // Take the list without the first forecast that is displayed as the today one
        forecasts = list.subList(1, list.size());
    }

    @Override
    public ForecastsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_row, parent, false);
        return new ForecastsViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(ForecastsViewHolder holder, int position) {
        Forecast forecast = forecasts.get(position);
        final Context context = holder.itemView.getContext();
        holder.updateUI(context, forecast);
    }

    public void swap(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }
}
