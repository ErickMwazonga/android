package com.example.erickmwazonga.fragmentflexible2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AnotherActivity extends AppCompatActivity {

    Fragmentb fb;
    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Intent intent=getIntent();
        int index=intent.getIntExtra("index",0);

        manager=getSupportFragmentManager();
        fb = (Fragmentb) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        if(fb!=null) {
            fb.changeData(index);
        }
    }
}
