package com.artykod.veloufa.model.map.control.impl;

import android.content.Context;

import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.control.impl.google.MapControllerGoogle;
import com.google.android.gms.maps.GoogleMap;

public class MapControllerFactory {
    public static MapController buildGoogleMap(Context context, GoogleMap googleMap) {
        return new MapControllerGoogle(context, googleMap);
    }

    public static MapController buildYandexMap() {
        throw new UnsupportedOperationException();
    }
}
