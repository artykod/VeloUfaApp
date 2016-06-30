package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class KmlStyle {
    @Attribute(name = "id")
    public String id;

    @Element(name = "IconStyle", required = false)
    public KmlStyleIcon iconStyle;

    @Element(name = "LineStyle", required = false)
    public KmlStyleLine lineStyle;
}
