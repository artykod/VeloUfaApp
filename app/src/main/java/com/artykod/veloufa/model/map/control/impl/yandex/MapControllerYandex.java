package com.artykod.veloufa.model.map.control.impl.yandex;

import android.location.Location;

import com.artykod.veloufa.model.map.control.impl.MapControllerBase;

import java.util.ArrayList;

public class MapControllerYandex extends MapControllerBase {
    public MapControllerYandex() {
    }

    @Override
    public void navigate(Location location, float zoomFactor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawPolyline(ArrayList<Location> points, int width, int lineColor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawMarker(Location location, int iconResource, String title) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void clearMap() {
        throw new UnsupportedOperationException();
    }
}
