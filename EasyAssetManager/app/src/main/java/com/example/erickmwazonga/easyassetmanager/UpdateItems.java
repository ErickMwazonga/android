package com.example.erickmwazonga.easyassetmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class UpdateItems extends AppCompatActivity implements  Spinner.OnItemSelectedListener {

    //Tags used in the JSON String
    public static final String TAG_SERIAL = "serial_no";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_ADD_DATE = "add_date";
    public static final String TAG_QUANTITY = "quantity";
    public static final String TAG_VENDOR = "vendor";
    public static final String TAG_WARRANTY = "warranty";
    public static final String TAG_EXPIRY_DATE = "expiry_date";


    //An ArrayList for Spinner Items
    private ArrayList<String> items;

    //JSON Array
    private JSONArray result;

    //JSON array name
    public static final String JSON_ARRAY = "result";

    EditText name, description, quantity, vendor, warranty;
    Spinner serial,department, category, status;
    TextView addDate, expiryDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update or Delete an Item");

        serial = (Spinner) findViewById(R.id.addSerial);
        name = (EditText) findViewById(R.id.addName);
        description = (EditText) findViewById(R.id.addDescription);
        department = (Spinner) findViewById(R.id.spinnerAddDepartment);
        addDate = (TextView) findViewById(R.id.addDate);
        quantity = (EditText) findViewById(R.id.addQunatity);
        category = (Spinner) findViewById(R.id.spinnerAddCategory);
        status = (Spinner) findViewById(R.id.spinnerAddStatus);
        vendor = (EditText) findViewById(R.id.addVendor);
        warranty = (EditText) findViewById(R.id.addWarranty);
        expiryDate = (TextView) findViewById(R.id.addExpiryDate);


        serial.setOnItemSelectedListener(this);
        //Initializing the ArrayList
        items = new ArrayList<String>();
        getData();


        //spinner operations
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerAddStatus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.spinnerAddCategory, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinnerDepartments, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter2);

        //datepicker operations
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
        //datepicker operations
        expiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment1();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });

    }

    public void delete(View view) {
        confirmDeleteItem();
    }

    public void update(View view) {
        updateItem();
    }


    private void updateItem(){
        final String userial = serial.getSelectedItem().toString().trim();
        final String uname = name.getText().toString().trim();
        final String udescription = description.getText().toString().trim();
        final String udepartment = department.getSelectedItem().toString().trim();
        final String udate = addDate.getText().toString().trim();
        final String uquantity = quantity.getText().toString().trim();
        final String ucategory = category.getSelectedItem().toString().trim();
        final String ustatus = status.getSelectedItem().toString().trim();
        final String uvendor = vendor.getText().toString().trim();
        final String uwarranty = warranty.getText().toString().trim();
        final String uexpiry = expiryDate.getText().toString().trim();

        class UpdateItem extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateItems.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpdateItems.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("serial_no", userial);
                hashMap.put("name", uname);
                hashMap.put("description", udescription);
                hashMap.put("department", udepartment);
                hashMap.put("add_date", udate);
                hashMap.put("quantity", uquantity);
                hashMap.put("category", ucategory);
                hashMap.put("status", ustatus);
                hashMap.put("vendor", uvendor);
                hashMap.put("warranty", uwarranty);
                hashMap.put("expiry_date", uexpiry);


                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_ITEM,hashMap);

                return s;
            }
        }

        UpdateItem ue = new UpdateItem();
        ue.execute();
    }


    private void deleteItem(){
        class DeleteItem extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateItems.this, "Deleting...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpdateItems.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_ITEM, "serial_no");
                return s;
            }
        }

        DeleteItem de = new DeleteItem();
        de.execute();
    }

    private void confirmDeleteItem(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this item?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteItem();
                        startActivity(new Intent(UpdateItems.this,Main_Menu.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        name.setText(getName(position));
        description.setText(getDescription(position));
        addDate.setText(addDate(position));
        quantity.setText(getQuantity(position));
        vendor.setText(getVendor(position));
        warranty.setText(getWarranty(position));
        expiryDate.setText(getExpiry(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        name.setText("");
        description.setText("");
        addDate.setText("");
        quantity.setText("");
        vendor.setText("");
        warranty.setText("");
        expiryDate.setText("");
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
            TextView textview = (TextView) getActivity().findViewById(R.id.addDate);
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
            TextView textview = (TextView) getActivity().findViewById(R.id.addExpiryDate);
            textview.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Config.DATA_ALL_URL,
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
        serial.setAdapter(new ArrayAdapter<String>(UpdateItems.this, android.R.layout.simple_spinner_dropdown_item, items));
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
    private String getDescription(int position){
        String description="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            description = json.getString(TAG_DESCRIPTION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return description;
    }

    private String getQuantity(int position){
        String quantity="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            quantity = json.getString(TAG_QUANTITY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return quantity;
    }
    private String getWarranty(int position){
        String warranty="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            warranty = json.getString(TAG_WARRANTY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return warranty;
    }
    private String addDate(int position){
        String add="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            add = json.getString(TAG_ADD_DATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return add;
    }
    private String getExpiry(int position){
        String expiry="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            expiry = json.getString(TAG_EXPIRY_DATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return expiry;
    }

    private String getVendor(int position){
        String vendor="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            vendor = json.getString(TAG_VENDOR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return vendor;
    }

}