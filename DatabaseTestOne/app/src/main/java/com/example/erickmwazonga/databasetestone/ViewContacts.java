package com.example.erickmwazonga.databasetestone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ViewContacts extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDBHelper userDBHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        listView = (ListView) findViewById(R.id.displayListView);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        cursor = userDBHelper.getInfo(sqLiteDatabase);
        if(cursor.moveToFirst()){
           do{
               String name,email,password;
               name = cursor.getString(0);
               email = cursor.getString(1);
               password =cursor.getString(2);

               DataProvider dataProvider=new DataProvider(name,email,password);
               listDataAdapter.add(dataProvider);

           }while (cursor.moveToNext());
        }

    }
}
