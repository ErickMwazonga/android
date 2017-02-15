package com.example.erickmwazonga.gridlayout;

/**
 * Created by Erick Mwazonga on 7/6/2016.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.albuma, R.drawable.albumi,
            R.drawable.albumb, R.drawable.albumf,
            R.drawable.albumc, R.drawable.albuma,
            R.drawable.albumd, R.drawable.albumb,
            R.drawable.albume, R.drawable.albumc,
            R.drawable.albumf, R.drawable.albumd,
            R.drawable.albumg, R.drawable.albume,
            R.drawable.albumh
    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        return imageView;
    }

}