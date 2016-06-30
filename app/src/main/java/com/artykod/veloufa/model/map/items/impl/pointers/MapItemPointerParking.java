package com.artykod.veloufa.model.map.items.impl.pointers;

import android.location.Location;

import com.artykod.veloufa.R;

public class MapItemPointerParking extends MapItemPointerBase {
    public MapItemPointerParking(Location location) {
        super(location);
    }

    @Override
    protected int iconResource() {
        return R.drawable.ic_park;
    }
}
