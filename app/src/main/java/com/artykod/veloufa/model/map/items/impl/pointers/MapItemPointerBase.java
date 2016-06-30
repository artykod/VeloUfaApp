package com.artykod.veloufa.model.map.items.impl.pointers;

import android.location.Location;

import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.items.MapItemPointer;
import com.artykod.veloufa.model.map.items.impl.MapItemBase;

public abstract class MapItemPointerBase extends MapItemBase implements MapItemPointer {
    protected Location location = null;

    public MapItemPointerBase(Location location) {
        this.location = location;
    }

    protected abstract int iconResource();

    @Override
    protected String drawSelf(MapController mapController) {
        return mapController.drawMarker(location, iconResource(), getName(), getDescription());
    }
}
