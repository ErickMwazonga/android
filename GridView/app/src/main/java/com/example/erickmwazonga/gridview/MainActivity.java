package com.example.erickmwazonga.gridview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new uniqueAdapter(this));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, myDialog.class);
        ViewHolder holder = (ViewHolder) view.getTag();
        Country temp = (Country) holder.myCountry.getTag();
        intent.putExtra("countryImage", temp.imageId);
        intent.putExtra("countryName", temp.countryNames);
        startActivity(intent);
    }
}

class Country {
    String countryNames;
    int imageId;

    Country(String countryNames, int imageId) {
        this.countryNames = countryNames;
        this.imageId = imageId;
    }

}

class uniqueAdapter extends BaseAdapter {
    ArrayList<Country> list;
    Context context;

    uniqueAdapter(Context context) {
        this.context = context;

        list = new ArrayList<Country>();
        Resources res = context.getResources();

        String[] tempCountryNames = res.getStringArray(R.array.countryName);
        int[] tempImages = {R.drawable.botswana, R.drawable.burundi, R.drawable.congo,
                R.drawable.eritrea, R.drawable.ethiopia, R.drawable.kenya, R.drawable.malawi,
                R.drawable.nigeria, R.drawable.rwanda, R.drawable.uganda};

        for (int i = 0; i < 10; i++) {
            Country tempCountry = new Country(tempCountryNames[i], tempImages[i]);
            list.add(tempCountry);
        }
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {//recycling
            holder = (ViewHolder) row.getTag();
        }
        Country temp = list.get(position);
        holder.myCountry.setImageResource(temp.imageId);
        holder.myCountry.setTag(temp);
        return row;
    }
}

class ViewHolder {
    ImageView myCountry;

    ViewHolder(View v) {
        myCountry = (ImageView) v.findViewById(R.id.imageView);
    }
}