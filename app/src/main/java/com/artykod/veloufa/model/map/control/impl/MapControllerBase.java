package com.artykod.veloufa.model.map.control.impl;

import android.content.Context;

import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.items.MapItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class MapControllerBase implements MapController {
    protected Context context;
    protected ArrayList<MapItem> items = new ArrayList<>();
    private Map<Type, Boolean> itemsVisibility = new HashMap<>();
    private Map<String, MapItem> itemsMarkers = new HashMap<>();

    public MapControllerBase(Context context) {
        this.context = context;
    }

    @Override
    public void addItems(ArrayList<MapItem> items) {
        if (items != null) {
            for (MapItem i : items) {
                this.items.add(i);
            }
        }
    }

    @Override
    public void update() {
        clearMap();

        itemsMarkers.clear();

        for (MapItem i : items) {
            i.draw(this);
            itemsMarkers.put(i.getMarkerId(), i);
        }
    }

    @Override
    public void setItemsVisibility(Type itemsType, boolean isVisible) {
        for (MapItem i : items) {
            if (i.getVisibility() != isVisible && i.getClass() == itemsType) {
                i.setVisibility(isVisible);
            }
        }

        itemsVisibility.put(itemsType, isVisible);
    }

    @Override
    public boolean getItemsVisibility(Type itemsType) {
        Boolean result = itemsVisibility.get(itemsType);
        return result == null || result;
    }

    protected abstract void clearMap();

    protected void clickedOnMarker(String markerId) {
        MapItem item = itemsMarkers.get(markerId);
        if (item != null) {

        }
    }
}
