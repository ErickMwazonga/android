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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {
    NotificationManager notificationManager;
    int notifID = 33;

    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    StudentDBHelper studentDBHelper;

    EditText name, description;
    TextView date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        name = (EditText) findViewById(R.id.taskName);
        description = (EditText) findViewById(R.id.taskDescription);
        date = (TextView) findViewById(R.id.taskDate);
        time = (TextView) findViewById(R.id.taskTime);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Task");

        //Timepicker operations
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        //datepicker operations
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
    }

    public void addTask(View view) {
        Intent intent = new Intent(this, Schedule.class);
        intent.putExtra("Tasks", 1);
        startActivity(intent);
    }

    public void cancelTask(View view) {
        startActivity(new Intent(this, MainMenu.class));
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
            TextView textview = (TextView) getActivity().findViewById(R.id.taskTime);

            textview.setText(hourOfDay + ":" + minute);
        }
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
            TextView textview = (TextView) getActivity().findViewById(R.id.taskDate);
            textview.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void saveTask(View view) {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name is a required field", Toast.LENGTH_SHORT).show();
        } else {
            String taskName = name.getText().toString();
            String taskDescription = description.getText().toString();
            String taskDate = date.getText().toString();
            String taskTime = time.getText().toString();

            studentDBHelper = new StudentDBHelper(context);
            sqLiteDatabase = studentDBHelper.getWritableDatabase();
            studentDBHelper.addTask(taskName, taskDescription, taskDate, taskTime, sqLiteDatabase);
            Toast.makeText(getApplicationContext(), "Task added successfully", Toast.LENGTH_SHORT).show();
            studentDBHelper.close();

            Intent intent = new Intent(this, Schedule.class);
            intent.putExtra("Exercises", 0);
            startActivity(intent);

            showNotification();
        }
    }

    public void showNotification() {
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this)
                .setContentTitle(name.getText().toString())
                .setContentText("Date :" + date.getText().toString() + "   Time :" + time.getText().toString() + "     Have you accomplished your tasks?")
                .setTicker("Student Companion | Tasks")
                .setSmallIcon(R.drawable.stasks);

        Intent moreInfoIntent = new Intent(this, Schedule.class);
        moreInfoIntent.putExtra("Tasks", 1);
        startActivity(moreInfoIntent);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(Schedule.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifID, builder.build());
    }
}
