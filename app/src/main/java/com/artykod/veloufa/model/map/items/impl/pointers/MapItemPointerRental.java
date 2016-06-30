package com.artykod.veloufa.model.map.items.impl.pointers;

import android.location.Location;

import com.artykod.veloufa.R;

public class MapItemPointerRental extends MapItemPointerBase {
    public MapItemPointerRental(Location location) {
        super(location);
    }

    @Override
    public String getTitle() {
        return "Rental";
    }

    @Override
    protected int iconResource() {
        return R.drawable.ic_rental;
    }
}
