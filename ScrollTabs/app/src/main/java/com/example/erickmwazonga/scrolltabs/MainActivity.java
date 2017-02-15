package com.example.erickmwazonga.scrolltabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);

        FragmentManager manager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(manager));


    }
}

//saving states
//class MyAdapter extends FragmentStatePagerAdapter{
class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new Fragmenta();
        }
        if (position == 1) {
            fragment = new Fragmentb();
        }
        if (position == 2) {
            fragment = new Fragmentc();
        }
        return fragment;
//        return null
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = new String();
        if (position == 0) {
            return "FIRST TAB";
        }
        if (position == 1) {
            return "SECOND TAB";
        }
        if (position == 2) {
            return "THIRD TAB";
        }
        return null;
    }
}
