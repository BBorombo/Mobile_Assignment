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

/**
 * Created by Borombo on 25/06/2017.
 */

public class ForecastsAdapter extends RecyclerView.Adapter<ForecastsViewHolder> {

    private ArrayList<Forecast> forecasts = new ArrayList<>();

    public ForecastsAdapter(ArrayList<Forecast> list){
        forecasts = list;
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

    @Override
    public int getItemCount() {
        return forecasts.size();
    }
}
