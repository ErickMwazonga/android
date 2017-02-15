package com.example.erickmwazonga.studentcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DayTimetable extends AppCompatActivity {

    private  ViewPager viewpager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_timetable);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Day Timetable");

        viewpager= (ViewPager) findViewById(R.id.daytimetableviewpager);
        setupViewPager(viewpager);

        final Intent intent = getIntent();
        if (intent.hasExtra("Monday")) {
            viewpager.setCurrentItem(0);
        } else if (intent.hasExtra("Tuesday")) {
            viewpager.setCurrentItem(1);
        } else if (intent.hasExtra("Wednesday")) {
            viewpager.setCurrentItem(2);
        } else if (intent.hasExtra("Thursday")) {
            viewpager.setCurrentItem(3);
        } else if (intent.hasExtra("Friday")) {
            viewpager.setCurrentItem(4);
        } else if (intent.hasExtra("Saturday")){
            viewpager.setCurrentItem(5);
        }else if (intent.hasExtra("Sunday")){
            viewpager.setCurrentItem(6);
        }
        else {
            viewpager.setCurrentItem(0);
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewpager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter=new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Monday(),"MONDAY");
        adapter.addFragment(new Tuesday(),"TUESDAY");
        adapter.addFragment(new Wednesday(),"WEDNESDAY");
        adapter.addFragment(new Thursday(),"THURSDAY");
        adapter.addFragment(new Friday(),"FRIDAY");
        adapter.addFragment(new Saturday(),"SATURDAY");
        adapter.addFragment(new Sunday(),"SUNDAY");
        viewPager.setAdapter(adapter);

    }
    class ViewPageAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPageAdapter(FragmentManager manager) {
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
