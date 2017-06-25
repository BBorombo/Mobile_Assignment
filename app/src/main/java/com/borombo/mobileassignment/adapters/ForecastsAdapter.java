package com.borombo.mobileassignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.holders.ForecastsViewHolder;

import java.util.ArrayList;

/**
 * Created by Borombo on 25/06/2017.
 */

public class ForecastsAdapter extends RecyclerView.Adapter<ForecastsViewHolder> {

    private ArrayList<String> datas = new ArrayList<>();

    public ForecastsAdapter(ArrayList<String> list){
        datas = list;
    }

    @Override
    public ForecastsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_row, parent, false);
        return new ForecastsViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(ForecastsViewHolder holder, int position) {
        String name = datas.get(position);
        holder.updateUI(name);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
