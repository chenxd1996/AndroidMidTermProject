package com.example.dell.midterm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        filtersData fd = filtersData.getInstance();
        ArrayList<String> first_char_filter = fd.getFilter(filtersData.filterType.first_char_filter);
        initFilterView(first_char_filter, R.id.first_char_filter);
        ArrayList<String> native_place_modern = fd.getFilter(filtersData.filterType.native_place_modern_filter);
        initFilterView(native_place_modern, R.id.naive_place_modern_filter);
        ArrayList<String> native_place_ancient = fd.getFilter(filtersData.filterType.native_place_ancient_filter);
        initFilterView(native_place_ancient, R.id.naive_place_ancient_filter);
        ArrayList<String> camp = fd.getFilter(filtersData.filterType.camp_filter);
        initFilterView(camp, R.id.camp_filter);
        ArrayList<String> ages = fd.getFilter(filtersData.filterType.age_filter);
        initFilterView(ages, R.id.age_filter);
        ArrayList<String> sexs = fd.getFilter(filtersData.filterType.sex_filter);
        initFilterView(sexs, R.id.sex_filter);
    }

    private void initFilterView(ArrayList<String> data, int viewId) {
        ArrayList<HashMap<String, String>> list_items = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("filter_item_text", data.get(i));
            list_items.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                list_items,
                R.layout.filter_item,
                new String[] {"filter_item_text"},
                new int[] {R.id.filter_item_text});
        final GridView gridView = (GridView) findViewById(viewId);
        final ViewTreeObserver viewTreeObserver = gridView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View firstChild = gridView.getChildAt(0);
                firstChild.setBackgroundColor(getResources().getColor(R.color.colorGreen, null));
                gridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    parent.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorWhite, null));
                    TextView v = (TextView) parent.getChildAt(i).findViewById(R.id.filter_item_text);
                    v.setTextColor(getResources().getColor(R.color.colorBlack, null));
                }
                view.setBackgroundColor(getResources().getColor(R.color.colorGreen, null));
                TextView v = (TextView) view.findViewById(R.id.filter_item_text);
                v.setTextColor(getResources().getColor(R.color.colorWhite, null));
            }
        });
    }
}
