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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    //Button buttonSave,buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername=(EditText) findViewById(R.id.editTextUsername);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);

    }

    public void save(View view){
        String user=editTextUsername.getText().toString();
        String pass=editTextPassword.getText().toString();

        File file=null;
        user=user+" ";
        FileOutputStream fileOutputStream = null;
        try {
            file = getFilesDir();
            fileOutputStream = openFileOutput("myData.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(user.getBytes());
            fileOutputStream.write(pass.getBytes());

        } catch (IOException e) {
            //e.printStackTrace();
            Log.d("myData", e.toString());
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this,"Saved successfully in "+file +" /myData.txt ", Toast.LENGTH_SHORT).show();

        /*SharedPreferences sharedPreferences=getSharedPreferences("myData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",editTextUsername.getText().toString());
        editor.putString("password",editTextPassword.getText().toString());
        editor.commit();
        */

        Toast.makeText(this,"Data save successfully",Toast.LENGTH_SHORT).show();
    }
    public void next(View view){
        Toast.makeText(this,"Next",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,SecondActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
