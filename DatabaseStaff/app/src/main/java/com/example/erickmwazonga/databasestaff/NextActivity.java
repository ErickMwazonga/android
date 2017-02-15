package com.example.erickmwazonga.databasestaff;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NextActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextLUsername;
    Button buttonLInternalCache,buttonLExternalCache,buttonLExternalPrivate,buttonLExternalPublic,buttonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        editTextLUsername= (EditText) findViewById(R.id.editTextLUsername);
        buttonLInternalCache= (Button) findViewById(R.id.buttonLInternalCache);
        buttonLExternalCache= (Button) findViewById(R.id.buttonLExternalCache);
        buttonLExternalPrivate= (Button) findViewById(R.id.buttonLExternalPrivate);
        buttonLExternalPublic= (Button) findViewById(R.id.buttonLExternalPublic);
        buttonPrevious= (Button) findViewById(R.id.buttonPrevius);

        buttonLExternalPrivate.setOnClickListener(this);
        buttonLExternalCache.setOnClickListener(this);
        buttonLExternalPrivate.setOnClickListener(this);
        buttonLExternalPublic.setOnClickListener(this);
        buttonPrevious.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLInternalCache:
            {
                File folder=getCacheDir();
                File myFile=new File(folder,"myData1.txt");
                String data=readData(myFile);
                checkNull(data);
            }
                break;
            case R.id.buttonLExternalCache:
            {
                File folder=getExternalCacheDir();
                File myFile=new File(folder,"myData2.txt");
                String data=readData(myFile);
                checkNull(data);
            }
                break;
            case R.id.buttonLExternalPrivate:
            {
                File folder=getExternalFilesDir("ericko");
                File myFile=new File(folder,"myData3.txt");
                String data=readData(myFile);
                checkNull(data);
            }
                break;
            case R.id.buttonLExternalPublic:
            {
                File folder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File myFile=new File(folder,"myData4.txt");
                String data=readData(myFile);
                checkNull(data);
            }
                break;
            case R.id.buttonPrevius:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
    private void checkNull(String data){
        if(data!=null){
            editTextLUsername.setText(data);
        }else{
            editTextLUsername.setText("No data was found");
        }
    }
    private String readData(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            int read = -1;
            fileInputStream = new FileInputStream(myFile);
            StringBuffer buffer = new StringBuffer();
            while ((read = fileInputStream.read()) != -1) {
                buffer.append((char)read);//23209302 ABC
            }
            return buffer.toString();

            //Toast.makeText(this, data + " was written successfully " + myFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
