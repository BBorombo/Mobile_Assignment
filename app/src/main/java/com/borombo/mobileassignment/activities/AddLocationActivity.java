package com.borombo.mobileassignment.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.model.Location;
import com.borombo.mobileassignment.utils.DialogManager;
import com.borombo.mobileassignment.utils.LocationsManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Activity that embed the map for select the wanted location
 */
public class AddLocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapLongClick(final LatLng latLng) {
        final Marker marker = map.addMarker(new MarkerOptions().position(latLng));

        DialogInterface.OnClickListener negativeButton = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                marker.remove();
            }
        };

        final EditText input = new EditText(AddLocationActivity.this);

        // When user do a long click on the map, a a marker is placed and a popup appears to ask
        // him the name of this location. If the user cancel the popup, the marker is removed
        DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Location location = new Location();
                location.setName(input.getText().toString());
                location.setLatitude(latLng.latitude);
                location.setLongitude(latLng.longitude);
                LocationsManager.getInstance().add(location);

                Intent intent = new Intent(AddLocationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        };

        AlertDialog.Builder builder = DialogManager.getInstance().getDialogWithInput(AddLocationActivity.this,
                            getString(R.string.addCityTitle),
                            getString(R.string.addCityText),
                            input,
                            getString(R.string.cancel),
                            getString(R.string.ok),
                            negativeButton,
                            positiveButton
        );

        builder.show();
    }
}
