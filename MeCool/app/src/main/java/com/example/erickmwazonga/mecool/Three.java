package com.example.erickmwazonga.mecool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Three extends AppCompatActivity implements  View.OnClickListener {

    Button back,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.next:
                startActivity(new Intent(this, Four.class));
                break;
            case R.id.back:
                startActivity(new Intent(this, Two.class));
                break;
        }
    }
}
