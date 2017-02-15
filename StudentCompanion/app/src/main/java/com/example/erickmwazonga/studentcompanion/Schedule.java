package com.example.erickmwazonga.studentcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Schedule");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //Switching the tabs from a different layout
        final Intent intent = getIntent();
        if (intent.hasExtra("Exercises")) {
            viewPager.setCurrentItem(0);
        } else if (intent.hasExtra("Tasks")) {
            viewPager.setCurrentItem(1);
        } else if (intent.hasExtra("Exams")) {
            viewPager.setCurrentItem(2);
        } else if (intent.hasExtra("Assignments")) {
            viewPager.setCurrentItem(3);
        } else if (intent.hasExtra("Holidays")) {
            viewPager.setCurrentItem(4);
        } else {
            viewPager.setCurrentItem(0);
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Exercise(), "EXERCISE");
        adapter.addFragment(new Tasks(), "TASKS");
        adapter.addFragment(new Exams(), "EXAMS");
        adapter.addFragment(new Assignments(), "ASSIGNMENTS");
        adapter.addFragment(new Holidays(), "HOLIDAYS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
