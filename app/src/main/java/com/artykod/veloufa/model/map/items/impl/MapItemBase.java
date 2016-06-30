package com.artykod.veloufa.model.map.items.impl;

import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.items.MapItem;

public abstract class MapItemBase implements MapItem {
    protected boolean isVisible = true;

    @Override
    public boolean getVisibility() {
        return isVisible;
    }

    @Override
    public void setVisibility(boolean value) {
        isVisible = value;
    }

    @Override
    public void draw(MapController mapController) {
        if (isVisible) {
            drawSelf(mapController);
        }
    }

    protected abstract void drawSelf(MapController mapController);
}
