package com.example.erickmwazonga.easyassetmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewMaintainedItems extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    MaintainedItemsAdapter maintainedItemsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_maintained_items);

        listView = (ListView) findViewById(R.id.maintainedlistview);

        maintainedItemsAdapter = new MaintainedItemsAdapter(this, R.layout.row_layout_maintained);
        listView.setAdapter(maintainedItemsAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.maintainedtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Maintained Items");


        json_string = getIntent().getExtras().getString("json_maintained");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count = 0;
            String name, serial, reason, maintainer;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                serial = jo.getString("serial_no");
                reason = jo.getString("reason");
                maintainer = jo.getString("maintainer");

                DataProviderMaintainedItems dataProviderMaintainedItems = new DataProviderMaintainedItems(name, serial,reason,maintainer);
                maintainedItemsAdapter.add(dataProviderMaintainedItems);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
