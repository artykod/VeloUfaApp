package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;

public class KmlStyleIcon {
    @Element(name = "scale")
    public float scale;

    @Path(value = "Icon")
    @Element(name = "href")
    public String href;
}
