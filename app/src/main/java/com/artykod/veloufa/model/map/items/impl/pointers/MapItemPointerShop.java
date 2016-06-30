package com.artykod.veloufa.model.map.items.impl.pointers;

import android.location.Location;

import com.artykod.veloufa.R;

public class MapItemPointerShop extends MapItemPointerBase {
    public MapItemPointerShop(Location location) {
        super(location);
    }

    @Override
    public String getTitle() {
        return "Shop";
    }

    @Override
    protected int iconResource() {
        return R.drawable.ic_shop;
    }
}
