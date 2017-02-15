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
public class ListAssignmentAdapter extends ArrayAdapter{
    List list= new ArrayList();
    public ListAssignmentAdapter(Context context, int resource) {
        super(context, resource);
    }
    static class LayoutHandler{
        TextView name,status, date, time;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout_assignment,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name= (TextView) row.findViewById(R.id.textViewAssignmentName);
            layoutHandler.status= (TextView) row.findViewById(R.id.textViewAssignmentStatus);
            layoutHandler.date= (TextView) row.findViewById(R.id.textViewAssignmentDate);
            layoutHandler.time= (TextView) row.findViewById(R.id.textViewAssignmentTime);
            row.setTag(layoutHandler);
        }else{
            layoutHandler= (LayoutHandler) row.getTag();
        }
        DataProviderAssignment dataProviderAssignment= (DataProviderAssignment) this.getItem(position);
        layoutHandler.name.setText(dataProviderAssignment.getName());
        layoutHandler.status.setText(dataProviderAssignment.getStatus());
        layoutHandler.date.setText(dataProviderAssignment.getDate());
        layoutHandler.time.setText(dataProviderAssignment.getTime());
        return row;
    }
}
