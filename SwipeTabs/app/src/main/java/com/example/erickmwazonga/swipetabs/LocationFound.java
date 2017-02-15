package com.example.erickmwazonga.swipetabs;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.io.IOException;

public class LocationFound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_found);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set the icon
        toolbar.setNavigationIcon(R.drawable.ic_action_location);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




//        // get action bar
//        ActionBar actionBar = getActionBar();
//
//        // Enabling Up / Back navigation
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
