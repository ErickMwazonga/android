package com.example.erickmwazonga.easyassetmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RecordMaintenance extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    //Tags used in the JSON String
    public static final String TAG_SERIAL = "serial_no";
    public static final String TAG_NAME = "name";
    //An ArrayList for Spinner Items
    private ArrayList<String> items;

    //JSON Array
    private JSONArray result;

    //JSON array name
    public static final String JSON_ARRAY = "result";

    EditText name, years_used, reason, maintainer,cost;
    TextView toDate,fromDate;
    Spinner serial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_maintenance);

        serial= (Spinner) findViewById(R.id.maintainerSerial);
        name= (EditText) findViewById(R.id.maintainerName);
        years_used= (EditText) findViewById(R.id.maintainerYears);
        reason= (EditText) findViewById(R.id.maintainerReason);
        maintainer= (EditText) findViewById(R.id.maintainerMaintainer);
        cost= (EditText) findViewById(R.id.maintainerCost);
        toDate= (TextView) findViewById(R.id.maintenanceToDate);
        fromDate= (TextView) findViewById(R.id.maintenanceFromDate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Record Maintenance");

        serial.setOnItemSelectedListener(this);
        //Initializing the ArrayList
        items = new ArrayList<String>();
        getData();

        //datepicker operations
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment1();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
    }

    public void saveMaintenance(View view) {
        if((name.getText().toString().trim().isEmpty()) && (serial.getSelectedItem().toString().trim().isEmpty())
                && (reason.getText().toString().trim().isEmpty()) && (maintainer.getText().toString().trim().isEmpty())){
            Toast.makeText(this,"Please fill these fields \n[Name, Serial,Description,Date Received]",Toast.LENGTH_LONG).show();
        }else{
            maintenanceitemDb();
        }
    }

    public void maintenanceCancel(View view) {
        startActivity(new Intent(this, Main_Menu.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        name.setText(getName(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        name.setText("");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            TextView textview = (TextView) getActivity().findViewById(R.id.maintenanceToDate);
            textview.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
    public static class DatePickerFragment1 extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            TextView textview = (TextView) getActivity().findViewById(R.id.maintenanceFromDate);
            textview.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void maintenanceitemDb() {
        final String mserial = serial.getSelectedItem().toString();
        final String mname = name.getText().toString();
        final String myears = years_used.getText().toString();
        final String mreason = reason.getText().toString();
        final String mmaintainer = maintainer.getText().toString();
        final String mcost = cost.getText().toString();
        final String mtoDate = toDate.getText().toString();
        final String mfromDate = fromDate.getText().toString();


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Toast.makeText(getApplicationContext(),"Data inserted successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
//                        Intent intent = new Intent(AddItem.this, Main_Menu.class);
//                        AddItem.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RecordMaintenance.this);
                        builder.setMessage("Recording maintenance an Item Failed \nThe serial number has already been captured")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest(mserial, mname, myears, mreason, mmaintainer, mcost,
                mtoDate, mfromDate, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RecordMaintenance.this);
        queue.add(maintenanceRequest);
    }

    class MaintenanceRequest extends StringRequest {

        private Map<String, String> params;

        public MaintenanceRequest(String serial, String name, String years, String reason, String maintainer,
                            String cost, String toDate, String fromDate, Response.Listener<String> listener) {
            super(Method.POST, Config.MAINTENANCE_REQUEST_URL, listener, null);

            params = new HashMap<>();
            params.put("serial", serial);
            params.put("name", name);
            params.put("years", years);
            params.put("reason", reason);
            params.put("maintainer", maintainer);
            params.put("cost", cost);
            params.put("toDate", toDate);
            params.put("fromDate", fromDate);
        }

        public Map<String, String> getParams() {
            return params;
        }
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Config.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getItems(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getItems(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                items.add(json.getString(TAG_SERIAL));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        serial.setAdapter(new ArrayAdapter<String>(RecordMaintenance.this, android.R.layout.simple_spinner_dropdown_item, items));
    }
    private String getName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            name = json.getString(TAG_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
}
