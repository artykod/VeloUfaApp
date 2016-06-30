package com.artykod.veloufa.model.map.control.impl.google;

import android.content.Context;
import android.location.Location;

import com.artykod.veloufa.R;
import com.artykod.veloufa.model.map.control.impl.MapControllerBase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapControllerGoogle extends MapControllerBase {
    private GoogleMap map = null;
    private Map<Integer, BitmapDescriptor> iconsCache = new HashMap<>();

    public MapControllerGoogle(Context context, GoogleMap googleMap) {
        super(context);

        map = googleMap;

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                clickedOnMarker(marker.getId());
            }
        });
    }

    @Override
    public void navigate(Location location, float zoomFactor) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngFromLocation(location), zoomFactor));
    }

    @Override
    public String drawPolyline(ArrayList<Location> points, int width, int lineColor) {
        PolylineOptions polylineOptions = new PolylineOptions();

        for (int i = 1; i < points.size(); i++) {
            LatLng prev = latLngFromLocation(points.get(i - 1));
            LatLng curr = latLngFromLocation(points.get(i));

            polylineOptions.add(prev, curr)
                    .clickable(true)
                    .width(width)
                    .color(lineColor);
        }

        return map.addPolyline(polylineOptions).getId();
    }

    @Override
    public String drawMarker(Location location, int iconResource, String title, String description) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLngFromLocation(location))
                .icon(getIconFromCache(iconResource))
                .title(title)
                .snippet(context.getResources().getString(R.string.map_item_more_info));

        return map.addMarker(markerOptions).getId();
    }

    @Override
    protected void clearMap() {
        map.clear();
    }

    private BitmapDescriptor getIconFromCache(int iconResource) {
        if (iconsCache.containsKey(iconResource)) {
            return iconsCache.get(iconResource);
        } else {
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(iconResource);
            iconsCache.put(iconResource, icon);
            return icon;
        }
    }

    private LatLng latLngFromLocation(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
