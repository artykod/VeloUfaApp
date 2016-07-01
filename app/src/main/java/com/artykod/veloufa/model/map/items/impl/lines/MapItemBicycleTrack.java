package com.artykod.veloufa.model.map.items.impl.lines;

import android.location.Location;

import java.util.ArrayList;

public class MapItemBicycleTrack extends MapItemPolylineBase {
    private int lineWidth;
    private int lineColor;

    public MapItemBicycleTrack(ArrayList<Location> points, int width, int color) {
        super(points);

        lineWidth = width;
        lineColor = color;
    }

    @Override
    protected int lineWidth() {
        return lineWidth;
    }

    @Override
    protected int lineColor() {
        return lineColor;
    }
}
