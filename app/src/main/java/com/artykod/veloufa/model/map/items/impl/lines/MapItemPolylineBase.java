package com.artykod.veloufa.model.map.items.impl.lines;

import android.location.Location;

import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.items.MapItemPolyline;
import com.artykod.veloufa.model.map.items.impl.MapItemBase;

import java.util.ArrayList;

public abstract class MapItemPolylineBase extends MapItemBase implements MapItemPolyline {
    private ArrayList<Location> points = null;

    public MapItemPolylineBase(ArrayList<Location> points) {
        this.points = (ArrayList<Location>)points.clone();
    }

    @Override
    protected void drawSelf(MapController mapController) {
        mapController.drawPolyline(points, lineWidth(), lineColor());
    }

    protected abstract int lineWidth();
    protected abstract int lineColor();
}
