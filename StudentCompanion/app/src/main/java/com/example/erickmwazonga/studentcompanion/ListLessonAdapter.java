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
public class ListLessonAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public ListLessonAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView name, venue, startTime;
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
        View row= convertView;
        LayoutHandler layoutHandler;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout_lesson,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name= (TextView) row.findViewById(R.id.textViewName);
            //layoutHandler.day = (TextView) row.findViewById(R.id.textViewDay);
            layoutHandler.venue= (TextView) row.findViewById(R.id.textViewVenue);
            layoutHandler.startTime = (TextView) row.findViewById(R.id.textViewTime);
            row.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProviderLesson dataProviderLesson= (DataProviderLesson) this.getItem(position);
        layoutHandler.name.setText(dataProviderLesson.getName());
        //layoutHandler.day.setText(dataProviderLesson.getDay());
        layoutHandler.venue.setText(dataProviderLesson.getVenue());
        layoutHandler.startTime.setText(dataProviderLesson.getTime());
        return row;
    }
}
