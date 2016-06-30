package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class KmlDocument {
    @Element(name = "name")
    public String name;

    @ElementList(entry = "Placemark", inline = true)
    public List<KmlPlacemark> placemarks;

    @ElementList(entry = "Style", inline = true)
    public List<KmlStyle> styles;
}
