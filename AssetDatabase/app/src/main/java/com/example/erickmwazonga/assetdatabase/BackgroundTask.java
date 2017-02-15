package com.example.erickmwazonga.assetdatabase;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Erick Mwazonga on 11/7/2016.
 */

public class BackgroundTask extends AsyncTask<String, Void, String>  {

    Context context;
    BackgroundTask(Context context){
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String method=params[0];
        if(method.equals("register")){

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
