package com.example.erickmwazonga.easyassetmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewBorrowedItems extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    BorrowedItemsAdapter borrowedItemsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_borrowed_items);

        listView = (ListView) findViewById(R.id.borrowlistview);

        borrowedItemsAdapter = new BorrowedItemsAdapter(this, R.layout.row_layout_borrowed);
        listView.setAdapter(borrowedItemsAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.borrowtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Borrowed Items");


        json_string = getIntent().getExtras().getString("json_borrowed");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count = 0;
            String name, serial, description, date_returned;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                serial = jo.getString("serial_no");
                description = jo.getString("description");
                date_returned = jo.getString("date_returned");

                DataProviderBorrowedItems dataProviderBorrowedItems  = new DataProviderBorrowedItems(name, serial,description,date_returned);
                borrowedItemsAdapter.add(dataProviderBorrowedItems);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
