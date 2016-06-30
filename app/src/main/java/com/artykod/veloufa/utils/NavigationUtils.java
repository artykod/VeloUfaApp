package com.artykod.veloufa.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class NavigationUtils {
    public static Location getCurrentLocation(Context context) {
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        if (lm == null) {
            return null;
        }

        Location location;

        try {
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        } catch (SecurityException e) {
            location = null;
        }

        if (location == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            String provider = lm.getBestProvider(criteria, true);

            try {
                location = lm.getLastKnownLocation(provider);
            } catch (SecurityException e) {
                location = null;
            }
        }

        return location;
    }
}
