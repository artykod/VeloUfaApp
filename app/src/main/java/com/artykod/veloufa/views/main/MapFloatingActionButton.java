package com.artykod.veloufa.views.main;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.artykod.veloufa.R;
import com.artykod.veloufa.model.map.control.MapController;
import com.artykod.veloufa.model.map.items.impl.lines.MapItemBicycleTrack;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerParking;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerRental;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerService;
import com.artykod.veloufa.model.map.items.impl.pointers.MapItemPointerShop;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MapFloatingActionButton extends FloatingActionButton {
    class MapItemNaming {
        private Type itemType;
        private int nameResource;

        public MapItemNaming(Type type, int name) {
            itemType = type;
            nameResource = name;
        }

        public Type getItemType() {
            return itemType;
        }
        public String getName() {
            return getResources().getString(nameResource);
        }
    }

    private MapController mapController;
    private ListAdapter listAdapter;
    private ListPopupWindow listPopupWindow;
    private List<MapItemNaming> mapItems;

    public MapFloatingActionButton(Context context) {
        super(context);
    }

    public MapFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
        setVisibility(VISIBLE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setVisibility(INVISIBLE);

        mapItems = new ArrayList<>();
        mapItems.add(new MapItemNaming(MapItemPointerParking.class, R.string.map_item_parking));
        mapItems.add(new MapItemNaming(MapItemPointerRental.class, R.string.map_item_rental));
        mapItems.add(new MapItemNaming(MapItemPointerShop.class, R.string.map_item_service));
        mapItems.add(new MapItemNaming(MapItemPointerService.class, R.string.map_item_shop));
        mapItems.add(new MapItemNaming(MapItemBicycleTrack.class, R.string.map_item_track));

        List<String> names = new ArrayList<>();
        for (MapItemNaming i : mapItems) {
            names.add(i.getName());
        }

        listAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_multiple_choice, names);

        final View selfView = this;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapController != null) {
                    listPopupWindow = new ListPopupWindow(getContext());
                    listPopupWindow.setAdapter(listAdapter);
                    listPopupWindow.setAnchorView(selfView);
                    listPopupWindow.setModal(true);
                    listPopupWindow.setWidth((int) getResources().getDimension(R.dimen.map_item_selector));
                    listPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            listPopupWindow = null;
                        }
                    });
                    listPopupWindow.show();

                    final ListView listView = listPopupWindow.getListView();
                    listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            boolean isChecked = listView.getCheckedItemPositions().get(position);
                            mapController.setItemsVisibility(mapItems.get(position).getItemType(), isChecked);
                            mapController.update();
                        }
                    });

                    for (int i = 0; i < mapItems.size(); i++) {
                        boolean isChecked = mapController.getItemsVisibility(mapItems.get(i).getItemType());
                        listView.setItemChecked(i, isChecked);
                    }
                }
            }
        });
    }
}
