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


public class AddExercise extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_exercise);

        name = (EditText) findViewById(R.id.exerciseName);
        description = (EditText) findViewById(R.id.exerciseDescription);
        date = (TextView) findViewById(R.id.exerciseDate);
        time = (TextView) findViewById(R.id.exerciseTime);

        getSupportActionBar().setTitle("Add Excercise");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Timepicker operations
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
    }

    public void addExercise(View view) {
        Intent intent = new Intent(this, Schedule.class);
        intent.putExtra("Exercises", 0);
        startActivity(intent);
    }

    public void cancelExercise(View view) {
        startActivity(new Intent(this, MainMenu.class));
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
            TextView textview = (TextView) getActivity().findViewById(R.id.exerciseDate);
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
            TextView textview = (TextView) getActivity().findViewById(R.id.exerciseTime);

            textview.setText(hourOfDay + ":" + minute);
        }
    }

    public void saveExercise(View view) {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name is a required field", Toast.LENGTH_SHORT).show();
        } else {
            String exerciseName = name.getText().toString();
            String exerciseDescription = description.getText().toString();
            String exerciseDate = date.getText().toString();
            String exerciseTime = time.getText().toString();

            studentDBHelper = new StudentDBHelper(context);
            sqLiteDatabase = studentDBHelper.getWritableDatabase();
            studentDBHelper.addExercise(exerciseName, exerciseDescription, exerciseDate, exerciseTime, sqLiteDatabase);
            Toast.makeText(getApplicationContext(), "Exercise added successfully", Toast.LENGTH_SHORT).show();
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
                .setContentText("Submit Date :" + date.getText().toString() + "   Submit Time :" + time.getText().toString() + "       Have you done your Exercise?")
                .setSmallIcon(R.drawable.sexcercise);

        Intent moreInfoIntent = new Intent(this, Schedule.class);
        moreInfoIntent.putExtra("Exercises", 0);
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
