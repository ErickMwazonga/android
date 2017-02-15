package com.example.erickmwazonga.easyassetmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class IssueItem extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    //Tags used in the JSON String
    public static final String TAG_SERIAL = "serial_no";
    public static final String TAG_NAME = "name";
    //An ArrayList for Spinner Items
    private ArrayList<String> items;

    //JSON Array
    private JSONArray result;

    //JSON array name
    public static final String JSON_ARRAY = "result";

    EditText name, user, location, quantity;
    TextView issueDate, issueReturnDate;
    Spinner department,serial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_item);

        serial = (Spinner) findViewById(R.id.issueSerial);
        name = (EditText) findViewById(R.id.issueName);
        user = (EditText) findViewById(R.id.issueUser);
        department = (Spinner) findViewById(R.id.spinnerIssueDepartment);
        location = (EditText) findViewById(R.id.issueLocation);
        quantity = (EditText) findViewById(R.id.issueQuantity);
        issueDate = (TextView) findViewById(R.id.issueDate);
        issueReturnDate = (TextView) findViewById(R.id.issueReturningDate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Issue an Item");

        //spinner operations
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinnerDepartments, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter2);

        serial.setOnItemSelectedListener(this);
        //Initializing the ArrayList
        items = new ArrayList<String>();
        getData();


        //datepicker operations
        issueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
        issueReturnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment1();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
    }

    public void issueCancel(View view) {
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
            TextView textview = (TextView) getActivity().findViewById(R.id.issueDate);
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
            TextView textview = (TextView) getActivity().findViewById(R.id.issueReturningDate);
            textview.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void issueSave(View view) {
        if((name.getText().toString().trim().isEmpty()) && (serial.getSelectedItem().toString().trim().isEmpty())
                && (user.getText().toString().trim().isEmpty()) && (issueDate.getText().toString().trim().isEmpty())){
            Toast.makeText(this,"Please fill these fields \n[Name, Serial,User,Date Issued]",Toast.LENGTH_LONG).show();
        }else{
            issueitemDb();
        }
    }

    public void issueitemDb() {
        final String iserial = serial.getSelectedItem().toString();
        final String iname = name.getText().toString();
        final String iuser = user.getText().toString();
        final String idepartment = department.getSelectedItem().toString();
        final String ilocation = location.getText().toString();
        final String iquantity = quantity.getText().toString();
        final String idateIssued = issueDate.getText().toString();
        final String idateReturned = issueReturnDate.getText().toString();


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
                        AlertDialog.Builder builder = new AlertDialog.Builder(IssueItem.this);
                        builder.setMessage("Issuing an Item Failed \nThe serial number has already been captured")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        IssueRequest issueRequest = new IssueRequest(iserial, iname, iuser, idepartment, ilocation, iquantity,
                idateIssued, idateReturned, responseListener);
        RequestQueue queue = Volley.newRequestQueue(IssueItem.this);
        queue.add(issueRequest);
    }

    class IssueRequest extends StringRequest {

        private Map<String, String> params;

        public IssueRequest(String serial, String name, String user, String department, String location,
                            String quantity, String dateIssued, String dateReturned, Response.Listener<String> listener) {
            super(Method.POST, Config.ISSUE_REQUEST_URL, listener, null);

            params = new HashMap<>();
            params.put("serial", serial);
            params.put("name", name);
            params.put("user", user);
            params.put("department", department);
            params.put("location", location);
            params.put("quantity", quantity);
            params.put("dateIssued", dateIssued);
            params.put("dateReturned", dateReturned);
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
        serial.setAdapter(new ArrayAdapter<String>(IssueItem.this, android.R.layout.simple_spinner_dropdown_item, items));
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
