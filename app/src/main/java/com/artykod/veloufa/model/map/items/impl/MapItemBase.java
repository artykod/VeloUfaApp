package com.artykod.veloufa.model.map.items.impl;

import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.items.MapItem;

public abstract class MapItemBase implements MapItem {
    protected boolean isVisible = true;
    protected String name;
    protected String description;
    protected String markerId;

    @Override
    public void draw(MapController mapController) {
        if (isVisible) {
            markerId = drawSelf(mapController);
        }
    }

    @Override
    public boolean getVisibility() {
        return isVisible;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setDescription(String value) {
        description = value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setVisibility(boolean value) {
        isVisible = value;
    }

    @Override
    public String getMarkerId() {
        return markerId;
    }

    protected abstract String drawSelf(MapController mapController);
}
