package com.example.erickmwazonga.listviewbaseadapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= (ListView) findViewById(R.id.listView);
        listView.setAdapter(new uniqueAdapter(this));
    }
}
class SingleRow{
    int images;
    String titles;
    String description;

    SingleRow(int images,String titles, String description){
        this.images=images;
        this.titles=titles;
        this.description=description;
    }
}
class uniqueAdapter extends BaseAdapter{

    Context context;
    ArrayList<SingleRow> list;
    uniqueAdapter(Context c){
        Context context=c;
        list=new ArrayList<SingleRow>();
        Resources res=c.getResources();
        String []titles=res.getStringArray(R.array.title);
        String []descptions=res.getStringArray(R.array.descriptions);
        int [] images={R.drawable.azx,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.ok,R.drawable.user1,R.drawable.user2,R.drawable.user7,R.drawable.usersttings,};
        for(int i=0;i<9;i++){
            list.add(new SingleRow(images[i],titles[i],descptions[i]));
        }
    }

    @Override
    public int getCount() {
        return list.size();
        //return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
        //return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
        //return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        if(row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row1, parent, false);
        }
        ImageView image= (ImageView) row.findViewById(R.id.imageView2);
        TextView title= (TextView) row.findViewById(R.id.textView2);
        TextView description= (TextView) row.findViewById(R.id.textView3);

        SingleRow temp=list.get(position);
        title.setText(temp.titles);
        image.setImageResource(temp.images);
        description.setText(temp.description);

        return row;

        //return null;
    }
}
