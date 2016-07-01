package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class KmlDocument {
    @Attribute(name = "id")
    public String id;

    @Element(name = "name")
    public String name;

    @ElementList(entry = "Placemark", inline = true)
    public List<KmlPlacemark> placemarks;

    @ElementList(entry = "Style", inline = true)
    public List<KmlStyle> styles;

    public KmlStyle getStyleForPlacemark(KmlPlacemark placemark) {
        String searchStyleId = placemark.styleUrl.substring(1); // remove #

        for (KmlStyle i : styles) {
            if (i.id.equals(searchStyleId)) {
                return i;
            }
        }

        return null;
    }
}
