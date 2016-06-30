package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Element;

public class KmlCoordinates {
    @Element(name = "tessellate", required = false)
    public int tessellate;

    @Element(name = "coordinates")
    public String coordinates;
}
