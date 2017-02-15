package com.example.erickmwazonga.assetdatabase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Erick Mwazonga on 11/7/2016.
 */

public class SaveInfo extends AppCompatActivity {

    EditText Name, Email, Mobile;
    String name, email, mobile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_addinfo);

        Name = (EditText) findViewById(R.id.etName);
        Email = (EditText) findViewById(R.id.etEmail);
        Mobile = (EditText) findViewById(R.id.etMobile);

        name=Name.getText().toString();
        email=Email.getText().toString();
        mobile=Mobile.getText().toString();

    }
    class BackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... args) {
            String name, email, mobile;
            name = args[0];
            email = args[1];
            mobile = args[2];

            return "success";
        }
    }


}
