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
public class ListExerciseAdapter extends ArrayAdapter {
    List list= new ArrayList();
    public ListExerciseAdapter(Context context, int resource) {
        super(context, resource);
    }
    static class LayoutHandler{
        TextView name,date,time;
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
        View row=convertView;
        LayoutHandler layoutHandler;
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout_exercise,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name= (TextView) row.findViewById(R.id.textViewExerciseName);
            layoutHandler.date= (TextView) row.findViewById(R.id.textViewExerciseDate);
            layoutHandler.time= (TextView) row.findViewById(R.id.textViewExerciseTime);
            row.setTag(layoutHandler);
        }else{
            layoutHandler= (LayoutHandler) row.getTag();
        }
        DataProviderExercise dataProviderExercise= (DataProviderExercise) this.getItem(position);
        layoutHandler.name.setText(dataProviderExercise.getName());
        layoutHandler.date.setText(dataProviderExercise.getDate());
        layoutHandler.time.setText(dataProviderExercise.getTime());
        return row;
    }
}
