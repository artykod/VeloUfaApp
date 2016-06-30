package com.artykod.veloufa.model.map.items;

import com.artykod.veloufa.model.map.control.MapController;

public interface MapItem {
    String getTitle();
    void draw(MapController mapController);
    void setVisibility(boolean value);
    boolean getVisibility();
}
