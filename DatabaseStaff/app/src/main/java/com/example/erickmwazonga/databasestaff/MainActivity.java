package com.example.erickmwazonga.databasestaff;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUsername;
    Button buttonInternalCache, buttonExternalCache, buttonExternalPrivate, buttonExternalPublic, buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        buttonInternalCache = (Button) findViewById(R.id.buttonInternalCache);
        buttonExternalCache = (Button) findViewById(R.id.buttonExternalCache);
        buttonExternalPrivate = (Button) findViewById(R.id.buttonExternalPrivate);
        buttonExternalPublic = (Button) findViewById(R.id.buttonExternalPublic);
        buttonNext = (Button) findViewById(R.id.buttonNext);

        buttonExternalPrivate.setOnClickListener(this);
        buttonExternalCache.setOnClickListener(this);
        buttonExternalPrivate.setOnClickListener(this);
        buttonExternalPublic.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonInternalCache: {
                String data = editTextUsername.getText().toString();
                File folder = getCacheDir();
                File myFile = new File(folder, "myData1.txt");
                writeData(myFile, data);
            }
            break;
            case R.id.buttonExternalCache: {
                String data = editTextUsername.getText().toString();
                File folder = getExternalCacheDir();
                File myFile = new File(folder, "myData2.txt");
                writeData(myFile, data);
            }
            break;
            case R.id.buttonExternalPrivate: {
                String data = editTextUsername.getText().toString();
                File folder = getExternalFilesDir("ericko");
                File myFile = new File(folder, "myData3.txt");
                writeData(myFile, data);
            }
            break;
            case R.id.buttonExternalPublic: {
                String data = editTextUsername.getText().toString();
                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File myFile = new File(folder, "myData4.txt");
                writeData(myFile, data);
            }
            break;
            case R.id.buttonNext:
                startActivity(new Intent(this, NextActivity.class));
                break;
        }
    }

    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream1 = null;
        try {
            fileOutputStream1 = new FileOutputStream(myFile);
            fileOutputStream1.write(data.getBytes());

            Toast.makeText(this, data + " was written successfully " + myFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream1 != null) {
                try {
                    fileOutputStream1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
