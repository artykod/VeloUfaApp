package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Element;

public class KmlStyleLine {
    @Element(name = "color")
    public String color;

    @Element(name = "width")
    public int width;
}
