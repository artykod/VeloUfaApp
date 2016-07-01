package com.artykod.veloufa.model.map.control;

import android.location.Location;

import com.artykod.veloufa.model.map.items.MapItem;

import java.lang.reflect.Type;
import java.util.ArrayList;

public interface MapController {
    void navigate(Location location, float zoomFactor);
    void addItems(ArrayList<MapItem> items);
    void update();
    void setItemsVisibility(Type itemsType, boolean isVisible);
    boolean getItemsVisibility(Type itemsType);
    String drawPolyline(ArrayList<Location> points, int width, int lineColor);
    String drawMarker(Location location, int iconResource, String title, String description);

    void setClickListener(ClickListener listener);

    interface ClickListener {
        void onItemClick(MapItem mapItem);
    }
}
