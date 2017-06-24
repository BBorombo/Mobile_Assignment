package com.borombo.mobileassignment.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.model.Location;

/**
 * Created by Borombo on 24/06/2017.
 */

public class CitiesViewHolder extends ViewHolder {

    private TextView nameLabel;
    private TextView latLabel;
    private TextView longLabel;

    public CitiesViewHolder(View itemView) {
        super(itemView);
        nameLabel = (TextView) itemView.findViewById(R.id.nameLabel);
        latLabel = (TextView) itemView.findViewById(R.id.latLabel);
        longLabel = (TextView) itemView.findViewById(R.id.longLabel);
    }

    public void updateUI(Context context, Location location){
        nameLabel.setText(location.getName());
        longLabel.setText(context.getText(R.string.lon) + String.valueOf(location.getLongitude()));
        latLabel.setText(context.getText(R.string.lat) + String.valueOf(location.getLatitude()));
    }

}
