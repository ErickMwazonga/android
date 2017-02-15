package com.example.erickmwazonga.sqlitedatabase;

import android.database.sqlite.SQLiteDatabase;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername,editTextPassword,editTextName;
    uniqueDatabaseAdapter uniqueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername= (EditText) findViewById(R.id.editTextUsername);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextName= (EditText) findViewById(R.id.editTextName);

        uniqueAdapter =new uniqueDatabaseAdapter(this);

    }
    public void addUser(View view){
        String user=editTextUsername.getText().toString();
        String pass=editTextPassword.getText().toString();

        Long id=uniqueAdapter.insertData(user,pass);
        if(id<0){
            Message.message(this,"Unsuccessful");
        }else{
            Message.message(this,"successful inserted a row");
        }

    }
    public void viewDetails(View view){
        String data=uniqueAdapter.getAllData();
        Message.message(this,data);
    }
    public void getDetails(View view){
        String s1=editTextName.getText().toString();
        //eg erick mwa
        String sub1=s1.substring(0,s1.indexOf(" "));
        String sub2=s1.substring(s1.indexOf(" ")+1);
        String s3=uniqueAdapter.getData(sub1,sub2);
        Message.message(this,s3);

        /*
        String row=uniqueAdapter.getData(sub1,sub2);
        if(row.equals(null)){
            Message.message(this,"Unsuccessful");
        }else{
            Message.message(this,row);
        }*/

    }
    public  void update(View view){
        uniqueAdapter.update("unique","erique");
    }
    public  void delete(View view){
        int count=uniqueAdapter.delete();
        Message.message(this,""+count);
    }
}
