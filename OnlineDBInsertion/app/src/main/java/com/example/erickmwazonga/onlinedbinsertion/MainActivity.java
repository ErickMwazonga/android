package com.example.erickmwazonga.onlinedbinsertion;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvNetwork;
    Button add,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNetwork= (TextView) findViewById(R.id.network);
        add= (Button) findViewById(R.id.buttonAdd);
        view= (Button) findViewById(R.id.buttonView);

        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null && networkInfo.isConnected()){
            tvNetwork.setVisibility(View.INVISIBLE);
        }else{
            add.setEnabled(false);
            view.setEnabled(false);
        }

    }
    public void addContact(View view){
        startActivity(new Intent(this, Addinfo.class));
    }
}
