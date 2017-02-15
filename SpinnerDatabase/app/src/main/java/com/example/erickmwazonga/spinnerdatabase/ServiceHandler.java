package com.example.erickmwazonga.spinnerdatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * Created by Erick Mwazonga on 8/28/2016.
 */
public class ServiceHandler {


    static InputStream is = null;
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public ServiceHandler() {
    }

    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method);
    }

    public String makeServiceCall(String url, int method, List<NameValuePair> params) {
        //try {
            // http client
            DefaultHttpClient httpClient;=new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
        //}
    }
}