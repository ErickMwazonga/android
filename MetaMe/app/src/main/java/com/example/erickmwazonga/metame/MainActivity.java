package com.example.erickmwazonga.metame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText  etUsername,etPassword;
    Button btsave,btLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btsave= (Button) findViewById(R.id.btSave);
        btLogout=(Button)findViewById(R.id.btLogout);

        btLogout.setOnClickListener(this);
        btsave.setOnClickListener(this);
    }
    @Override
    public  void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSave:
                String user=etUsername.getText().toString();
                String pass=etPassword.getText().toString();

                user=user+" ";
                FileOutputStream fileOutputStream=null;
                File file=null;
                try {
                    file=getFilesDir();
                    fileOutputStream=openFileOutput("myData.txt",Context.MODE_PRIVATE);
                    fileOutputStream.write(user.getBytes());
                    fileOutputStream.write(pass.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(this, "File "+file+" /myData.txt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btLogout:
                startActivity(new Intent(this, Register.class));
                break;
        }
        /*
        switch (v.getId()) {
            case R.id.btSave:
                SharedPreferences sharedPreferences=getSharedPreferences("myData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username",etUsername.getText().toString());
                editor.putString("password",etPassword.getText().toString());
                editor.commit();

                Toast.makeText(this,"Saved successfully",Toast.LENGTH_LONG).show();
                break;
            case R.id.btLogout:
                startActivity(new Intent(this, Register.class));
                break;
        }*/
    }
    /*
    public void showRegister( View view){
        String button_test;
        button_test =((Button) view).getText().toString();
        if (button_test.equals("LOGOUT"))
        {
            Intent intent1= new Intent(this,Register.class);
            startActivity(intent1 );
        }

    }*/
}
