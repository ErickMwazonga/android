package com.example.erickmwazonga.studentcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Erick Mwazonga on 9/5/2016.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] gridViewString;
    private final int[] gridViewImageId;

    public GridViewAdapter(Context context, String[] gridViewString, int[] gridViewImageId) {
        this.context = context;
        this.gridViewString = gridViewString;
        this.gridViewImageId = gridViewImageId;
    }

    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View gridViewAndroid;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            gridViewAndroid=new View(context);
            gridViewAndroid=inflater.inflate(R.layout.gridview_layout,null);
            TextView textViewAndroid= (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            ImageView imageViewAndroid=(ImageView)gridViewAndroid.findViewById(R.id.android_gridview_image);
            textViewAndroid.setText(gridViewString[i]);
            imageViewAndroid.setImageResource(gridViewImageId[i]);
        }
        else{
            gridViewAndroid=(View)view;
        }

        return gridViewAndroid;
    }
}
