package com.example.erickmwazonga.alertdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showDialog(View view){
        AlertClass alertClass=new AlertClass();
        alertClass.show(getSupportFragmentManager(),"My Alert");
    }
}
