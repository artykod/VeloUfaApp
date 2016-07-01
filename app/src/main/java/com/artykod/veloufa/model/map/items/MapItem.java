package com.artykod.veloufa.model.map.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.artykod.veloufa.model.map.control.MapController;

import java.io.Serializable;

public interface MapItem extends Serializable {
    void draw(MapController mapController);
    void setVisibility(boolean value);
    void setName(String value);
    void setDescription(String value);
    String getName();
    String getDescription();
    String getMarkerId();
    boolean getVisibility();

    class ParcelInfo implements Parcelable {
        public String name;
        public String description;

        public ParcelInfo(MapItem fromItem) {
            if (fromItem != null) {
                name = fromItem.getName();
                description = fromItem.getDescription();
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(description);
        }

        public static final Parcelable.Creator<ParcelInfo> CREATOR = new Creator<ParcelInfo>() {
            @Override
            public ParcelInfo createFromParcel(Parcel source) {
                ParcelInfo instance = new ParcelInfo(null);
                instance.name = source.readString();
                instance.description = source.readString();
                return instance;
            }

            @Override
            public ParcelInfo[] newArray(int size) {
                return new ParcelInfo[size];
            }
        };
    }
}
