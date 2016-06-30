package com.artykod.veloufa.model.map.parser.impl.kml.structure;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "kml")
public class KmlRoot {
    @Element(name = "Document")
    public KmlDocument document;
}
