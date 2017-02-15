package com.example.erickmwazonga.databasetestone;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText password,email,name;
    Context context = this;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= (EditText) findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);

    }
    public void addUser(View view){
        String contactName = name.getText().toString();
        String contactEmail = email.getText().toString();
        String contactPassword = password.getText().toString();

        userDBHelper = new UserDBHelper(context);
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        userDBHelper.addContacts(contactName,contactEmail,contactPassword,sqLiteDatabase);
        Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_LONG).show();
        userDBHelper.close();
    }

    public void viewContacts(View view){
        startActivity(new Intent(this, ViewContacts.class));
    }
    public  void searchContacts(View view){
        startActivity(new Intent(this, ViewSearch.class));
    }
}
