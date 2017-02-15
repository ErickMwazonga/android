package com.example.erickmwazonga.fragmentflexible2;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragmenta.Communicator {

    Fragmenta fa;
    Fragmentb fb;
    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=getSupportFragmentManager();
        fa= (Fragmenta) manager.findFragmentById(R.id.fragment);
        fa.setCommunicator(this);
    }

    @Override
    public void respond(int index) {
        fb= (Fragmentb) manager.findFragmentById(R.id.fragment2);
        if(fb.isVisible()){
            fb.changeData(index);
        }else{
            Intent intent=new Intent(this, AnotherActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
    }
}
