package com.example.erickmwazonga.metame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements  View.OnClickListener {
    EditText etUsername, etPassword;
    Button btLogin;
    TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btLogin=(Button)findViewById(R.id.btLogin);
        tvRegisterLink=(TextView)findViewById(R.id.tvRegisterLink);

        btLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
    }
    @Override
    public  void onClick(View v){
        switch(v.getId()){
            case R.id.btLogin:
                startActivity(new Intent(this,Login.class ));
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this,Register.class ));
                break;
        }
    }


}
