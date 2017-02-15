package com.example.erickmwazonga.studentcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Gridview operations
    GridView androidGridView;

    String [] gridViewString={"Day Timetable","Schedule","Lesson","Exercise", "Tasks", "Exams", "Assignments","Holiday"};
    int [] gridViewImageId={R.drawable.sdaytimetable,R.drawable.sschedule,R.drawable.slesson,
            R.drawable.sexcercise,R.drawable.stasks,R.drawable.sexams,R.drawable.sassignment,R.drawable.sholiday};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Main Menu");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Gridview operations
        GridViewAdapter gridViewAdapter=new GridViewAdapter(MainMenu.this,gridViewString,gridViewImageId);
        androidGridView= (GridView) findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(gridViewAdapter);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(MainMenu.this,DayTimetable.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainMenu.this,Schedule.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainMenu.this,AddLesson.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainMenu.this,AddExercise.class));
                        break;
                    case 4:
                       startActivity(new Intent(MainMenu.this,AddTask.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainMenu.this,AddExam.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainMenu.this,AddAssignment.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainMenu.this,AddHoliday.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about){
            startActivity(new Intent(this,UserManual.class));
        }else if(id == R.id.sync) {
            // Handle the camera action
        } else if (id == R.id.shareapp) {

        } else if (id == R.id.rateapp) {

        } else if (id == R.id.general) {

        } else if (id == R.id.reminders) {

        } else if(id==R.id.mute){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
