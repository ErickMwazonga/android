package com.example.erickmwazonga.databasetestone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erick Mwazonga on 10/20/2016.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView NAME,EMAIL,PASSWORD;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView) row.findViewById(R.id.textViewName);
            layoutHandler.EMAIL= (TextView) row.findViewById(R.id.textViewEmail);
            layoutHandler.PASSWORD= (TextView) row.findViewById(R.id.textViewPassword);
            row.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.EMAIL.setText(dataProvider.getEmail());
        layoutHandler.PASSWORD.setText(dataProvider.getPassword());

        return  row;
    }
}
