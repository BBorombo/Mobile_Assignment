package com.borombo.mobileassignement.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.borombo.mobileassignement.adapters.CitiesAdapter;
import com.borombo.mobileassignement.R;

public class HomeActivity extends LateralMenuActivity {

    private RecyclerView citiesRecyclerView;
    private CitiesAdapter citiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupActivity();

        citiesRecyclerView = (RecyclerView) findViewById(R.id.citiesRecyclerView);

        citiesAdapter = new CitiesAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        citiesRecyclerView.setLayoutManager(linearLayoutManager);
        citiesRecyclerView.setAdapter(citiesAdapter);


    }
}
