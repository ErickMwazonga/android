package com.example.erickmwazonga.listtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListPraco extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listViewPraco;

    String [] days={"Hanningtone","Gladys","Facetrick","Erick","Lenny","Patrick","Stephen","Purity","Festus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_praco);

        listViewPraco= (ListView) findViewById(R.id.listViewPraco);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,days);
        listViewPraco.setAdapter(adapter);
        listViewPraco.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView temp= (TextView) view;
        Toast.makeText(this,temp.getText()+" "+position,Toast.LENGTH_LONG).show();
    }
}
