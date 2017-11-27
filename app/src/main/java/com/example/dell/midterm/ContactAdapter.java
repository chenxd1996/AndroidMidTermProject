package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/26.
 */
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class ContactAdapter extends ArrayAdapter<Person> {
    private int resourceId;

    public ContactAdapter(Context context, int textViewResourceId, List<Person> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person contact = getItem(position);
        View view;
        if (convertView == null) {
            view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }
        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(contact.name);
        ImageView img = (ImageView) view.findViewById(R.id.result_image);
        if (contact.picture != null) {
            img.setImageURI(Uri.parse(contact.picture));
        } else {
            img.setImageURI(null);
        }
        return view;
    }
}

