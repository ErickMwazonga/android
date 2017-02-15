package com.example.erickmwazonga.metame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.Buffer;

public class Register extends AppCompatActivity implements View.OnClickListener{
    EditText etUsername, etPassword;
    Button btLoad, btRegister;

    private static  final String DIANAH=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btRegister=(Button)findViewById(R.id.btRegister);
        btLoad= (Button) findViewById(R.id.btLoad);

        btRegister.setOnClickListener(this);
        btLoad.setOnClickListener(this);
    }
    @Override
    public  void onClick(View v) {
        // to store data in the internal phone storage
        switch (v.getId()) {
            case R.id.btLoad:
                try {
                    FileInputStream fileInputStream=openFileInput("myData.txt");
                    int read=-1;
                    StringBuffer buffer=new StringBuffer();
                    while ((read=fileInputStream.read())!=-1){
                        buffer.append((char)read);
                    }
                    String user=buffer.substring(0,buffer.indexOf(" "));
                    String pass=buffer.substring(buffer.indexOf(" ")+1);

                    etUsername.setText(user);
                    etPassword.setText(pass);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(this,"Loaded succefully",Toast.LENGTH_LONG).show();

                break;
            case R.id.btRegister:
                startActivity(new Intent(this, Login.class));
                break;

        //shared preferences to store data
       /* switch(v.getId()){
            case R.id.btLoad:
                SharedPreferences sharedPreferences=getSharedPreferences("myData", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","DIANAH");
                String password=sharedPreferences.getString("password","DIANAH");

                if(username.equals(DIANAH) ||password.equals(DIANAH)){
                    Toast.makeText(this,"NULL POINTER",Toast.LENGTH_LONG).show();
                }else {
                    etUsername.setText(username);
                    etPassword.setText(password);
                    Toast.makeText(this,"Loaded succefully",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btRegister:
                startActivity(new Intent(this,Login.class ));
                break;
        }*/
        }
    }
}
