package com.example.erickmwazonga.databasetestone;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewSearch extends AppCompatActivity {

    TextView displayEmail,displayPassword;
    EditText searchName;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String searchContactName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_search);

        displayEmail = (TextView) findViewById(R.id.textViewSearchEmail);
        displayPassword = (TextView) findViewById(R.id.textViewSearchPassword);
        searchName = (EditText) findViewById(R.id.editTextSearchName);

        displayEmail.setVisibility(View.GONE);
        displayPassword.setVisibility(View.GONE);
    }

    public void searchContacts(View view){
        searchContactName = searchName.getText().toString();
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor = userDBHelper.getContacts(searchContactName,sqLiteDatabase);
        if(cursor.moveToFirst()){
            String EMAIL = cursor.getString(0);
            String PASSWORD = cursor.getString(1);

            displayEmail.setText(EMAIL);
            displayPassword.setText(PASSWORD);

            displayEmail.setVisibility(View.VISIBLE);
            displayPassword.setVisibility(View.VISIBLE);
        }
    }
}
