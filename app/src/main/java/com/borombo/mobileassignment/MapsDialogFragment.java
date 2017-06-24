package com.borombo.mobileassignment;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.borombo.mobileassignment.activities.HomeActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by Borombo on 24/06/2017.
 */

public class MapsDialogFragment extends DialogFragment {

    private EditText namePlace;

    public MapsDialogFragment(){}

    public static MapsDialogFragment newInstance(String title){
        MapsDialogFragment frag = new MapsDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_city_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        namePlace = (EditText) view.findViewById(R.id.namePlace);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", getString(R.string.addCityTitle));
        getDialog().setTitle(title);

        MapView mapView = (MapView) view.findViewById(R.id.mapView);
        MapsInitializer.initialize(getContext());

        mapView.onCreate(getDialog().onSaveInstanceState());
        mapView.onResume();// needed to get the map to display immediately
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

            }
        });
    }
}
