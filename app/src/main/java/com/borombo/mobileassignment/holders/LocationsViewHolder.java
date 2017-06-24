package com.borombo.mobileassignment.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.adapters.LocationsAdapter;
import com.borombo.mobileassignment.model.Location;
import com.borombo.mobileassignment.utils.LocationsManager;

/**
 * Created by Borombo on 24/06/2017.
 */

public class LocationsViewHolder extends ViewHolder {

    private TextView nameLabel;
    private ImageButton deleteButton;
    private LocationsAdapter adapter;

    public LocationsViewHolder(View itemView, LocationsAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        nameLabel = (TextView) itemView.findViewById(R.id.nameLabel);
        deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);

    }

    public void updateUI(final Context context, final Location location, final int position){
        nameLabel.setText(location.getName());
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationsManager.getInstance().delete(location);
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
