package com.example.erickmwazonga.easyassetmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewDeletedItems extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    AvailableItemsAdapter availableItemsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_deleted_items);

        listView = (ListView) findViewById(R.id.listview);

        availableItemsAdapter = new AvailableItemsAdapter(this, R.layout.row_layout_items);
        listView.setAdapter(availableItemsAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Available Items");


        json_string = getIntent().getExtras().getString("json_deleted");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count = 0;
            String name, serial, description, quantity;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                serial = jo.getString("serial_no");
                description = jo.getString("description");
                quantity = jo.getString("quantity");

                DataProviderAvailableItems dataProviderAvailableItems = new DataProviderAvailableItems(name, serial,description,quantity);
                availableItemsAdapter.add(dataProviderAvailableItems);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
