package com.example.erickmwazonga.myspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner spinner;
    CheckBox c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner= (Spinner) findViewById(R.id.spinner);
        c= (CheckBox) findViewById(R.id.checkBox);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
        c.setOnClickListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView temp= (TextView) view;
        Toast.makeText(this,"You are talking to "+temp.getText(),Toast.LENGTH_LONG).show();
        //spinner.setPrompt("Select your friend");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        CheckBox t= (CheckBox) v;
        if(t.isChecked()){
            Toast.makeText(this,"I am good",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Not good",Toast.LENGTH_SHORT).show();
        }
    }
}
