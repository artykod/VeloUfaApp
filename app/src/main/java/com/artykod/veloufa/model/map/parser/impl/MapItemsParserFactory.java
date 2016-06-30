package com.artykod.veloufa.model.map.parser.impl;

import android.content.Context;

import com.artykod.veloufa.model.map.parser.MapItemsParser;
import com.artykod.veloufa.model.map.parser.impl.kml.MapItemsParserKml;

public class MapItemsParserFactory {
    public static MapItemsParser buildKmlParser(Context context) {
        return new MapItemsParserKml(context);
    }
}
