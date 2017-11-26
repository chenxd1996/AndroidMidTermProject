package com.example.dell.midterm;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class person_detail extends AppCompatActivity {
    public database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        db = new database(this);
        ImageView pic = (ImageView) findViewById(R.id.pic);
        HashMap<filtersData.filterType, String> filters_selected = filtersData.getInstance().getFiltersSelected();
        ArrayList<Person> results = db.myfind(filters_selected);
        Person p = results.get(0);
        Log.e("test", p.picture);
        pic.setImageURI(Uri.parse(p.picture));
    }
}