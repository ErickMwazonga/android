package com.example.erickmwazonga.studentcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erick Mwazonga on 10/25/2016.
 */
public class ListHolidayAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ListHolidayAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView name, start, end;
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
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout_holiday,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name= (TextView) row.findViewById(R.id.textViewHolidayName);
            layoutHandler.start = (TextView) row.findViewById(R.id.textVieHolidayStart);
            layoutHandler.end= (TextView) row.findViewById(R.id.textViewHolidayEnd);
            row.setTag(layoutHandler);
        }else{
            layoutHandler= (LayoutHandler) row.getTag();
        }
        DataProviderHoliday dataProviderHoliday= (DataProviderHoliday) this.getItem(position);
        layoutHandler.name.setText(dataProviderHoliday.getName());
        layoutHandler.start.setText(dataProviderHoliday.getStart());
        layoutHandler.end.setText(dataProviderHoliday.getEnd());
        return row;
    }
}
