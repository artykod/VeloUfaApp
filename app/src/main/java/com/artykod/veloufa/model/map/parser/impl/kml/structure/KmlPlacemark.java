package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Element;

public class KmlPlacemark {
    @Element(name = "styleUrl")
    public String styleUrl;

    @Element(name = "name", required = false)
    public String name;

    @Element(name = "description", required = false)
    public String description;

    @Element(name = "Point", required = false)
    public KmlCoordinates point;

    @Element(name = "LineString", required = false)
    public KmlCoordinates line;
}
