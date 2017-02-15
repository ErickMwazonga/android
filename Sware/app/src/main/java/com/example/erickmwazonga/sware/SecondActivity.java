package com.example.erickmwazonga.sware;

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
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    public  static final String DEFAULT="N/A";
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextUsername=(EditText) findViewById(R.id.editTextUsername);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);

    }
    public void load(View view){
        try {
            FileInputStream fileInputStream=openFileInput("myData.txt");
            int read=-1;
            StringBuffer buffer=new StringBuffer();
            while((read= fileInputStream.read())!=-1){
                buffer.append((char)read);
            }

            Log.d("myData",buffer.toString());

            String user =buffer.substring(0,buffer.indexOf(" "));
            String pass=buffer.substring(buffer.indexOf(" ")+1);

            editTextUsername.setText(user);
            editTextPassword.setText(pass);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"Saved successfully in ", Toast.LENGTH_SHORT).show();


       /* SharedPreferences sharedPreferences=getSharedPreferences("myData", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("name", DEFAULT);
        String password=sharedPreferences.getString("password",DEFAULT);

        if(name.equals(DEFAULT) || (password.equals(DEFAULT))){
            Toast.makeText(this,"Data was not found",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Data Loaded succ essfully",Toast.LENGTH_SHORT).show();
            editTextUsername.setText(name);
            editTextPassword.setText(password);
        }*/


    }
    public void back(View view){
        Toast.makeText(this,"Next",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

}
