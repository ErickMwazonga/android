package com.example.erickmwazonga.easyassetmanager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ViewList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    String[] titles;
    String json_string, json_borrowed, json_received, json_returned, json_issued, json_deleted, json_maintained;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("View Items");

        Resources res = getResources();
        titles = res.getStringArray(R.array.viewItemsList);

        listView = (ListView) findViewById(R.id.listViewItemsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

//        new BackgroundTask().execute();
//        new BackgroundTaskBorrowed().execute();
//        new BackgroundTaskDeleted().execute();
//        new BackgroundTaskIssued().execute();
//        new BackgroundTaskReceived().execute();
//        new BackgroundTaskMaintained().execute();
//        new BackgroundTaskReturned().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                if (json_string == null) {
                    new BackgroundTask().execute();
                } else {
                    //new BackgroundTask().execute();
                    Intent intent = new Intent(this, ViewAvailableItems.class);
                    intent.putExtra("json_data", json_string);
                    startActivity(intent);
                }
                break;
            case 1:
                if (json_borrowed == null) {
                    new BackgroundTaskBorrowed().execute();
                } else {
                    //new BackgroundTask().execute();
                    Intent intent = new Intent(this, ViewBorrowedItems.class);
                    intent.putExtra("json_borrowed", json_borrowed);
                    startActivity(intent);
                }
                break;
            case 2:
                if (json_received == null) {
                    new BackgroundTaskReceived().execute();
                } else {
                    //new BackgroundTask().execute();
                    Intent intent = new Intent(this, ViewReceivedItems.class);
                    intent.putExtra("json_received", json_received);
                    startActivity(intent);
                }

//                new BackgroundTaskReceived().execute();
//                //startActivity(new Intent(this, ViewReceivedItems.class));
//
//                if (json_received == null) {
//                    Toast.makeText(getApplicationContext(), "First Get json", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(this, ViewReceivedItems.class);
//                    intent.putExtra("json_received", json_received);
//                    startActivity(intent);
//                }
                break;
            case 3:
                if (json_issued == null) {
                    new BackgroundTaskIssued().execute();
                } else {
                    //new BackgroundTask().execute();
                    Intent intent = new Intent(this, ViewIssuedItems.class);
                    intent.putExtra("json_issued", json_issued);
                    startActivity(intent);
                }
                break;
            case 4:
//                if (json_maintained == null) {
//                    new BackgroundTaskMaintained().execute();
//                } else {
//                    //new BackgroundTask().execute();
//                    Intent intent = new Intent(this, ViewMaintainedItems.class);
//                    intent.putExtra("json_maintained", json_maintained);
//                    startActivity(intent);
//                }



//                new BackgroundTaskMaintained().execute();
//                //startActivity(new Intent(this, ViewReceivedItems.class));
//
//                if (json_maintained == null) {
//                    Toast.makeText(getApplicationContext(), "First Get json", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(this, ViewMaintainedItems.class);
//                    intent.putExtra("json_maintained", json_maintained);
//                    startActivity(intent);
//                }
                break;
            case 5:
                if (json_deleted == null) {
                    new BackgroundTaskDeleted().execute();
                } else {
                    //new BackgroundTask().execute();
                    Intent intent = new Intent(this, ViewDeletedItems.class);
                    intent.putExtra("json_deleted", json_deleted);
                    startActivity(intent);
                }
                break;
            default:
                //-------//
        }
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.GET_ITEMS_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_string = result;

            Intent intent = new Intent(getApplicationContext(), ViewAvailableItems.class);
            intent.putExtra("json_data", json_string);
            startActivity(intent);

            super.onPostExecute(json_string);
        }
    }

    class BackgroundTaskBorrowed extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.GET_BORROWED_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_borrowed = result;
            Intent intent = new Intent(getApplicationContext(), ViewBorrowedItems.class);
            intent.putExtra("json_borrowed", json_borrowed);
            startActivity(intent);

            super.onPostExecute(json_borrowed);
        }
    }

    class BackgroundTaskIssued extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.GET_ISSUED_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
//            json_issued = result;
//            Intent intent = new Intent();
//            intent.putExtra("json_issued", json_issued);

            json_issued = result;
            Intent intent = new Intent(getApplicationContext(), ViewIssuedItems.class);
            intent.putExtra("json_issued", json_issued);
            startActivity(intent);

            super.onPostExecute(json_issued);
        }
    }

    class BackgroundTaskReceived extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.GET_RECEIVED_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
//            json_received = result;
//            Intent intent = new Intent();
//            intent.putExtra("json_received", json_received);

            json_received = result;
            Intent intent = new Intent(getApplicationContext(), ViewReceivedItems.class);
            intent.putExtra("json_received", json_received);
            startActivity(intent);

            super.onPostExecute(json_received);
        }
    }

    class BackgroundTaskMaintained extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.GET_MAINTAINED_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
//            json_maintained = result;
//            Intent intent = new Intent();
//            intent.putExtra("json_maintained", json_maintained);

            json_maintained = result;
            Intent intent = new Intent(getApplicationContext(), ViewMaintainedItems.class);
            intent.putExtra("json_maintained", json_maintained);
            startActivity(intent);

            super.onPostExecute(json_maintained);
        }
    }

    class BackgroundTaskReturned extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.GET_RETURNED_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
//            json_returned = result;
//            Intent intent = new Intent();
//            intent.putExtra("json_returned", json_returned);

            json_returned = result;
            Intent intent = new Intent(getApplicationContext(), ViewReturnedItems.class);
            intent.putExtra("json_returned", json_returned);
            startActivity(intent);

            super.onPostExecute(json_returned);
        }
    }

    class BackgroundTaskDeleted extends AsyncTask<Void, Void, String> {
        String json_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            json_url = Config.GET_DELETED_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
//            json_deleted = result;
//            Intent intent = new Intent();
//            intent.putExtra("json_deleted", json_deleted);

            json_deleted = result;
            Intent intent = new Intent(getApplicationContext(), ViewDeletedItems.class);
            intent.putExtra("json_deleted", json_deleted);
            startActivity(intent);

            super.onPostExecute(json_deleted);
        }
    }
}