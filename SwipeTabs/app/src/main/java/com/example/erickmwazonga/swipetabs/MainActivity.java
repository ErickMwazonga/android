package com.example.erickmwazonga.swipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set the icon
        //toolbar.setNavigationIcon(R.drawable.ic_action_location);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("COOL"));
        tabLayout.addTab(tabLayout.newTab().setText("AWESOME"));
        tabLayout.addTab(tabLayout.newTab().setText("CAPTIVATING"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //initializing the menu items

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
        /**
         * On selecting action bar icons
         * */
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Take appropriate action for each action item click
            switch (item.getItemId()) {
                case R.id.action_search:
                    // search action
                    return true;
                case R.id.action_location_found:
                    // location found
                    LocationFound();
                    return true;
                case R.id.action_refresh:
                    // refresh
                    return true;
                case R.id.action_help:
                    // help action
                    return true;
                case R.id.action_check_updates:
                    // check for updates action
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    /**
     * Launching new activity
     * */
    private void LocationFound() {
        Intent i = new Intent(MainActivity.this, LocationFound.class);
        startActivity(i);
    }
}
