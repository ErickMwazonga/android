package com.example.erickmwazonga.databasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername,editTextEmail,editTextPassword;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername= (EditText) findViewById(R.id.name);
        editTextEmail= (EditText) findViewById(R.id.email);
        editTextPassword= (EditText) findViewById(R.id.password);

        databaseAdapter=new DatabaseAdapter(this);
    }

    public void addUser(View view){
        String username=editTextUsername.getText().toString();
        String email=editTextEmail.getText().toString();
        String password=editTextPassword.getText().toString();

        Long id=databaseAdapter.insertData(username,email,password);
        if(id<0){
            Toast.makeText(this,"Unsuccessully",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Data saved Successful",Toast.LENGTH_LONG).show();
        }
    }
}
