package com.artykod.veloufa.model.map.items.impl.lines;

import android.graphics.Color;
import android.location.Location;

import java.util.ArrayList;

public class MapItemBicycleTrack extends MapItemPolylineBase {
    @Override
    public String getTitle() {
        return "BicycleTrack";
    }

    public MapItemBicycleTrack(ArrayList<Location> points) {
        super(points);
    }

    @Override
    protected int lineWidth() {
        return 10;
    }

    @Override
    protected int lineColor() {
        return Color.RED;
    }
}
