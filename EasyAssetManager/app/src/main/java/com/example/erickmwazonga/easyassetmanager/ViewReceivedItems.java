package com.example.erickmwazonga.easyassetmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewReceivedItems extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    ReceivedItemsAdapter receivedItemsAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_received_items);

        listView = (ListView) findViewById(R.id.receivelistview);

        receivedItemsAdapter = new ReceivedItemsAdapter(this, R.layout.row_layout_received);
        listView.setAdapter(receivedItemsAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.receivetoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Received Items");


        json_string = getIntent().getExtras().getString("json_received");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count = 0;
            String name, serial, description, date_received;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                serial = jo.getString("serial_no");
                description = jo.getString("description");
                date_received = jo.getString("date_received");

                DataProviderReceivedItems dataProviderReceivedItems = new DataProviderReceivedItems(name, serial,description,date_received);
                receivedItemsAdapter.add(dataProviderReceivedItems);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
