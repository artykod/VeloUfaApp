package com.artykod.veloufa.views;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
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
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerParking;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerRental;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerService;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerShop;
import com.artykod.veloufa.model.map.parser.MapItemsParser;
import com.artykod.veloufa.model.map.parser.impl.MapItemsParserFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private MapController mapController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        final Context context = this;
        ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map))
                .getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mapController = MapControllerFactory.buildGoogleMap(googleMap);

                        //  Ufa location
                        float zoomFactor = 11f;
                        Location location = new Location("");
                        location.setLatitude(54.76092125581864d);
                        location.setLongitude(56.011763513088226d);
                        mapController.navigate(location, zoomFactor);

                        MapItemsParser parser = MapItemsParserFactory.buildKmlParser(context);
                        mapController.addItems(parser.parseTextAsset("parks.kml"));
                        mapController.addItems(parser.parseTextAsset("rentals.kml"));
                        mapController.addItems(parser.parseTextAsset("services.kml"));
                        mapController.addItems(parser.parseTextAsset("shops.kml"));
                        mapController.addItems(parser.parseTextAsset("tracks.kml"));

                        mapController.update();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
            case R.id.nav_park:
                switchItemsVisibility(MapItemPointerParking.class);
                break;
            case R.id.nav_rental:
                switchItemsVisibility(MapItemPointerRental.class);
                break;
            case R.id.nav_service:
                switchItemsVisibility(MapItemPointerService.class);
                break;
            case R.id.nav_shop:
                switchItemsVisibility(MapItemPointerShop.class);
                break;
            case R.id.nav_vk:
                openUrl("https://new.vk.com/veloufa");
                break;
            case R.id.nav_forum:
                openUrl("http://forum.veloufa.ru/");
                break;
        }

        return true;
    }

    private void switchItemsVisibility(Type itemsType) {
        if (mapController != null) {
            mapController.setItemsVisibility(itemsType, !mapController.getItemsVisibility(itemsType));
            mapController.update();
        }
    }

    private void openUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
