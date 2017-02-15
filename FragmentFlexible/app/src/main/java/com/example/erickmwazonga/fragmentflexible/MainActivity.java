package com.example.erickmwazonga.fragmentflexible;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void respond(int i){
        FragmentManager manager=getSupportFragmentManager();
        Fragmentb fb= (Fragmentb) manager.findFragmentById(R.id.fragment2);
        fb.changeData(i);
    }
}
