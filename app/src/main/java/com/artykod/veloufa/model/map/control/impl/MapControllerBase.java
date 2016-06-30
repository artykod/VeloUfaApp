package com.artykod.veloufa.model.map.control.impl;

import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.items.MapItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class MapControllerBase implements MapController {
    protected ArrayList<MapItem> items = new ArrayList<>();
    private Map<Type, Boolean> itemsVisibility = new HashMap<>();

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

        for (MapItem i : items) {
            i.draw(this);
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
        return !itemsVisibility.containsKey(itemsType) || itemsVisibility.get(itemsType);
    }

    protected abstract void clearMap();
}
