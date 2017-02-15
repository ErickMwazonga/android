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

public class BorrowItem extends AppCompatActivity {
    EditText serial, name, owner, description, quantity;
    Spinner department;
    TextView borrowedDate,returnedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_item);

        serial= (EditText) findViewById(R.id.borrowSerial);
        name= (EditText) findViewById(R.id.borrowName);
        owner= (EditText) findViewById(R.id.borrowOwner);
        department= (Spinner) findViewById(R.id.spinnerBorrowDepartment);
        description= (EditText) findViewById(R.id.borrowDescription);
        quantity= (EditText) findViewById(R.id.borrowQuantity);
        borrowedDate= (TextView) findViewById(R.id.borrowBorrowedDate);
        returnedDate= (TextView) findViewById(R.id.borrowReturningDate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Borrow an Item");

        //spinner operations
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinnerDepartments, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter2);

        //datepicker operations
        borrowedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
        returnedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment1();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });

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
            TextView textview = (TextView) getActivity().findViewById(R.id.borrowBorrowedDate);
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
            TextView textview = (TextView) getActivity().findViewById(R.id.borrowReturningDate);
            textview.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
    public void borrowSave(View view) {
        if((name.getText().toString().trim().isEmpty()) && (serial.getText().toString().trim().isEmpty())
                && (description.getText().toString().trim().isEmpty()) && (returnedDate.getText().toString().trim().isEmpty())){
            Toast.makeText(this,"Please fill these fields \n[Name, Serial,Description,Date Returned]",Toast.LENGTH_LONG).show();
        }else{
            borrowitemDb();
        }
    }

    public void borrowitemDb() {
        final String bserial = serial.getText().toString();
        final String bname = name.getText().toString();
        final String bowner = owner.getText().toString();
        final String bdepartment = department.getSelectedItem().toString();
        final String bdescription = description.getText().toString();
        final String bquantity = quantity.getText().toString();
        final String bdateBorrowed = borrowedDate.getText().toString();
        final String bdateReturned = returnedDate.getText().toString();


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
                        AlertDialog.Builder builder = new AlertDialog.Builder(BorrowItem.this);
                        builder.setMessage("Borrowing an Item Failed \nThe serial number has already been captured")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        BorrowRequest borrowRequest = new BorrowRequest(bserial, bname, bowner, bdepartment, bdescription, bquantity,
                bdateBorrowed,bdateReturned, responseListener);
        RequestQueue queue = Volley.newRequestQueue(BorrowItem.this);
        queue.add(borrowRequest);
    }

    class BorrowRequest extends StringRequest{

        private Map<String, String> params;

        public BorrowRequest(String serial, String name, String owner, String department,String description,
                             String quantity, String dateBorrowed, String dateReturned,Response.Listener<String> listener) {
            super(Method.POST, Config.BORROW_REQUEST_URL, listener, null);

            params = new HashMap<>();
            params.put("serial", serial);
            params.put("name", name);
            params.put("owner", owner);
            params.put("department", department);
            params.put("description", description);
            params.put("quantity", quantity);
            params.put("dateBorrowed", dateBorrowed);
            params.put("dateReturned", dateReturned);
        }
        public Map<String, String> getParams() {
            return params;
        }
    }
}


