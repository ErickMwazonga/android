package com.example.erickmwazonga.studentcompanion;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddLesson extends AppCompatActivity {
    NotificationManager notificationManager;
    int notifID = 33;

    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    StudentDBHelper studentDBHelper;

    Spinner day;
    EditText name, code, teacher, venue;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        day = (Spinner) findViewById(R.id.lessonDay);
        name = (EditText) findViewById(R.id.lessonName);
        code = (EditText) findViewById(R.id.lessonCode);
        teacher = (EditText) findViewById(R.id.lessonTeacher);
        venue = (EditText) findViewById(R.id.lessonVenue);
        time = (TextView) findViewById(R.id.lessonTime);

        getSupportActionBar().setTitle("Add Lesson");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                R.array.spinnerLesson, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter);
    }

    public void addLesson(View view) {
        startActivity(new Intent(this, DayTimetable.class));
    }

    public void cancelLesson(View view) {
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
            TextView textview = (TextView) getActivity().findViewById(R.id.lessonTime);
            textview.setText(hourOfDay + ":" + minute);
        }
    }

    public void saveLesson(View view) {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name is a required field", Toast.LENGTH_SHORT).show();
        } else {
            String lessonDay = day.getSelectedItem().toString();
            String lessonName = name.getText().toString();
            String lessonCode = code.getText().toString();
            String lessonTeacher = teacher.getText().toString();
            String lessonVenue = venue.getText().toString();
            String lessonTime = time.getText().toString();

            studentDBHelper = new StudentDBHelper(context);
            sqLiteDatabase = studentDBHelper.getWritableDatabase();
            //studentDBHelper.addLesson(lessonKey, lessonDay, lessonName, lessonCode, lessonTeacher, lessonVenue, lessonTime, sqLiteDatabase);
            studentDBHelper.addLesson(lessonDay, lessonName, lessonCode, lessonTeacher, lessonVenue, lessonTime, sqLiteDatabase);
            Toast.makeText(getApplicationContext(), "Lesson added successfully", Toast.LENGTH_SHORT).show();
            studentDBHelper.close();

            showNotification();

            if (lessonDay.equals("Monday")) {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Monday", 1);
                startActivity(intent);
            } else if (lessonDay.equals("Tuesday")) {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Tuesday", 1);
                startActivity(intent);
            } else if (lessonDay.equals("Wednesday")) {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Wednesday", 1);
                startActivity(intent);
            } else if (lessonDay.equals("Thursday")) {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Thursday", 1);
                startActivity(intent);
            } else if (lessonDay.equals("Friday")) {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Friday", 1);
                startActivity(intent);
            } else if (lessonDay.equals("Saturday")) {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Saturday", 1);
                startActivity(intent);
            } else if (lessonDay.equals("Sunday")) {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Sunday", 1);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, DayTimetable.class);
                intent.putExtra("Monday", 1);
                startActivity(intent);
            }
        }
    }

    public void showNotification() {
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(this)
                .setContentTitle(name.getText().toString())
                .setContentText("Day :" + day.getSelectedItem().toString() + "     Time :" + time.getText().toString() + "     Keep Time")
                .setTicker("Student Companion | Lesson")
                .setSmallIcon(R.drawable.slesson);

        Intent moreInfoIntent = new Intent(this, Schedule.class);
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
