package com.borombo.mobileassignment.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.borombo.mobileassignment.R;

/**
 * Created by Borombo on 24/06/2017.
 *
 * Custom activity which have a Navigation view.
 * This class is extend from all the activity that need the navigation view.
 */

public abstract class LateralMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected Toolbar toolbar;

    /**
     * Setup all what is needed for that the DrawerLayout and NavigationView works fine
     */
    protected  void setupActivity(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent();
        // Start the right activity when user select a item on the menu list
        switch (id){
            case R.id.home:
                intent = new Intent(LateralMenuActivity.this, HomeActivity.class);
                break;
            case R.id.settings:
                intent = new Intent(LateralMenuActivity.this, MyPreferencesActivity.class);
                break;
            case R.id.help:
                intent = new Intent(LateralMenuActivity.this, HelpActivity.class);
                break;
        }
        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // Close the drawer if it's open on back pressed
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
