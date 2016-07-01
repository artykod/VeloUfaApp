package com.artykod.veloufa.views;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.artykod.veloufa.R;
import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.control.impl.MapControllerFactory;
import com.artykod.veloufa.model.map.items.MapItem;
import com.artykod.veloufa.model.map.parser.MapItemsParser;
import com.artykod.veloufa.model.map.parser.impl.MapItemsParserFactory;
import com.artykod.veloufa.views.main.MapFloatingActionButton;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        SupportMapFragment mapFragment = (SupportMapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                initMapController(googleMap);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!closeDrawer()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_vk:
                openUrl(R.string.url_vk_community);
                break;
            case R.id.nav_forum:
                openUrl(R.string.url_forum);
                break;
            case R.id.nav_github:
                openUrl(R.string.url_github);
                break;
        }

        closeDrawer();

        return true;
    }

    private boolean closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return true;
        } else {
            return false;
        }
    }

    private void initMapController(GoogleMap googleMap) {
        MapController mapController = MapControllerFactory.buildGoogleMap(this, googleMap);

        //  Ufa location
        float zoomFactor = 11f;
        Location location = new Location("");
        location.setLatitude(54.76092125581864d);
        location.setLongitude(56.011763513088226d);
        mapController.navigate(location, zoomFactor);

        MapItemsParser parser = MapItemsParserFactory.buildKmlParser(this);
        String[] kmls = new String[] {
                "parks.kml",
                "rentals.kml",
                "services.kml",
                "shops.kml",
                "tracks.kml"
        };
        for (String i : kmls) {
            mapController.addItems(parser.parseTextAsset(i));
        }

        mapController.update();

        MapFloatingActionButton fab = (MapFloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setMapController(mapController);
        }

        mapController.setClickListener(new MapController.ClickListener() {
            @Override
            public void onItemClick(MapItem mapItem) {
                showMapItemInfo(mapItem);
            }
        });
    }

    private void showMapItemInfo(MapItem mapItem) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setClass(this, MapItemInfoActivity.class);
        i.putExtra("mapItem", new MapItem.ParcelInfo(mapItem));
        startActivity(i);
    }

    private void openUrl(int url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(getResources().getString(url)));
        startActivity(i);
    }
}
