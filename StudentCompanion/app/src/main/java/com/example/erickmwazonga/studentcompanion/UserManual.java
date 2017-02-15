package com.example.erickmwazonga.studentcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserManual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Manual");
    }

    public void day(View view) {
        startActivity(new Intent(this, DayTimetable.class));
    }

    public void sche(View view) {
        startActivity(new Intent(this, Schedule.class));
    }

    public void les(View view) {
        startActivity(new Intent(this, AddLesson.class));
    }

    public void exe(View view) {
        startActivity(new Intent(this, AddExercise.class));
    }

    public void ta(View view) {
        startActivity(new Intent(this, AddTask.class));
    }

    public void ex(View view) {
        startActivity(new Intent(this, AddExam.class));
    }

    public void assign(View view) {
        startActivity(new Intent(this, AddAssignment.class));
    }

    public void hol(View view) {
        startActivity(new Intent(this, AddHoliday.class));
    }
}
