package com.artykod.veloufa.model.map.items;

import com.artykod.veloufa.model.map.control.MapController;

public interface MapItem {
    void draw(MapController mapController);

    void setVisibility(boolean value);
    boolean getVisibility();

    void setName(String value);
    String getName();

    void setDescription(String value);
    String getDescription();

    String getMarkerId();
}
