package com.artykod.veloufa.model.map.parser.impl.kml;

import android.content.Context;
import android.graphics.Color;

import com.artykod.veloufa.model.map.items.MapItem;
import com.artykod.veloufa.model.map.items.impl.MapItemFactory;
import com.artykod.veloufa.model.map.parser.impl.MapItemsParserBase;
import com.artykod.veloufa.model.map.parser.impl.kml.structure.KmlDocument;
import com.artykod.veloufa.model.map.parser.impl.kml.structure.KmlPlacemark;
import com.artykod.veloufa.model.map.parser.impl.kml.structure.KmlRoot;
import com.artykod.veloufa.model.map.parser.impl.kml.structure.KmlStyle;

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
            KmlDocument document = kmlRoot.document;
            if (document != null) {
                for (KmlPlacemark i : document.placemarks) {
                    MapItem item = null;
                    KmlStyle style = document.getStyleForPlacemark(i);

                    if (style != null) {
                        if (i.line != null && style.lineStyle != null) {
                            String coords = i.line.coordinates;
                            int width = style.lineStyle.width;
                            int color = Color.parseColor("#" + style.lineStyle.color);

                            switch (document.id) {
                                case "track":
                                    item = MapItemFactory.buildBicycleTrackFromRawPoints(
                                            coords, width, color);
                                    break;
                            }
                        } else if (i.point != null && style.iconStyle != null) {
                            String coords = i.point.coordinates;

                            switch (document.id) {
                                case "parking":
                                    item = MapItemFactory.buildParkingFromRawPoint(coords);
                                    break;
                                case "rental":
                                    item = MapItemFactory.buildRentalFromRawPoint(coords);
                                    break;
                                case "service":
                                    item = MapItemFactory.buildServiceFromRawPoint(coords);
                                    break;
                                case "shop":
                                    item = MapItemFactory.buildShopFromRawPoint(coords);
                                    break;
                            }

                        }
                    }

                    if (item != null) {
                        item.setName(i.name);
                        item.setDescription(i.description);
                        items.add(item);
                    }
                }
            }
        }

        return items;
    }
}
