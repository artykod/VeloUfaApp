package com.artykod.veloufa.model.map.parser;

import com.artykod.veloufa.model.map.items.MapItem;

import java.util.ArrayList;

public interface MapItemsParser {
    ArrayList<MapItem> parseTextAsset(String textAssetName);
}
