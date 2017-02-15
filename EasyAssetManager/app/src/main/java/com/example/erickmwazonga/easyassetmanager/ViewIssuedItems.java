package com.example.erickmwazonga.easyassetmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewIssuedItems extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    IssuedItemsAdapter issuedItemsAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_issued_items);

        listView = (ListView) findViewById(R.id.issuelistview);

        issuedItemsAdapter = new IssuedItemsAdapter(this, R.layout.row_layout_issued);
        listView.setAdapter(issuedItemsAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.issuetoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Issued Items");


        json_string = getIntent().getExtras().getString("json_issued");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count = 0;
            String name, serial, user, date_issued;

            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("name");
                serial = jo.getString("serial_no");
                user = jo.getString("user");
                date_issued = jo.getString("date_issued");

                DataProviderIssuedItems dataProviderIssuedItems  = new DataProviderIssuedItems(name, serial,user,date_issued);
                issuedItemsAdapter.add(dataProviderIssuedItems);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
