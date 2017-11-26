package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/26.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class ContactAdapter extends ArrayAdapter<Contactlist> {
    private int resourceId;

    public ContactAdapter(Context context, int textViewResourceId, List<Contactlist> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contactlist contact = getItem(position);
        View view;
        if (convertView == null) {
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }
        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(contact.getName());

        return view;
    }
}

