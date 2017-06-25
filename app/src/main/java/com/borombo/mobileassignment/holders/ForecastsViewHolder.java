package com.borombo.mobileassignment.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.borombo.mobileassignment.R;

/**
 * Created by Borombo on 25/06/2017.
 */

public class ForecastsViewHolder extends RecyclerView.ViewHolder {

    private TextView name;


    public ForecastsViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
    }

    public void updateUI(String name){
        this.name.setText(name);
    }

}
