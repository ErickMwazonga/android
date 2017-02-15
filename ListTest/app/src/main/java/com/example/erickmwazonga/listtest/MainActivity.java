package com.example.erickmwazonga.listtest;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
/*
    ListView listView;
    String [] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};


    ListView listView;
    String [] data={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
*/
    Button showlist;
    ListView listView;
    String [] memeTitles;
    String [] memeDescriptions;
    int [] images={R.drawable.azx,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.ok,R.drawable.user1,R.drawable.user2,R.drawable.user7,R.drawable.usersttings,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showlist= (Button) findViewById(R.id.showList);
        showlist.setOnClickListener(this);

        Resources res=getResources();
        memeTitles=res.getStringArray(R.array.title);
        memeDescriptions=res.getStringArray(R.array.descriptions);

        listView= (ListView) findViewById(R.id.listView);
        uniqueAdapter adapter=new uniqueAdapter(this,memeTitles,images,memeDescriptions);
        listView.setAdapter(adapter);

        /*listView= (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,days);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        listView= (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView,data);
        listView.setAdapter(adapter);
        */
    }
/*
-    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView temp= (TextView) view;
        Toast.makeText(this, temp.getText()+""+position,Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.showList) {
            startActivity(new Intent(this, ListPraco.class));
        }
    }
}
class uniqueAdapter extends ArrayAdapter<String>{
    Context context;
    int [] imagez;
    String[] TitleArray;
    String [] DescArray;
    public uniqueAdapter(Context c,String [] titles, int [] imgs,String []desc) {
        super(c,R.layout.single_row1, R.id.textView2,titles);
        this.context=c;
        this.imagez=imgs;
        this.TitleArray=titles;
        this.DescArray=desc;
    }
    class myViewHolder{
        ImageView myImage;
        TextView myTitle;
        TextView myDescriptions;
        myViewHolder(View v){
            myImage= (ImageView) v.findViewById(R.id.imageView2);
            myTitle= (TextView) v.findViewById(R.id.textView2);
            myDescriptions= (TextView) v.findViewById(R.id.textView3);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        myViewHolder holder=null;
        if(row==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.single_row1,parent,false);
            holder=new myViewHolder(row);
            row.setTag(holder);
            Log.d("Ricky","Creating a new row");
        }else {
            holder= (myViewHolder) row.getTag();
            Log.d("Ricky","Recycling stuff");
        }
        //LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View row=inflater.inflate(R.layout.single_row1,parent,false);

        holder.myImage.setImageResource(imagez[position]);
        holder.myTitle.setText(TitleArray[position]);
        holder.myDescriptions.setText(DescArray[position]);

        return row;
        //return super.getView(position, convertView, parent);
    }
/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        if(row==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.single_row1,parent,false);
        }
        //LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View row=inflater.inflate(R.layout.single_row1,parent,false);

        ImageView myimage= (ImageView) row.findViewById(R.id.imageView2);
        TextView myTitle= (TextView) row.findViewById(R.id.textView2);
        TextView myDescription= (TextView) row.findViewById(R.id.textView3);

        myimage.setImageResource(imagez[position]);
        myTitle.setText(TitleArray[position]);
        myDescription.setText(DescArray[position]);

        return row;
        //return super.getView(position, convertView, parent);
    }*/
}
