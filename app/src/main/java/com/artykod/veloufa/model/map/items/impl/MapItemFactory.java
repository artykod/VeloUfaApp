package com.artykod.veloufa.model.map.items.impl;

import android.location.Location;

import com.artykod.veloufa.model.map.items.MapItem;
import com.artykod.veloufa.model.map.items.impl.lines.MapItemBicycleTrack;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerParking;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerRental;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerService;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerShop;

import java.util.ArrayList;

public class MapItemFactory {
    public static MapItem buildBicycleTrack(ArrayList<Location> points) {
        return new MapItemBicycleTrack(points);
    }

    public static MapItem buildBicycleTrackFromRawPoints(String rawPoints) {
        ArrayList<Location> points = new ArrayList<>();

        String[] coordsList = rawPoints.split(" ");
        for (String i : coordsList) {
            points.add(parseRawPoint(i));
        }

        return buildBicycleTrack(points);
    }

    public static MapItem buildParking(Location location) {
        return new MapItemPointerParking(location);
    }

    public static MapItem buildParkingFromRawPoint(String rawPoint) {
        return buildParking(parseRawPoint(rawPoint));
    }

    public static MapItem buildRental(Location location) {
        return new MapItemPointerRental(location);
    }

    public static MapItem buildRentalFromRawPoint(String rawPoint) {
        return buildRental(parseRawPoint(rawPoint));
    }

    public static MapItem buildService(Location location) {
        return new MapItemPointerService(location);
    }

    public static MapItem buildServiceFromRawPoint(String rawPoint) {
        return buildService(parseRawPoint(rawPoint));
    }

    public static MapItem buildShop(Location location) {
        return new MapItemPointerShop(location);
    }

    public static MapItem buildShopFromRawPoint(String rawPoint) {
        return buildShop(parseRawPoint(rawPoint));
    }

    private static Location parseRawPoint(String rawPoint) {
        String[] coords = rawPoint.split(",");
        double latitude = Double.parseDouble(coords[1]);
        double longitude = Double.parseDouble(coords[0]);
        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return location;
    }
}
