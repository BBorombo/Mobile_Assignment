package com.borombo.mobileassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.borombo.mobileassignment.adapters.LocationsAdapter;
import com.borombo.mobileassignment.R;

public class HomeActivity extends LateralMenuActivity {

    private RecyclerView citiesRecyclerView;
    private LocationsAdapter locationsAdapter;
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
                locationsAdapter.swap();
                refreshLayout.setRefreshing(false);
            }
        });

        addCityButton = (FloatingActionButton) findViewById(R.id.addCityFAB);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, AddLocationActivity.class);
            startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        locationsAdapter.swap();
    }

    private void setupReyclerView(){
        citiesRecyclerView = (RecyclerView) findViewById(R.id.citiesRecyclerView);

        locationsAdapter = new LocationsAdapter(getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        citiesRecyclerView.setLayoutManager(linearLayoutManager);
        citiesRecyclerView.setAdapter(locationsAdapter);
    }
}
