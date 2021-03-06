package com.example.erickmwazonga.assetdatabase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Addinfo extends AppCompatActivity {

    EditText Name, Email, Mobile;
    String name, email, mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);

        Name = (EditText) findViewById(R.id.etName);
        Email = (EditText) findViewById(R.id.etEmail);
        Mobile = (EditText) findViewById(R.id.etMobile);
    }

    public void saveInfo(View view) {
        name = Name.getText().toString();
        email = Email.getText().toString();
        mobile = Mobile.getText().toString();

       // String method="register";

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(name, email, mobile);
        finish();
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {

        String add_info_url;

        @Override
        protected void onPreExecute() {
            //add_info_url="http://erickmwazonga.hostingsiteforfree.com/add_info.php";
            add_info_url = "http://169.254.18.86/Android/local/add_info.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String name, email, mobile;
            name = args[0];
            email = args[1];
            mobile = args[2];

            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string =
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                //httpURLConnection.disconnect();

                return "One row of data inserted";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Data couldnt be inserted";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
