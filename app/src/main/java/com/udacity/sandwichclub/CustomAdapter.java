package com.udacity.sandwichclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    String[] arrayList;

    public CustomAdapter(@NonNull Context context, @NonNull String[] objects) {
        super(context, 0, objects);
        arrayList=objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_view_item,parent,false);
        }
        TextView list_item_tv=listItemView.findViewById(R.id.list_item);

        list_item_tv.setText(arrayList[position]);
        return listItemView;
    }
}
