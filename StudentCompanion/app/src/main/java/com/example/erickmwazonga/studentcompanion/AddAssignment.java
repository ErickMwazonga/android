package com.example.erickmwazonga.studentcompanion;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAssignment extends AppCompatActivity {
    NotificationManager notificationManager;
    int notifID=33;

    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    StudentDBHelper studentDBHelper;

    Spinner status;
    EditText name, subject, description;
    TextView date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        name = (EditText) findViewById(R.id.assignmentsName);
        subject = (EditText) findViewById(R.id.assignmentsSubject);
        description = (EditText) findViewById(R.id.assignmentsDescription);
        status = (Spinner) findViewById(R.id.assignmentSpinner);
        date = (TextView) findViewById(R.id.assignmentsDate);
        time = (TextView) findViewById(R.id.assignmnetSubmitTime);

        getSupportActionBar().setTitle("Add Assignment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });

        //Timepicker operations
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        //spinner operations
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerAssignment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);
    }

    public void addAssignment(View view) {
        Intent intent = new Intent(this, Schedule.class);
        intent.putExtra("Assignments", 2);
        startActivity(intent);
    }

    public void assignmentCancel(View view) {
        startActivity(new Intent(this,MainMenu.class));
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
            TextView textview = (TextView) getActivity().findViewById(R.id.assignmentsDate);
            textview.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            TextView textview = (TextView) getActivity().findViewById(R.id.assignmnetSubmitTime);
            textview.setText(hourOfDay + ":" + minute);
        }
    }

    public void saveAssignment(View view) {
        if(name.getText().toString().isEmpty()){
            Toast.makeText(this, "Name is a required field",Toast.LENGTH_SHORT).show();
        }else {
            String assignmentName = name.getText().toString();
            String assignmentSubject = subject.getText().toString();
            String assignmentDescription = description.getText().toString();
            String assignmentStatus = status.getSelectedItem().toString();
            String assignmentDate = date.getText().toString();
            String assignmentTime = time.getText().toString();

            studentDBHelper = new StudentDBHelper(context);
            sqLiteDatabase = studentDBHelper.getWritableDatabase();
            studentDBHelper.addAssignment(assignmentName, assignmentSubject, assignmentDescription, assignmentStatus, assignmentDate, assignmentTime, sqLiteDatabase);
            Toast.makeText(getApplicationContext(), "Assignment added successfully", Toast.LENGTH_SHORT).show();
            studentDBHelper.close();

            Intent intent = new Intent(this, Schedule.class);
            intent.putExtra("Assignments", 2);
            startActivity(intent);

            showNotification();
        }
    }


    public void showNotification() {
        NotificationCompat.Builder builder=new
                NotificationCompat.Builder(this)
                .setContentTitle(name.getText().toString())
                .setContentText("Date :"+date.getText().toString()+ "       Time :"+time.getText().toString()+"     Have you completed the Assignment?")
                .setTicker("Student Companion | Assignments")
                .setSmallIcon(R.drawable.sassignment);

        Intent moreInfoIntent = new Intent(this, Schedule.class);
        moreInfoIntent.putExtra("Assignments", 2);
        startActivity(moreInfoIntent);

        TaskStackBuilder taskStackBuilder=TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(Schedule.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);

        PendingIntent pendingIntent=taskStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifID,builder.build());
    }

}
