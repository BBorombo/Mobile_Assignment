package com.borombo.mobileassignment.holders;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.adapters.LocationsAdapter;
import com.borombo.mobileassignment.model.Location;
import com.borombo.mobileassignment.utils.DialogManager;
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

        final DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LocationsManager.getInstance().delete(location);
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();
            }
        };

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            AlertDialog.Builder builder = DialogManager.getInstance().getSimpleDialog(context,
                                        context.getString(R.string.deleteLocationTitle),
                                        context.getString(R.string.deleteLocationText),
                                        context.getString(R.string.cancel),
                                        context.getString(R.string.ok),
                                        null, positiveButton);
            builder.show();

            }
        });
    }

}
