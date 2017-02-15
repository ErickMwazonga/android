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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity {

    EditText serial, name, description, quantity, vendor, warranty;
    Spinner department, category, status;
    TextView addDate, expiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        serial = (EditText) findViewById(R.id.addSerial);
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Item");

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

    public void addSave(View view) {
        if((name.getText().toString().trim().isEmpty()) && (serial.getText().toString().trim().isEmpty())
                && (description.getText().toString().trim().isEmpty()) && (quantity.getText().toString().trim().isEmpty())){
            Toast.makeText(this,"Please fill these fields \n[Name, Serial,Description,Quantity]",Toast.LENGTH_LONG).show();
        }else{
            additemDb();
        }
    }

    public void borrowCancel(View view) {
        startActivity(new Intent(this, Main_Menu.class));
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

    public void additemDb() {
        final String iserial = serial.getText().toString();
        final String iname = name.getText().toString();
        final String idescription = description.getText().toString();
        final String idepartment = department.getSelectedItem().toString();
        final String idate = addDate.getText().toString();
        final String iquantity = quantity.getText().toString();
        final String icategory = category.getSelectedItem().toString();
        final String istatus = status.getSelectedItem().toString();
        final String ivendor = vendor.getText().toString();
        final String iwarranty = warranty.getText().toString();
        final String iexpiry = expiryDate.getText().toString();


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
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddItem.this);
                        builder.setMessage("Adding an Item Failed \nThe serial number has already been captured")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        AddRequest addRequest = new AddRequest(iserial, iname, idescription, idepartment, idate,
                iquantity, icategory, istatus, ivendor, iwarranty, iexpiry, responseListener);
        RequestQueue queue = Volley.newRequestQueue(AddItem.this);
        queue.add(addRequest);
    }

    class AddRequest extends StringRequest {
        private Map<String, String> params;

        public AddRequest(String serial, String name, String description, String department, String date,
                          String quantity, String category, String status, String vendor, String warranty,
                          String expiry, Response.Listener<String> listener) {
            super(Method.POST, Config.ADD_REQUEST_URL, listener, null);

            params = new HashMap<>();
            params.put("serial", serial);
            params.put("name", name);
            params.put("description", description);
            params.put("department", department);
            params.put("date", date);
            params.put("quantity", quantity);
            params.put("category", category);
            params.put("status", status);
            params.put("vendor", vendor);
            params.put("warranty", warranty);
            params.put("expiry", expiry);
        }

        public Map<String, String> getParams() {
            return params;
        }
    }
}
