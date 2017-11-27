package com.example.dell.midterm;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    HashMap<filtersData.filterType, String> filters_selected = new HashMap<>();
    private database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        init();
    }

    private void init() {
        final filtersData fd = filtersData.getInstance();
        ArrayList<String> first_char_filter = fd.getFilter(filtersData.filterType.first_char_filter);
        initFilterView(first_char_filter, R.id.first_char_filter, filtersData.filterType.first_char_filter);
        ArrayList<String> native_place_modern = fd.getFilter(filtersData.filterType.native_place_modern_filter);
        initFilterView(native_place_modern, R.id.naive_place_modern_filter, filtersData.filterType.native_place_modern_filter);
        ArrayList<String> native_place_ancient = fd.getFilter(filtersData.filterType.native_place_ancient_filter);
        initFilterView(native_place_ancient, R.id.naive_place_ancient_filter, filtersData.filterType.native_place_ancient_filter);
        ArrayList<String> camp = fd.getFilter(filtersData.filterType.camp_filter);
        initFilterView(camp, R.id.camp_filter, filtersData.filterType.camp_filter);
        ArrayList<String> ages = fd.getFilter(filtersData.filterType.age_filter);
        initFilterView(ages, R.id.age_filter, filtersData.filterType.age_filter);
        ArrayList<String> sexs = fd.getFilter(filtersData.filterType.sex_filter);
        initFilterView(sexs, R.id.sex_filter, filtersData.filterType.sex_filter);

        filters_selected.put(filtersData.filterType.first_char_filter, "");
        filters_selected.put(filtersData.filterType.native_place_ancient_filter, "");
        filters_selected.put(filtersData.filterType.native_place_modern_filter, "");
        filters_selected.put(filtersData.filterType.camp_filter, "");
        filters_selected.put(filtersData.filterType.age_filter, "");
        filters_selected.put(filtersData.filterType.sex_filter, "");

        Button addBtn = (Button) findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, create_person.class);
                startActivity(intent);
            }
        });

        Button searchBtn = (Button) findViewById(R.id.search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.name_filter);
                String name = et.getText().toString();
                filters_selected.put(filtersData.filterType.name_filter, name);
                filtersData.getInstance().setFiltersSelected(filters_selected);
                Intent intent = new Intent(MainActivity.this, show_result.class);
                startActivity(intent);
            }
        });

        db = new database(this);
        Cursor cursor = db.getReadableDatabase().rawQuery("select count(*) from " + database.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            int count = cursor.getInt(0);
            if (count == 0) {
                for (int i = 0; i < 10; i++) {
                    String name = Character.toString((char)((int)'A' + i));
                    db.myinsert(name, "男", name.charAt(0) + "", "魏",
                            "北京", "荆州", 50 + i, "路人" + name, null);
                }
            }
        }
        cursor.close();
        db.close();

    }

    private void initFilterView(ArrayList<String> data, int viewId, final filtersData.filterType type) {
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
                if (v.getText().toString().equals("不限"))
                    filters_selected.put(type, "");
                else
                    filters_selected.put(type, v.getText().toString());
            }
        });
    }

    private void verifyStoragePermissions(Activity activity) {
        try {
            int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
