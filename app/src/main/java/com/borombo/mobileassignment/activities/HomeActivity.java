package com.borombo.mobileassignment.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.borombo.mobileassignment.CitiesManager;
import com.borombo.mobileassignment.MapsDialogFragment;
import com.borombo.mobileassignment.adapters.CitiesAdapter;
import com.borombo.mobileassignment.R;
import com.borombo.mobileassignment.model.City;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

public class HomeActivity extends LateralMenuActivity {

    private RecyclerView citiesRecyclerView;
    private CitiesAdapter citiesAdapter;
    private FloatingActionButton addCityButton;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupActivity();
        setupReyclerView();

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                citiesAdapter.swap();
                refreshLayout.setRefreshing(false);
            }
        });

        addCityButton = (FloatingActionButton) findViewById(R.id.addCityFAB);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

                final EditText input = new EditText(HomeActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);

                builder.setTitle(getString(R.string.addCityTitle))
                        .setView(input)
                        .setMessage(getString(R.string.addCityText))
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                City city = new City();
                                city.setName(input.getText().toString());
                                city.setLatitude(0.0);
                                city.setLongitude(0.0);
                                CitiesManager.getInstance().add(city);

                                /*Log.d("Value", input.getText().toString());
                                FragmentManager fm = getSupportFragmentManager();
                                MapsDialogFragment mapsDialogFragment = MapsDialogFragment.newInstance(getString(R.string.addCityTitle));
                                mapsDialogFragment.show(fm,"fragment_add_place");*/
                            }
                        })
                        .show();

            }
        });

    }

    private void mapsDialog(){
        Dialog dialog = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.maps_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.show();

        MapView mapView = (MapView) dialog.findViewById(R.id.mapsView);
        MapsInitializer.initialize(HomeActivity.this);

        mapView.onCreate(dialog.onSaveInstanceState());
        mapView.onResume();// needed to get the map to display immediately
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

            }
        });
    }

    private void setupReyclerView(){
        citiesRecyclerView = (RecyclerView) findViewById(R.id.citiesRecyclerView);

        citiesAdapter = new CitiesAdapter(getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        citiesRecyclerView.setLayoutManager(linearLayoutManager);
        citiesRecyclerView.setAdapter(citiesAdapter);
    }
}
