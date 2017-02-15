package com.example.erickmwazonga.easyassetmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

    }

    public void register(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void login(View view) {
        loginUser();
        //startActivity(new Intent(this,Main_Menu.class));
    }

    public void loginUser() {

        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        String name = jsonResponse.getString("name");

                        startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                        //Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_LONG).show();
                        //int age = jsonResponse.getInt("age");

//                        Intent intent = new Intent(Login.this, UserAreaActivity.class);
//                        intent.putExtra("name", name);
//                        intent.putExtra("age", age);
//                        intent.putExtra("username", username);
//                        LoginActivity.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(loginRequest);

    }

    class LoginRequest extends StringRequest {
        private Map<String, String> params;

        public LoginRequest(String email, String password, Response.Listener<String> listener) {
            super(Request.Method.POST, Config.LOGIN_REQUEST_URL, listener, null);

            params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);
        }

        public Map<String, String> getParams() {
            return params;
        }
    }
}