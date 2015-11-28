package com.example.talento.moneymanagerv4.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.talento.moneymanagerv4.R;

import java.util.List;

/**
 * Created by ErickAlejandro on 24/10/2015.
 */
public class DrawerAdapter extends BaseAdapter {
    List<String> items;

    public DrawerAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.drawer_item, null);
        }

        TextView lblDrawerItemTitle = (TextView) convertView.findViewById(R.id.lblDrawerItemTitle);
        lblDrawerItemTitle.setText((String)getItem(position));

        return convertView;
    }
}
