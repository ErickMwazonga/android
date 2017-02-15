package com.example.erickmwazonga.mecool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class One extends AppCompatActivity implements View.OnClickListener{

    Button back,next;
    ImageView one;
    Bitmap source;
    float angle=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);

        one=(ImageView)findViewById(R.id.one);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.next:
                startActivity(new Intent(this, Two.class));
                break;

        }
    }

}
