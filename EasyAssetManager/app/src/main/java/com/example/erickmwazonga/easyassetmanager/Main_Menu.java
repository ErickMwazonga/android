package com.example.erickmwazonga.easyassetmanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class Main_Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    ListView listView;
    String[] titles;

    int[] images = {R.drawable.ic_action_add, R.drawable.ic_action_basket, R.drawable.ic_action_box, R.drawable.ic_action_cart,
            R.drawable.ic_action_laptop, R.drawable.ic_action_record, R.drawable.ic_action_list, R.drawable.ic_action_edit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select An Action");

        Resources res = getResources();
        titles = res.getStringArray(R.array.title);

        listView = (ListView) findViewById(R.id.listView);

        menuadapter adapter=new menuadapter(this,titles,images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        ///////////////////////////////////////////////
        //Circular Floating Button

        // Create an icon
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.ic_action_add);

        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton actionButton = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        //actionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        // repeat many times:
        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.ic_action_add);

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.ic_action_list);

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.ic_action_edit);

        SubActionButton add = itemBuilder.setContentView(itemIcon1).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "I am button 1", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),AddItem.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewList.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UpdateItems.class));
            }
        });

        //attach the sub buttons to the main button
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(add)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .build();

        ///////////////////////////////////////////////

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main_menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_exit:
                // finish();
                System.exit(0);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                startActivity(new Intent(this, AddItem.class));
                break;
            case 1:
                startActivity(new Intent(this,BorrowItem.class));
                break;
            case 2:
                startActivity(new Intent(this,IssueItem.class));
                break;
            case 3:
                startActivity(new Intent(this,ReceiveItem.class));
                break;
            case 4:
                startActivity(new Intent(this,ReturnItem.class));
                break;
            case 5:
                startActivity(new Intent(this,RecordMaintenance.class));
                break;
            case 6:
                startActivity(new Intent(this,ViewList.class));
                break;
            case 7:
                startActivity(new Intent(this,UpdateItems.class));
                break;
            default:
                Toast.makeText(this,"Please Select an Action to perform",Toast.LENGTH_SHORT).show();
        }
    }
}
class menuadapter extends ArrayAdapter<String> {

    Context context;
    int[] imagez;
    String[] TitleArray;

    public menuadapter(Context c, String[] titles, int[] imgs) {
        super(c, R.layout.menu_single_row, R.id.textViewIcon, titles);
        this.context = c;
        this.imagez = imgs;
        this.TitleArray = titles;
    }

    class myViewHolder {
        ImageView myImage;
        TextView myTitle;

        myViewHolder(View v) {
            myImage = (ImageView) v.findViewById(R.id.imageViewIcon);
            myTitle = (TextView) v.findViewById(R.id.textViewIcon);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        myViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.menu_single_row, parent, false);
            holder = new myViewHolder(row);
            row.setTag(holder);
            // Log.d("Ricky", "Creating a new row");
        } else {
            holder = (myViewHolder) row.getTag();
            //Log.d("Ricky", "Recycling stuff");
        }
        holder.myImage.setImageResource(imagez[position]);
        holder.myTitle.setText(TitleArray[position]);

        return row;
    }
}