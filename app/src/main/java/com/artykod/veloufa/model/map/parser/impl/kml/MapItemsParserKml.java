package com.artykod.veloufa.model.map.parser.impl.kml;

import android.content.Context;

import com.artykod.veloufa.model.map.items.MapItem;
import com.artykod.veloufa.model.map.items.impl.MapItemFactory;
import com.artykod.veloufa.model.map.parser.impl.MapItemsParserBase;
import com.artykod.veloufa.model.map.parser.impl.kml.structure.KmlPlacemark;
import com.artykod.veloufa.model.map.parser.impl.kml.structure.KmlRoot;

import org.simpleframework.xml.core.Persister;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

public class MapItemsParserKml extends MapItemsParserBase {
    public MapItemsParserKml(Context context) {
        super(context);
    }

    @Override
    protected ArrayList<MapItem> parse(String content) throws Exception {
        ArrayList<MapItem> items = new ArrayList<>();
        Reader reader = new StringReader(content);
        Persister serializer = new Persister();
        KmlRoot kmlRoot;

        kmlRoot = serializer.read(KmlRoot.class, reader, false);
        if (kmlRoot != null) {
            for (KmlPlacemark i : kmlRoot.document.placemarks) {
                MapItem item = null;

                if (i.line != null) {
                    item = MapItemFactory.buildBicycleTrackFromRawPoints(i.line.coordinates);
                } else if (i.point != null) {
                    String coords = i.point.coordinates;
                    switch (i.styleUrl) {
                        case "#icon-1453":
                            item = MapItemFactory.buildParkingFromRawPoint(coords);
                            break;
                        case "#icon-1193":
                            item = MapItemFactory.buildRentalFromRawPoint(coords);
                            break;
                        case "#icon-1033":
                            item = MapItemFactory.buildServiceFromRawPoint(coords);
                            break;
                        case "#icon-1101":
                            item = MapItemFactory.buildShopFromRawPoint(coords);
                            break;
                    }

                }

                if (item != null) {
                    item.setName(i.name);
                    item.setDescription(i.description);
                    items.add(item);
                }
            }
        }

        return items;
    }
}
