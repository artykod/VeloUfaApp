package com.artykod.veloufa.model.map.parser.impl;

import android.content.Context;

import com.artykod.veloufa.model.map.items.MapItem;
import com.artykod.veloufa.model.map.parser.MapItemsParser;
import com.artykod.veloufa.utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class MapItemsParserBase implements MapItemsParser {
    protected Context context = null;

    public MapItemsParserBase(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<MapItem> parseTextAsset(String textAssetName) {
        String content;
        ArrayList<MapItem> mapItems = null;

        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream json = context.getResources().getAssets().open(textAssetName);
            BufferedReader in = new BufferedReader(new InputStreamReader(json));
            String str;

            while ((str = in.readLine()) != null) {
                stringBuilder.append(str);
            }
            in.close();

            content = stringBuilder.toString();
        } catch (IOException e) {
            content = null;
        }

        try {
            mapItems = parse(content);
        } catch (Exception e) {
            Logger.warning("Exception while parsing kml " + textAssetName + ": " + e.getMessage());
        }

        return mapItems;
    }

    protected abstract ArrayList<MapItem> parse(String content) throws Exception;
}
