package com.example.erickmwazonga.studentcompanion;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddHoliday extends AppCompatActivity {
    NotificationManager notificationManager;
    int notifID = 33;

    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    StudentDBHelper studentDBHelper;

    EditText name;
    TextView start, end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holiday);

        name = (EditText) findViewById(R.id.holidayName);
        start = (TextView) findViewById(R.id.holidayStartDate);
        end = (TextView) findViewById(R.id.holidayEndDate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Holiday");

        //datepicker operations
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DatePickerFragment1();
                dialogFragment.show(getFragmentManager(), "Date Picker Dialog");
            }
        });
    }

    public void addHoliday(View view) {
        Intent intent = new Intent(this, Schedule.class);
        intent.putExtra("Holidays", 4);
        startActivity(intent);
    }

    public void cancelHoliday(View view) {
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
            TextView textview = (TextView) getActivity().findViewById(R.id.holidayStartDate);
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
            TextView textview1 = (TextView) getActivity().findViewById(R.id.holidayEndDate);
            textview1.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void saveHoliday(View view) {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name is a required field", Toast.LENGTH_SHORT).show();
        } else {
            String holidayName = name.getText().toString();
            String holidayStart = start.getText().toString();
            String holidayEnd = end.getText().toString();

            studentDBHelper = new StudentDBHelper(context);
            sqLiteDatabase = studentDBHelper.getWritableDatabase();
            studentDBHelper.addHoliday(holidayName, holidayStart, holidayEnd, sqLiteDatabase);
            Toast.makeText(getApplicationContext(), "Holiday added successfully", Toast.LENGTH_SHORT).show();
            studentDBHelper.close();

            Intent intent = new Intent(this, Schedule.class);
            intent.putExtra("Holidays", 4);
            startActivity(intent);

            showNotification();
        }
    }

    public void showNotification() {
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this)
                .setContentTitle(name.getText().toString())
                .setContentText("Start Date :" + start.getText().toString() + "        End Date :" + end.getText().toString() + "      Enjoy Your Holiday")
                .setTicker("Student Companion | Holiday")
                .setSmallIcon(R.drawable.sholiday);

        Intent moreInfoIntent = new Intent(this, Schedule.class);
        moreInfoIntent.putExtra("Holidays", 4);
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
