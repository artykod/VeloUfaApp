package com.artykod.veloufa.views;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.artykod.veloufa.R;
import com.artykod.veloufa.model.map.items.MapItem;

public class MapItemInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_item_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MapItem.ParcelInfo mapItem = getIntent().getParcelableExtra("mapItem");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mapItem.name);
        }

        TextView titleText = (TextView) findViewById(R.id.title);
        if (titleText != null) {
            titleText.setText(mapItem.name);
        }

        TextView descriptionText = (TextView) findViewById(R.id.description);
        if (descriptionText != null) {
            descriptionText.setText(mapItem.description);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
